package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 카카오 경주로 건설하기 유형
 */

public class BJ_14442_벽부수고이동하기2 {

	static int MAX = 99999999;
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		
		int n = Integer.parseInt(st.nextToken()); //맵 세로
		int m = Integer.parseInt(st.nextToken()); //맵 가로
		int k = Integer.parseInt(st.nextToken()); //부술 수 있는 벽의 수
		
		//맵 설정
		int[][] map = new int[n][m];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(reader.readLine());
			
			String oneLine = st.nextToken();
			for(int j = 0; j < m; j++) {
				map[i][j] = oneLine.charAt(j) - '0';
			}
		}
		
		int answer = bfs(map, n, m, k);
		
		System.out.println(answer == MAX ? -1 : answer + 1);
	}
	
	static int[] dr = {0, 0, -1, 1};
	static int[] dc = {-1, 1, 0, 0};
	public static int bfs(int[][] map, int n, int m, int k) {
		Queue<int[]> que = new LinkedList<>();
		int[][][] check = new int[n][m][k + 1];
		que.add(new int[] {0, 0, k});
		
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				Arrays.fill(check[i][j], MAX);
			}
		}
		
		Arrays.fill(check[0][0], 0);
		
		while(!que.isEmpty()) {
			int[] now = que.poll();
			int nowR = now[0];
			int nowC = now[1];
			int breakable = now[2];
			
			for(int i = 0; i < 4; i++) {
				int togoR = nowR + dr[i];
				int togoC = nowC + dc[i];
				
				if(togoR >= 0 && togoC >= 0 && togoR < n && togoC < m) {
					//막혔는데 부수고 갈 수 있는 경우
					if(map[togoR][togoC] == 1 && breakable > 0) {
						//부수고 가는 게 더 이득일 경우
						if(check[togoR][togoC][breakable - 1] > check[nowR][nowC][breakable] + 1) {
							check[togoR][togoC][breakable - 1] = check[nowR][nowC][breakable] + 1;
							que.add(new int[] {togoR, togoC, breakable - 1});
						}
					}
					
					//안막혀있고, 그냥 가는 게 더 이득일 경우
					if(map[togoR][togoC] == 0 
							&& check[togoR][togoC][breakable] > check[nowR][nowC][breakable] + 1) {
						check[togoR][togoC][breakable] = check[nowR][nowC][breakable] + 1;
						que.add(new int[] {togoR, togoC, breakable});
					}
				}
			}
			
		}
		int answer = MAX;
		for(int i = 0; i < k; i++) {
			answer = Math.min(answer, check[n - 1][m - 1][i]);
		}
		
		return answer;
	}

}
