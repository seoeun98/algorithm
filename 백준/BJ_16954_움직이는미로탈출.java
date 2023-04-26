package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 맨 아래에서 시작해서 위로 올라가면 됨
 * 
 * 움직일 수 있는 경우
 * 1. 맵 내
 * 2. 이동하려는 위치에 벽이 없는 경우
 * 3. 이동하려는 위치 '위'에 벽이 없는 경우 - 움직임 끝나고 벽 내려오면 막히니까 위에도 벽이 없어야 함
 * 4. 이번 턴 내에 해당 칸으로 이동한 적이 없는 경우
 * 
 * 움직임 한 턴 끝날 때마다 벽 아래로 내려줌
 * 
 * 이 문제는 가만히 있는 경우를 고려하는 것이 중요하다
 * 만약 직전 움직임 턴에서 현재 위치인 [a][b]로 이동한 전적이 있다고 가정해보자
 * 그러면 check[a][b]는 true 상태일 것이다
 * 이제 [a][b]칸에서 다른 칸으로 이동하거나 >>가만히 있는 경우<<도 고려해야 하는데 현재 check[a][b]가 true이므로 가만히 있는 경우는 que에 넣을 수 없게 된다
 * 그러므로 움직임 탐색하기 전에 현재 위치의 check를 false로 바꿔주어야 한다
 * 
 * 아예 그냥 중복체크를 안해도(check를 사용하지 않아도) 통과. 대신 시간은 늘어남
 */

public class BJ_16954_움직이는미로탈출 {

	//시작 : [7][0]
	//도착 : [0][7]
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		int[][] map = new int[8][8];
		
		for(int i = 0; i < 8; i++) {
			StringTokenizer st = new StringTokenizer(reader.readLine());
			String line = st.nextToken();
			
			for(int j = 0; j < 8; j++) {
				switch(line.charAt(j)) {
					case '.' :
						map[i][j] = 0; //빈 칸
						break;
					case '#' :
						map[i][j] = 1; //벽
						break;
				}
			}
		}
		
		if(bfs(map)) {
			System.out.println(1);
		} else {
			System.out.println(0);
		}
	}

	//맨 위로 올라오기만 하면 
	static int[] dr = {-1, -1, -1, 0, 0, 0, 1, 1, 1};
	static int[] dc = {-1, 0, 1, 1, -1, 0, 0, -1, 1};
	public static boolean bfs(int[][] map) {
		Queue<int[]> que = new LinkedList<>();
//		boolean[][] check = new boolean[8][8];
		que.add(new int[] {7, 0});
		
		int count = 0;
		while(!que.isEmpty()) {
			int queSize = que.size();
			
			while(queSize-- > 0) {
				int[] now = que.poll();
				int nowR = now[0];
				int nowC = now[1];
				
//				check[nowR][nowC] = false;
				
				if(nowR == 0 || count >= 8) {
					return true;
				}
				
				for(int i = 0; i < 9; i++) {
					int togoR = nowR + dr[i];
					int togoC = nowC + dc[i];
					
					if(togoR >= 0 && togoC >= 0 && togoR < 8 && togoC < 8 //범위 내이고
//							&& !check[togoR][togoC] //방문한 적이 없고
							&& map[togoR][togoC] == 0 //빈 공간이고
							&& (togoR == 0 || (togoR >= 1 && map[togoR - 1][togoC] != 1))) { //위가 벽이 아니라면
						que.add(new int[] {togoR, togoC});
//						check[togoR][togoC] = true;
					}
				}
			}
			changeMap(map);
			count++;
		}
		
		return false;
	}
	
	public static void changeMap(int[][] map) {
		for(int i = 7; i >= 0; i--) {
			for(int j = 0; j < 8; j++) {
				if(map[i][j] == 1) { //벽이라면
					if(i == 7) { //맨 아래에 있어서 없애야 하는 벽
						map[i][j] = 0;
					} else { //내릴 공간이 있다면
						map[i][j] = 0;
						map[i + 1][j] = 1;
					}
				}
			}
		}
	}
}
