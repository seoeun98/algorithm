package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_17086_아기상어 {

	static int[][] distance;
	static int n, m;
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		int[][] map = new int[n][m];
		distance = new int[n][m];
		
		for(int i = 0; i < n; i++) {
			Arrays.fill(distance[i], Integer.MAX_VALUE);			
		}
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(reader.readLine());
			for(int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
		
				if(map[i][j] == 1) {
					bfs(i, j);
				}
			}
		}
		
		int answer = 0;
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(distance[i][j] > answer) {
					answer = distance[i][j];
				}
			}
		}
		
		System.out.println(answer);
	}
	
	//12시 방향부터 시계방향으로 8방탐색
	static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};
	public static void bfs(int startR, int startC) {
		Queue<int[]> que = new LinkedList<>();
		que.add(new int[] {startR, startC});
		int[][] tmp = new int[n][m];
		
		for(int i = 0; i < n; i++) {
			Arrays.fill(tmp[i], Integer.MAX_VALUE);
		}
		tmp[startR][startC] = 0;
		
		while(!que.isEmpty()) {
			int[] now = que.poll();
			int nowR = now[0];
			int nowC = now[1];
			
			for(int i = 0; i < 8; i++) {
				int togoR = nowR + dr[i];
				int togoC = nowC + dc[i];
				
				if(togoR >= 0 && togoC >= 0 && togoR < n && togoC < m && //범위 내이고
						tmp[togoR][togoC] > tmp[nowR][nowC] + 1) { //최솟값 경신할 수 있을 때만
					que.add(new int[] {togoR, togoC});
					tmp[togoR][togoC] = tmp[nowR][nowC] + 1;
				}
			}
		}
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(distance[i][j] > tmp[i][j]) {
					distance[i][j] = tmp[i][j];
				}
			}
		}
		
	}

}
