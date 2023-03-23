package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2468_안전영역 {

	static int mapSize;
	static int[][] map;
	static int min = Integer.MAX_VALUE;
	static int max = Integer.MIN_VALUE;
	public static void main(String[] args) throws IOException{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		
		boolean[] is = new boolean[101];
		
		mapSize = Integer.parseInt(st.nextToken());
		
		map = new int[mapSize][mapSize];
		
		for(int i = 0; i < mapSize; i++) {
			st = new StringTokenizer(reader.readLine());
			
			for(int j = 0; j < mapSize; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if(!is[map[i][j]])
					is[map[i][j]] = true;
				
				if(map[i][j] < min)
					min = map[i][j];
				if(map[i][j] > max)
					max = map[i][j];
			}
		}
		
		boolean[][] check;
		int maxCount = 1;
		for(int k = min; k < max; k++) {
			if(is[k]) {
				int count = 0;
				makeItRain(k);
				
				check = new boolean[mapSize][mapSize];
				for(int i = 0; i < mapSize; i++) {
					for(int j = 0; j < mapSize; j++) {
						if(map[i][j] != 0 && !check[i][j]) {
							dfs(i, j, check);
							count++;
						}
					}
				}
				
				if(maxCount < count)
					maxCount = count;
			}
		}
		
		System.out.println(maxCount);
	}
	
	public static void makeItRain(int height) {
		for(int i = 0; i < mapSize; i++) {
			for(int j = 0; j < mapSize; j++) {
				if(map[i][j] <= height) {
					map[i][j] = 0;
				}
			}
		}
		
	}
	
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	public static int dfs(int r, int c, boolean[][] check) {
		for(int i = 0; i < 4; i++) {
			int togoR = r + dr[i];
			int togoC = c + dc[i];
			
			if(togoR >= 0 && togoC >= 0 && togoR < mapSize && togoC < mapSize
					&& map[togoR][togoC] != 0 && !check[togoR][togoC]) {
				check[togoR][togoC] = true;
				dfs(togoR, togoC, check);
			}
		}
		
		return 0;
	}

}
