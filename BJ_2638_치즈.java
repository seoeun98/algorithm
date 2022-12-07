package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_2638_치즈 {

	static int[][] map;
	static int N, M;
	public static void main(String[] args) throws IOException{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(reader.readLine());
			
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int count = 0;
		
		while(true) {
			if(!bfs())
				break;
			
			for(int i = 0; i < N; i++)
				for(int j = 0; j < M; j++)
					if(map[i][j] == 2)
						map[i][j] = 1;

			count++;
		}
		
		System.out.println(count);
	}
	
	static int[] dr = {0, 1, -1, 0};
	static int[] dc = {1, 0, 0, -1};
	public static boolean bfs() {
		boolean isThereAnyCheese = false;
		
		Queue<int[]> que = new LinkedList<>();
		boolean[][] check = new boolean[N][M];
		que.add(new int[] {0, 0});
		check[0][0] = true;
		
		while(!que.isEmpty()) {
			int[] now = que.poll();
			int nowR = now[0];
			int nowC = now[1];
			
			for(int i = 0; i < 4; i++) {
				int r = nowR + dr[i];
				int c = nowC + dc[i];
				
				if(r >= 0 && c >= 0 && r < N && c < M && !check[r][c]) { //범위 내이고
					if(map[r][c] != 0) { //치즈면 닿는다고 표시
						isThereAnyCheese = true; //아직 치즈가 남아있음
						map[r][c]++;
						
						if(map[r][c] == 3) { //공기랑 두 면이 닿은 치즈는 없애버림
							map[r][c] = 0;
							check[r][c] = true; //0이 되면 밑 else if문에서 걸릴 수도 있으니까 그거 방지
						}
						
					}
					else { //공기이고 접근한 적 없다면
						que.add(new int[] {r, c});
						check[r][c] = true;
					}
				}
			}
		}
		
		return isThereAnyCheese;
		
	}

}
