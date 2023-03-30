package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 카카오 경사로 건설하기 비슷한 문제
 */

public class BJ_2206_벽부수고이동하기 {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		
		int n = Integer.parseInt(st.nextToken()); //맵 세로
		int m = Integer.parseInt(st.nextToken()); //맵 가로
		
		int[][] map = new int[n][m];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(reader.readLine());
			String oneLine = st.nextToken();
			
			for(int j = 0; j < m; j++) {
				map[i][j] = oneLine.charAt(j) - '0';
			}
		}
		
		System.out.println(bfs(map));
	}
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	public static int bfs(int[][] map) {
		Queue<int[]> que = new LinkedList<>();
		int[][][] min = new int[map.length][map[0].length][2]; //r, c, 남은 부수기 횟수
		que.add(new int[] {0, 0, 1});
		
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map[0].length; j++) {
				Arrays.fill(min[i][j], 9999999);
			}
		}
		
		min[0][0][0] = 1;
		min[0][0][1] = 1;
		
		while(!que.isEmpty()) {
			int[] now = que.poll();
			int nowR = now[0];
			int nowC = now[1];
			int breakC = now[2]; //현재 부술 수 있는 벽의 수
			
			for(int i = 0; i < 4; i++) {
				int togoR = nowR + dr[i];
				int togoC = nowC + dc[i];
				
				if(togoR >= 0 && togoR < map.length && togoC >= 0 && togoC < map[0].length ) {
					if(map[togoR][togoC] == 0 && min[togoR][togoC][breakC] > min[nowR][nowC][breakC] + 1) {//그냥 뚫려 있는 길
						min[togoR][togoC][breakC] = min[nowR][nowC][breakC] + 1;
						que.add(new int[] {togoR, togoC, breakC});
					}
					
					if(map[togoR][togoC] == 1 && breakC != 0 && min[togoR][togoC][0] > min[nowR][nowC][1] + 1) { //원래 막혀있는데 뚫을 수 있음
						min[togoR][togoC][0] = min[nowR][nowC][1] + 1;
						que.add(new int[] {togoR, togoC, 0});
					}
					
				}
			}
		}
		
		int answer = Math.min(min[map.length - 1][map[0].length - 1][0], min[map.length - 1][map[0].length - 1][1]);
		
		if(answer != 9999999) {
			return answer;
		} else {
			return -1;
		}
	}

}
