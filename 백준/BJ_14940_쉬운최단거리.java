package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 그냥 단순 bfs
 */
public class BJ_14940_쉬운최단거리 {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		
		int n = Integer.parseInt(st.nextToken()); //맵 세로
		int m = Integer.parseInt(st.nextToken()); //맵 가로
		int[][] map = new int[n][m];
		
		int startR = 0, startC = 0;
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(reader.readLine());
			
			for(int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if(map[i][j] == 2) {
					startR = i;
					startC = j;
				}
			}
		}
		
		int[] dr = {-1, 1, 0, 0};
		int[] dc = {0, 0, -1, 1};
		
		int[][] step = new int[n][m];
		Queue<int[]> que = new LinkedList<>();
		boolean[][] check = new boolean[n][m];
		
		que.add(new int[] {startR, startC});
		check[startR][startC] = true;
		
		int count = 1;
		while(!que.isEmpty()) {
			int queSize = que.size();
			
			while(queSize-- > 0) {
				int[] now = que.poll();
				int nowR = now[0];
				int nowC = now[1];
				
				for(int i = 0; i < 4; i++) {
					int togoR = nowR + dr[i];
					int togoC = nowC + dc[i];
					
					if(togoR >= 0 && togoR < n && togoC >= 0 && togoC < m
							&& map[togoR][togoC] == 1
							&& !check[togoR][togoC]) {
						step[togoR][togoC] = count;
						check[togoR][togoC] = true;
						que.add(new int[] {togoR, togoC});
					}
				}
			}
			
			count++;
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				//시작 지점이 아니고, 갈 수 있는 길인데 간 흔적이 없다면
				if(step[i][j] == 0 && (!(i == startR && j == startC) && map[i][j] != 0)) {
					sb.append("-1 ");
				} else {
					sb.append(step[i][j] + " ");
				}
			}
			
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
		
	}

}
