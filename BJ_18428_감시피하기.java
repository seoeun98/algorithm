package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_18428_감시피하기 {
	
	static int mapSize;
	static char[][] map;
	static boolean check = true;
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		
		mapSize = Integer.parseInt(st.nextToken());
		
		map = new char[mapSize][mapSize];
		
		for(int i = 0; i < mapSize; i++) {
			st = new StringTokenizer(reader.readLine());
			
			for(int j = 0; j < mapSize; j++) {
				map[i][j] = st.nextToken().charAt(0);
			}
		}
		
		setO(map, 0, 0, 0);

		if(check) {
			System.out.println("NO");
		}
	}
	
	public static void setO(char[][] newMap, int startI, int startJ, int count) {
		if(count == 3) {
			//검사
			if(check(newMap)) {
				System.out.println("YES");
				check = false;
			}
			return;
		}
		
		for(int i = startI; i < mapSize; i++) {
			int tmpJ = 0;
			
			if(i == startI) {
				tmpJ = startJ;
			}
			
			for(int j = tmpJ; j < mapSize; j++) {
				if(newMap[i][j] == 'X' && check) {
					newMap[i][j] = 'O';
					count++;
					
					setO(newMap, i, j, count);
					
					newMap[i][j] = 'X';
					count--;
				}
			}
		}
	}
	
	static int[] dr = {0, -1, 0, 1};
	static int[] dc = {1, 0, -1, 0};
	public static boolean check(char[][] map) {
		for(int i = 0; i < mapSize; i++) {
			for(int j = 0; j < mapSize; j++) {
				if(map[i][j] == 'S') {
					int sI = i;
					int sJ = j;
					
					for(int k = 0; k < 4; k++) { //4방 검사
						int dCount = 1;
						
						while(sI + dCount * dr[k] >= 0 && sJ + dCount * dc[k] >= 0 &&
								sI + dCount * dr[k] < mapSize && sJ + dCount * dc[k] < mapSize) {
							if(map[sI + dCount * dr[k]][sJ + dCount * dc[k]] == 'T') {
								return false;
							} else if(map[sI + dCount * dr[k]][sJ + dCount * dc[k]] == 'O') {
								break;
							}
							dCount++;
						}
					}
				}
			}
		}
		
		return true;
	}

}
