package work;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * 그냥 쉬운 dfs
 */

public class BJ_1189_컴백홈 {

	static char[][] map;
	static int answer = 0;
	static int mapSizeR = 0, mapSizeC = 0, k;
	public static void main(String[] args) throws IOException {
		Scanner input = new Scanner(System.in);
		
		mapSizeR = input.nextInt();
		mapSizeC = input.nextInt();
		k = input.nextInt();
		
		map = new char[mapSizeR][mapSizeC];
		for(int i = 0; i < mapSizeR; i++) {
			String line = input.next();
			
			for(int j = 0; j < mapSizeC; j++) {
				map[i][j] = line.charAt(j);
			}
		}
		boolean[][] check = new boolean[mapSizeR][mapSizeC];
		check[mapSizeR - 1][0] = true;
		dfs(k - 1, mapSizeR - 1, 0, check);
		
		System.out.println(answer);
		
	}
	
	static int[] dr = {1, -1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	public static void dfs(int count, int r, int c, boolean[][] check) {
		if(count == 0) {
			if(r == 0 && c == mapSizeC - 1) {
				answer++;
			}
			return;
		}
		
		for(int i = 0; i < 4; i++) {
			int togoR = r + dr[i];
			int togoC = c + dc[i];
			
			if(togoR >= 0 && togoC >= 0 && togoR < mapSizeR && togoC < mapSizeC
					&& !check[togoR][togoC]
					&& map[togoR][togoC] != 'T') {
				check[togoR][togoC] = true;
				dfs(count - 1, togoR, togoC, check);
				check[togoR][togoC] = false;
			}
		}
		
	}
	
//	public static int bfs() {
//		Queue<int[]> que = new LinkedList<>();
//		boolean[][][] check = new boolean[r][c][4];
//		que.add(new int[] {r - 1, 0});
////		check[r - 1][0] = true;
//		
//		int[] dr = {0, -1, 0, 1};
//		int[] dc = {1, 0, -1, 0};
//		
//		int answer = 0;
//		int count = 1;
//		while(!que.isEmpty()) {
//			int queSize = que.size();
//			
//			while(queSize-- > 0) {
//				int[] now = que.poll();
//				int nowR = now[0];
//				int nowC = now[1];
//				
//				if(count == k && nowR == 0 && nowC == c - 1) {
//					answer++;
//				}
//				
//				for(int i = 0; i < 4; i++) {
//					int togoR = nowR + dr[i];
//					int togoC = nowC + dc[i];
//					
////					if(togoR >= 0 && togoC >= 0 && togoR < r && togoC < c && //범위 내
////							!check[togoR][togoC] && map[togoR][togoC] != 'T' ) { //방문한 적이 없고 갈 수 있다면
////						que.add(new int[] {togoR, togoC});
////						check[togoR][togoC] = true;
//					}
//				}
//			}
//			count++;
//			
////			if(count > k) {
////				break;
////			}
////		}
//		
//		return answer;
//	}

}
