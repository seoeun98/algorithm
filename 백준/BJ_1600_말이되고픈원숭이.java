package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 카카오 경주로 건설하기와 같은 풀이
 *  
 */

public class BJ_1600_말이되고픈원숭이 {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		
		int k = Integer.parseInt(st.nextToken()); //말처럼 뛸 수 있는 횟수
		st = new StringTokenizer(reader.readLine());
		int mapSizeC = Integer.parseInt(st.nextToken()); //맵 가로길이
		int mapSizeR = Integer.parseInt(st.nextToken()); //맵 세로길이
		
		int[][] map = new int[mapSizeR][mapSizeC];
		
		for(int i = 0; i < mapSizeR; i++) {
			st = new StringTokenizer(reader.readLine());
			
			for(int j = 0; j < mapSizeC; j++) {
				map[i][j] = Integer.parseInt(st.nextToken()); //0은 평지, 1 장애물
			}
		}
		
		System.out.println(bfs(k, map));
	}
	
	static int[] dr = {0, 0, -1, 1};
	static int[] dc = {1, -1, 0, 0};
	static int[] drH = {-2, -1, -2, -1, 1, 1, 2, 2};
	static int[] dcH = {-1, -2, 1, 2, -2, 2, -1, 1};
	static int bfs(int k, int[][] map) {
		Queue<int[]> que = new LinkedList<>(); 
		int[][][] check = new int[map.length][map[0].length][k + 1];
		int max = 2100000000;
		
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map[i].length; j++) {
				if(!(i == 0 && j == 0)) {
					Arrays.fill(check[i][j], max);
				}
			}
		}
		
		que.add(new int[] {0, 0, k});
		
		while(!que.isEmpty()) {
			int[] now = que.poll();
			int nowR = now[0];
			int nowC = now[1];
			int jump = now[2];
			
			
			//말처럼 안뛰고 이동
			for(int i = 0; i < 4; i++) {
				int togoR = nowR + dr[i];
				int togoC = nowC + dc[i];
				
				if(togoR >= 0 && togoC >= 0 && togoR < map.length && togoC < map[0].length
						&& check[togoR][togoC][jump] > check[nowR][nowC][jump] + 1
						&& map[togoR][togoC] != 1) {
					check[togoR][togoC][jump] = check[nowR][nowC][jump] + 1;
					que.add(new int[] {togoR, togoC, jump});
				}
			}
			
			
			//말처럼 뛸 수 있는 기회가 남았다면 말처럼 뛰어서 이동
			if(jump > 0) {
				for(int i = 0; i < 8; i++) {
					int togoR = nowR + drH[i];
					int togoC = nowC + dcH[i];
					
					if(togoR >= 0 && togoC >= 0 && togoR < map.length && togoC < map[0].length
							&& check[togoR][togoC][jump - 1] > check[nowR][nowC][jump] + 1
							&& map[togoR][togoC] != 1) {
						check[togoR][togoC][jump - 1] = check[nowR][nowC][jump] + 1;
						que.add(new int[] {togoR, togoC, jump - 1});
					}
				}
			}
		}
		
		int min = max;
		for(int i = 0; i < k + 1; i++) {
			if(check[map.length - 1][map[0].length - 1][i] < min) {
				min = check[map.length - 1][map[0].length - 1][i];
			}
		}
		
		
		return min == max ? -1 : min;
	}

}
