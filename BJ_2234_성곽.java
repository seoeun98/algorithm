package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_2234_성곽 {

	public static int[][] check;
	public static int mapR, mapC;
	public static int[][][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st =  new StringTokenizer(reader.readLine());
		
		mapC = Integer.parseInt(st.nextToken());
		mapR = Integer.parseInt(st.nextToken());
		
		check = new int[mapR][mapC];
		
		map = new int[mapR][mapC][4];
		
		for(int i = 0; i < mapR; i++) {
			st = new StringTokenizer(reader.readLine());
			
			for(int j = 0; j < mapC; j++) {
				int cell = Integer.parseInt(st.nextToken());
				String num = Integer.toBinaryString(cell);
				
				for(int k = num.length() - 1; k >= 0; k--) {
					map[i][j][k] = num.charAt(num.length() - k - 1) - '0';
				}
			}
		}
		
		HashMap<Integer, Integer> map = new HashMap<>();
		
		//구역 나누기 체크
		int biggest = 0;
		int territoryN = 0;
		for(int i = 0; i < mapR; i++) {
			for(int j = 0; j < mapC; j++) {
				if(check[i][j] == 0) {
					//영역 범위 검사					
					territoryN++;
					
					int nowT = bfs(i, j, territoryN);
					
					map.put(territoryN, nowT);
					
					if(biggest < nowT) {
						biggest = nowT;
					}
				}
			}
		}
		
		int maxUnion = 0;
		//더하기
		for(int i = 0; i < mapR; i++) {
			for(int j = 0; j < mapC; j++) {
				int nowM = check[i][j];
				
				for(int k = 0; k < 4; k++) {
					int togoR = i + dr[k];
					int togoC = j + dc[k];
					
					if(togoR >= 0 && togoR < mapR && togoC >= 0 && togoC < mapC &&
							check[togoR][togoC] != check[i][j]
						&& maxUnion < map.get(nowM) + map.get(check[togoR][togoC])) {
						maxUnion = map.get(nowM) + map.get(check[togoR][togoC]);
					}
				}
			}
		}
		
		System.out.println(territoryN);
		System.out.println(biggest);
		System.out.println(maxUnion);
		
	}
	
	static int[] dr = {0, -1, 0, 1};
	static int[] dc = {-1, 0, 1, 0};
	public static int bfs(int startR, int startC, int nowN) {
		boolean[][] visited = new boolean[mapR][mapC];
		visited[startR][startC] = true;
		
		Queue<int[]> que = new LinkedList<>();
		que.add(new int[] {startR, startC});
		
		int territory = 0;
		
		while(!que.isEmpty()) {
			int[] now = que.poll();
			int nowR = now[0];
			int nowC = now[1];
			
			check[nowR][nowC] = nowN;
			territory++;
			
			for(int i = 0; i < 4; i++) {
				int togoR = nowR + dr[i];
				int togoC = nowC + dc[i];
				
				if(togoR >= 0 && togoR < mapR && togoC >= 0 && togoC < mapC 
						&& map[nowR][nowC][i] == 0 
						&& !visited[togoR][togoC]) { 
					//벽이 없을 경우만 && 방문하지 않은 곳일 때만
					que.add(new int[] {togoR, togoC});
					visited[togoR][togoC] = true;
				}
			}
		}
		
		return territory;
	}

}
