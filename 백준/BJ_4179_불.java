package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * bfs
 * 
 * 불이랑 지훈이랑 같은 큐에 넣고 불 먼저 번지게 한 후에 갈 수 있는 곳 가게 하기
 */

public class BJ_4179_불 {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		
		int mapR = Integer.parseInt(st.nextToken());
		int mapC = Integer.parseInt(st.nextToken());
		int[][] map = new int[mapR][mapC];
		
		int startR = 0, startC = 0;
		
		for(int i = 0; i < mapR; i++) {
			st = new StringTokenizer(reader.readLine());
			String line = st.nextToken();
			
			for(int j = 0; j < mapC; j++) {
				switch(line.charAt(j)) {
				case '#' :
					map[i][j] = -1;
					break;
				case 'J' :
					startR = i;
					startC = j;
					break;
				case 'F' :
					map[i][j] = 1;
					break;
				}
			}
		}
		
		int answer = bfs(map, startR, startC);
		System.out.println(answer == 0 ? "IMPOSSIBLE" : answer);
	}
	
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	public static int bfs(int[][] map, int startR, int startC) {
		Queue<int[]> que = new LinkedList<>();
		boolean[][] check = new boolean[map.length][map[0].length];
		
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map[0].length; j++) {
				if(map[i][j] == 1) {
					que.add(new int[] {i, j, 1});
				}
			}
		}
			
		que.add(new int[] {startR, startC, 2}); 
		check[startR][startC] = true;
			
		int count = 0;
		while(!que.isEmpty()) {
			int queSize = que.size();
			
			while(queSize-- > 0) {
				int[] now = que.poll();
				int nowR = now[0];
				int nowC = now[1];
				int who = now[2];
				
				if(who == 2) { //2 == 지훈
					if(nowR == map.length - 1 || nowR == 0 || nowC == map[0].length - 1 || nowC == 0) {
						return count + 1;
					}
					
					for(int i = 0; i < 4; i++) {
						int togoR = nowR + dr[i];
						int togoC = nowC + dc[i];
						
						if(togoR >= 0 && togoC >= 0 && togoR < map.length && togoC < map[0].length
								&& map[togoR][togoC] == 0
								&& !check[togoR][togoC]) {
							que.add(new int[] {togoR, togoC, 2});
							check[togoR][togoC] = true;
						}
					}
				} else { //불
					for(int i = 0; i < 4; i++) {
						int togoR = nowR + dr[i];
						int togoC = nowC + dc[i];
						
						if(togoR >= 0 && togoC >= 0 && togoR < map.length && togoC < map[0].length
								&& map[togoR][togoC] == 0) {
							que.add(new int[] {togoR, togoC, 1});
							map[togoR][togoC] = 1;
						}
					}
				}
				
				
			}
			
			count++;
		}
		
		return 0;
	}

}
