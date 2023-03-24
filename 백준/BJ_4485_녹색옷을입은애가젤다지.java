package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


/*
 * bfs
 * 
 */
public class BJ_4485_녹색옷을입은애가젤다지 {

	static int answer;
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		
		int mapSize = Integer.parseInt(st.nextToken());
		int problemCount = 1;
		while(mapSize != 0) {
			int[][] map = new int[mapSize][mapSize];
			
			for(int i = 0; i < mapSize; i++) {
				st = new StringTokenizer(reader.readLine());
				
				for(int j = 0; j < mapSize; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
					
			System.out.println("Problem " + problemCount++ + ": " + bfs(map));
			st = new StringTokenizer(reader.readLine());
			mapSize = Integer.parseInt(st.nextToken());
		}
	}

	static int[] dr = {0, 0, 1, -1};
	static int[] dc = {1, -1, 0, 0};
	public static int bfs(int[][] map) {
//		if(nowR == map.length - 1 && nowC == map.length - 1) {
//			if(money < answer) {
//				answer = money;
//			}
//			return;
//		}
//		
//		for(int i = 0; i < 4; i++) {
//			int togoR = nowR + dr[i];
//			int togoC = nowC + dc[i];
//			
//			if(togoR >= 0 && togoC >= 0 && togoR < map.length && togoC < map.length
//					&& answer > money + map[togoR][togoC]
//							&& !visited[togoR][togoC]) {
//				visited[togoR][togoC] = true;
//				dfs(map, togoR, togoC, money + map[togoR][togoC], visited);
//				visited[togoR][togoC] = false;
//			}
//		}
		
		int max = 2100000000;
		Queue<int[]> que = new LinkedList<>();
		int[][] check = new int[map.length][map.length];
		
		for(int i = 0; i < map.length; i++) {
			Arrays.fill(check[i], max);
		}
		
		que.add(new int[] {0, 0});
		check[0][0] = map[0][0];
		
		while(!que.isEmpty()) {
			int[] now = que.poll();
			int nowR = now[0];
			int nowC = now[1];
			
			for(int i = 0; i < 4; i++) {
				int togoR = nowR + dr[i];
				int togoC = nowC + dc[i];
				
				if(togoR >= 0 && togoC >= 0 && togoR < map.length && togoC < map.length
						&& (check[togoR][togoC] == max || check[togoR][togoC] > check[nowR][nowC] + map[togoR][togoC])) {
					check[togoR][togoC] = check[nowR][nowC] + map[togoR][togoC];
					que.add(new int[] {togoR, togoC});
				}
			}
		}
		
		return check[map.length - 1][map.length - 1];
	}
}
