package work;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


/*
 * (제일 낮은 높이 + 1) ~ (제일 높은 높이)을 기준으로 탐색
 * 담을 수 있다면(물의 높이가 칸의 높이보다 더 높다면) 해당 칸을 기준으로 bfs 탐색.
 * bfs로 칸에 물 담으면서 물 담은 칸은 나중에 또 중복 탐색 안하도록 방지해줌
 * 해당 칸에 특정 높이로 물을 담을 수 있다면 +1
 * bfs 탐색을 하다가 모서리에 닿으면 수영장이 안만들어지는 거니까 바로 return 0 
 * 
 */

public class BJ_1113_수영장만들기 {
	static boolean[][] checkForR;
	static int[][] map;
	static int mapSizeR, mapSizeC;
	public static void main(String[] args) throws IOException {
		Scanner input = new Scanner(System.in);
		
		mapSizeR = input.nextInt();
		mapSizeC = input.nextInt();
		
		map = new int[mapSizeR][mapSizeC];
		
		int max = 0;
		int min = 100000;
		for(int i = 0; i < mapSizeR; i++) {
			String oneLayer = input.next().toString();
			
			for(int j = 0; j < mapSizeC; j++) {
				map[i][j] = oneLayer.charAt(j) - '0';
				
				if(map[i][j] < min) {
					min = map[i][j];
				}
				if(map[i][j] > max) {
					max = map[i][j];
				}
			}
		}
		
		
		int answer = 0;
//		while(min <= max) {
//			int mid = (max + min) / 2;
//			
//			checkForR = new boolean[mapSizeR][mapSizeC];
//			int result = 0;
//			
//			for(int i = 0; i < mapSizeR; i++) {
//				for(int j = 0; j < mapSizeC; j++) {
//					if(map[i][j] < mid && !checkForR[i][j]) {
//						result += bfs(i, j, mid);
//					}
//				}
//			}
//			
//			if(result > answer) {
//				answer = result;
//			} 
//			
//			if(result > 0) {
//				min = mid + 1;
//			} else {
//				max = mid;
//			}
//		}
		
		for(int waterH = min + 1; waterH <= max; waterH++) {
			
			checkForR = new boolean[mapSizeR][mapSizeC];
			
			for(int i = 1; i < mapSizeR - 1; i++) {
				for(int j = 1; j < mapSizeC - 1; j++) {
					if(map[i][j] < waterH && !checkForR[i][j]) {
						answer += bfs(i, j, waterH);
					}
				}
			}
		}
		
		System.out.println(answer);
		
	}
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	public static int bfs(int startR, int startC, int waterH) {
		Queue<int[]> que = new LinkedList<>();
		boolean[][] check = new boolean[mapSizeR][mapSizeC];
		
		check[startR][startC] = true;
		que.add(new int[] {startR, startC});
		
		int total = 0;
		
		while(!que.isEmpty()) {
			int[] now = que.poll();
			int nowR = now[0];
			int nowC = now[1];
			
			total++;
			checkForR[nowR][nowC] = true;
			
			for(int i = 0; i < 4; i++) {
				int togoR = nowR + dr[i];
				int togoC = nowC + dc[i];
				
				if(togoR >= 0 && togoC >= 0 && togoR < mapSizeR && togoC < mapSizeC //범위 내이고
						&& !check[togoR][togoC] //방문한 적 없고
						&& map[togoR][togoC] < waterH) { //물이 담기는 곳이면
					check[togoR][togoC] = true;
					que.add(new int[] {togoR, togoC});
				}
				
				if(togoR < 0 || togoC < 0 || togoR >= mapSizeR || togoC >= mapSizeC) { 
					//범위를 벗어나면 == 끝자락이면 물을 담을 수 없는 곳
					return 0;
				}
				
			}
		}
		
//		for(int i = 0; i < mapSizeR; i++) {
//			for(int j = 0; j < mapSizeC; j++) {
//				if(check[i][j]) {
//					total += (waterH - map[i][j]);
//					checkForR[i][j] = true;
//				}
//			}
//		}
		
		return total;
	}

}
