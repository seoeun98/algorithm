package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 문제에서 주어진 순서대로 구현하면 됨
 * 
 * 1. 국경을 해제할 국가가 있는지 검사 
 * --> 검사 중복을 막기 위해 아래와 왼쪽으로만 검사한다
 * --> 국경 해제 조건이 된다면 해당 국가와 상대 국가의 국경을 true로 바꿈
 * --> 국경을 해제할 국가가 없다면 끝
 * 
 * 2. 같은 연합의 국가의 총 인구수, 각 국가의 위치를 저장하며 bfs 탐색
 * --> bfs 탐색이 끝난 이후 한 연합의 국가들의 국민 수를 갱신
 */
public class BJ_16234_인구이동 {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		
		int n = Integer.parseInt(st.nextToken()); //맵 크기
		int min = Integer.parseInt(st.nextToken()); //인구 차이가 min명 이상일 때
		int max = Integer.parseInt(st.nextToken()); //인구 차이가 max명 이상일 때
		
		int[][] map = new int[n][n];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(reader.readLine());
			
			for(int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(bfs(map, n, min, max));
		
	}
	
	//아래 오른쪽 위 왼쪽
	static int[] dr = {1, 0, -1, 0};
	static int[] dc = {0, 1, 0, -1};
	public static int openBorder(int[][] map, boolean[][][] border, int n, int min, int max) {
		int count = 0;
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				//아래, 오른쪽만 비교 --> 중복 비교를 막기 위해서
				int now = map[i][j];
				
				for(int k = 0; k < 2; k++) {
					//비교 대상이 범위 내일 경우만
					if(i + dr[k] >= 0 && j + dc[k] >= 0 && i + dr[k] < n && j + dc[k] < n) {
						//이동 대상에 해당된다면
						if(Math.abs(now - map[i + dr[k]][j + dc[k]]) >= min && 
								Math.abs(now - map[i + dr[k]][j + dc[k]]) <= max) {
							//문 열기
							border[i][j][k] = true;
							border[i + dr[k]][j + dc[k]][k + 2] = true;
							count++;
						}
					}
				}
			}
		}
		
		return count;
	}
	
	public static int bfs(int[][] map, int n, int min, int max) {
		boolean[][][] border = new boolean[n][n][4]; //문이 열려 있는지
		
		int answer = 0;
		while(openBorder(map, border, n, min, max) > 0) { //열려있는 곳이 없을 때까지 반복
			answer++;
			boolean[][] check = new boolean[n][n]; //특정 국가를 방문한 적이 있는 지를 체크
			Queue<int[]> countries = new LinkedList<>(); //여기에 들어간 국가들끼리 인구를 공유하게 된다
			
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					
					int people = 0;
					
					if(!check[i][j]) {
						//공유할 국가들의 총 인구 수
						Queue<int[]> que = new LinkedList<>(); //bfs 탐색용 que
						que.add(new int[] {i, j});
						countries.add(new int[] {i, j});
						people += map[i][j];
						check[i][j] = true;
						
						while(!que.isEmpty()) {
							int[] now = que.poll();
							int nowR = now[0];
							int nowC = now[1];
							
							for(int k = 0; k < 4; k++) {
								int togoR = nowR + dr[k];
								int togoC = nowC + dc[k];
								
								if(togoR >= 0 && togoC >= 0 && togoR < n && togoC < n
										&& !check[togoR][togoC]
										&& border[nowR][nowC][k]) {
									que.add(new int[] {togoR, togoC});
									check[togoR][togoC] = true;
									people += map[togoR][togoC];
									countries.add(new int[] {togoR, togoC});
								}
							}
						}
						
						int countryN = countries.size();
						int sharePeople = people / countryN;
						//국가끼리 공유
						while(!countries.isEmpty()) {
							int[] c = countries.poll();
							int cR = c[0];
							int cC = c[1];
							
							map[cR][cC] = sharePeople;
						}
					}
				}
			}	
			
			border = new boolean[n][n][4];
		}
		
		return answer;
	}

}
