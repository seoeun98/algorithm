package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_18808_스티커붙이기 {

	static boolean[][] map;
	static int mapR, mapC;
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		
		mapR = Integer.parseInt(st.nextToken()); //노트북 세로
		mapC = Integer.parseInt(st.nextToken()); //노트북 가로
		int stickerN = Integer.parseInt(st.nextToken()); //스티커 수
				
		map = new boolean[mapR][mapC];
		int[][][] list = new int[stickerN][][]; //스티커 목록
		
		for(int i = 0; i < stickerN; i++) {			
			st = new StringTokenizer(reader.readLine());
			int paperR = Integer.parseInt(st.nextToken()); //모눈종이 세로
			int paperC = Integer.parseInt(st.nextToken()); //모눈종이 가로
			
			list[i] = new int[paperR][paperC];
			
			for(int j = 0; j < paperR; j++) {
				st = new StringTokenizer(reader.readLine());
				
				for(int k = 0; k < paperC; k++) {
					list[i][j][k] = Integer.parseInt(st.nextToken());
				}
			}
		}
		
		for(int k = 0; k < stickerN; k++) {
			
			A: for(int r = 0; r < 4; r++) {
				 for(int i = 0; i < mapR; i++) {
					for(int j = 0; j < mapC; j++) {
						if(!map[i][j]) {
							if(isAttachable(i, j, list[k])) {
								break A;
							}
						}
					}
				}
				 list[k] = rotate(list[k]);
			}
		}
		
		//스티커 붙여진 영역 세기
		int answer = 0 ;
		for(int i = 0; i < mapR; i++) {
			for(int j = 0; j < mapC; j++) {
				if(map[i][j]) {
					answer++;
				}
			}
		}
		
		System.out.println(answer);
	}
	
	//여기서 붙이는 것까지 같이 수행
	public static boolean isAttachable(int r, int c, int[][] sticker) {
		
		//제일 처음으로 나오는 게 기준점
		int pointR = -1;
		int pointC = -1;
		A: for(int i = 0; i < sticker.length; i++) {
			for(int j = 0; j < sticker[i].length; j++) {
				if(sticker[i][j] == 1) {
					pointR = i;
					pointC = j;
					break A;
				}
			}
		}
		boolean first = true;
		for(int i = 0; i < sticker.length; i++) {
			for(int j = 0; j < sticker[i].length; j++) {
				if(sticker[i][j] == 1) {
					//붙여야하는데 맵 범위 밖이거나 이미 스티커가 있는 곳에 붙여야하면 불가능
					if(r + i - pointR < 0 || r + i - pointR >= mapR ||  c + j - pointC < 0 || c + j - pointC >= mapC) {
						return false;
					} else if(map[r + i - pointR][c + j - pointC] && sticker[i][j] == 1) {
						return false;
					}
				}
			}
		}
		
		//붙이기
		for(int i = 0; i < sticker.length; i++) {
			for(int j = 0; j < sticker[i].length; j++) {
				if(sticker[i][j] == 1) {
					map[r + i - pointR][c + j - pointC] = true;
				}
			}
		}
		
		return true;
	}
	
	//90도 회전 함수
	public static int[][] rotate(int[][] sticker) {
		int rSize = sticker.length;
		int cSize = sticker[0].length;
		
		int[][] rotate = new int[cSize][rSize];
		
		for(int i = 0; i < rSize; i++) {
			for(int j = 0; j < cSize; j++) {
				rotate[j][rSize - 1 - i] = sticker[i][j];
			}
		}
		
		return rotate;
	}

}
