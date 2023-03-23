package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


/* 
 * - 과정 -
 * 1. 방과 연결된 스위치 다 켜기
 * 2. 현재 방에서 갈 수 있는 방 상하좌우로 넓히기
 * 3. 갈 수 있고 & 불 켜져 있고 & 아직 방문한 적 없는 방이 있다면 해당 방을 que에 넣고 방문 체크
 * 
 * - 갈 수 있는 방을 탐색 할 때 - 
 * 처음에는 bfs로 하나하나 접근하였으나 단순하게 생각하면 bfs까지도 필요 없음. 
 * 그냥 갈 수 있는 방에 인접해있는 방이면 그 방도 갈 수 있는 방이 됨. 단순 for문으로 해결 가능
 * 
 */

public class BJ_11967_불켜기 {		

		static int n;
		static boolean[][] isLightOn; //현재 방에 불이 켜져있는지
		static boolean[][] reachable; //갈 수 있는 방인지
		static boolean[][] visited; //방문한 적이 있는지
		static LinkedList<int[]>[][] map;
		public static void main(String[] args) throws IOException {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer st = new StringTokenizer(reader.readLine());
			
			n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			isLightOn = new boolean[n][n]; //불이 켜져 있는가
			isLightOn[0][0] = true;
			
			reachable = new boolean[n][n]; //갈 수 있는가
			reachable[0][0] = true;
			reachable[0][1] = true;
			reachable[1][0] = true;
			 
			visited = new boolean[n][n]; //방문 했었는가
			visited[0][0] = true;
			
			//맵 초기화
			map = new LinkedList[n][n];
			
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					map[i][j] = new LinkedList<>();
				}
			}
			
			//각 방에 연결되어있는 스위치 입력
			for(int i = 0; i < m; i++) {
				st = new StringTokenizer(reader.readLine());
				
				int fR = Integer.parseInt(st.nextToken()) - 1;
				int fC = Integer.parseInt(st.nextToken()) - 1;
				int sR = Integer.parseInt(st.nextToken()) - 1;
				int sC = Integer.parseInt(st.nextToken()) - 1;
				
				map[fR][fC].add(new int[] {sR, sC});
			}
			
			Queue<int[]> que1 = new LinkedList<>(); //현재 내가 갈 수 있는 방의 위치
			que1.add(new int[] {0, 0});
			
			while(!que1.isEmpty()) {
				
				int[] now = que1.poll();
				
				// 현재 방과 연결되어있는 스위치 켜기
				for(int[] linked : map[now[0]][now[1]]) {
					if(!isLightOn[linked[0]][linked[1]]) {
						isLightOn[linked[0]][linked[1]] = true;
					}
				}
	
				// 현재 방 상하좌우로 갈 수 있는 영역 넓히기
				int[] dr = {0, -1, 1, 0};
				int[] dc = {1, 0, 0, -1};
				
				for(int i = 0; i < 4; i++) {
					int rr = now[0] + dr[i];
					int cc = now[1] + dc[i];
					
					if(rr >= 0 && rr < n && cc >= 0 && cc < n
							&& !reachable[rr][cc]) {
						reachable[rr][cc] = true;
					}
				}
				
				// 갈 수 있고 & 불 켜져 있는데 & 아직 방문 안한 방 있으면 그 방으로 이동
				for(int i = 0; i < n; i++) {
					for(int j = 0; j < n; j++) {
						if(reachable[i][j] && isLightOn[i][j] && !visited[i][j]) {
							que1.add(new int[] {i, j});
							visited[i][j] = true;
						}
					}
				}
			}
			
			// 불 켜져 있는 방의 수 세기
			int answer = 0;
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					if(isLightOn[i][j]) {
						answer++;
					}
				}
			}
			
			System.out.println(answer);
		}

}
