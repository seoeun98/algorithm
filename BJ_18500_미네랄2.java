package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 1. 미네랄 부수기
 * 2. 바닥에 맞닿아있는 클라스터와 연결되어있지 않은 부분(A) 찾기
 * 3. A 내리기
 * 3-1. 아래에서부터 내리기(위에서부터 하면 중첩돼서 처리하기 어려움)
 * 3-2. 다다음 순서까지 미리 보기 --> 다다음에 바닥에 닿거나 바닥에 붙어있는 클러스터랑 연결된다? 그러면 그만 내려야함
 *     					  --> 그게 아니라면 아직 더 내려야 바닥이나 기존 클러스터와 연결될 수 있으므로 계속 내리기
 */

public class BJ_18500_미네랄2 {

	static int mapR, mapC;
	static char[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		
		mapR = Integer.parseInt(st.nextToken());
		mapC = Integer.parseInt(st.nextToken());
		
		map = new char[mapR][mapC];
		
		for(int i = 0; i < mapR; i++) {
			st = new StringTokenizer(reader.readLine());
			
			String oneLine = st.nextToken();
			
			for(int j = 0; j < mapC; j++) {
				map[i][j] = oneLine.charAt(j);
			}
		}
		
		st = new StringTokenizer(reader.readLine());
		int throwN = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(reader.readLine());
		for(int i = 1; i <= throwN; i++) {
			int throwR = mapR - Integer.parseInt(st.nextToken()); //던진 높이
			int throwC = 0;
			
			if(i % 2 == 0) { //오른쪽에서 던질 차례
				throwC = mapC - 1;
			}
			
			//미네랄 없애기
			for(int j = 0; j < mapC; j++) {
				int strowC = Math.abs(j - throwC); //막대의 현 위치
				
				if(map[throwR][strowC] == 'x') { //미네랄 부숨
					map[throwR][strowC] = '.';
					break;
				}
			}
			
			//내리기
			down();
		}
		
		//답 출력
		for(int i = 0; i < mapR; i++) {
			for(int j = 0; j < mapC; j++) {
				System.out.print(map[i][j]);
			}
			
			if(i < mapR - 1) {
				System.out.println();
			}
		}
	}
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	public static void down() {
		boolean[][] check = new boolean[mapR][mapC];
		Queue<int[]> que = new LinkedList<>();
		
		for(int i = 0; i < mapC; i++) {
			if(map[mapR - 1][i] == 'x') {
				check[mapR - 1][i] = true;
				que.add(new int[] {mapR - 1, i});
			}
		}
		
		while(!que.isEmpty()) {
			int[] now = que.poll();
			int nowR = now[0];
			int nowC = now[1];
			
			for(int i = 0; i < 4; i++) {
				int togoR = nowR + dr[i];
				int togoC = nowC + dc[i];
				
				if(togoR >= 0 && togoC >= 0 && togoR < mapR && togoC < mapC && //범위 내이고
						!check[togoR][togoC] && map[togoR][togoC] == 'x') { //방문한 적 없고, 미네랄이면
					que.add(new int[] {togoR, togoC});
					check[togoR][togoC] = true;
				}
			}
		}
		
		boolean keepContinue = true;
		boolean isAny = false;
		
		do {
			for(int i = mapR - 2; i >= 0; i--) {
				for(int j = 0; j < mapC; j++) {
					if(map[i][j] == 'x' && !check[i][j]) {
						map[i + 1][j] = 'x';
						map[i][j] = '.';
						
						if(i + 2 == mapR 
								|| (i + 2 < mapR && (map[i + 2][j] == 'x' && check[i + 2][j]))) {
							keepContinue = false;
						}
						
						isAny = true;
					}
				}
			}
			
		} while(keepContinue && isAny);
		
		
	}

}
