package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_16918_봄버맨 {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		
		int r = Integer.parseInt(st.nextToken()); //격자판 세로
		int c = Integer.parseInt(st.nextToken()); //격자판 가로
		int n = Integer.parseInt(st.nextToken()); //지나야 하는 시간
		
		int[][] map = new int[r][c];
		
		for(int i = 0; i < r; i++) {
			st = new StringTokenizer(reader.readLine());
			String line = st.nextToken();
			
			for(int j = 0; j < c; j++) {
				if(line.charAt(j) == 'O') { //폭탄이 있는 곳이면
					//3초 후, 칸의 값이 1이 되면 터진다는 뜻
					//후에 1초동안은 아무것도 안하니까 그거까지 감안해서 3으로. 폭탄을 처음 심었을 때는 4
					map[i][j] = 3; 
				}
			}
		}
		
		int[] dr = {0, 0, -1, 1};
		int[] dc = {1, -1, 0, 0};
		
		int plant = 1;
		
		for(int time = 2; time <= n; time++) { 
			for(int i = 0; i < r; i++) {
				for(int j = 0; j < c; j++) {
					if(map[i][j] == 0 && plant == 1) {
						map[i][j] = 4;
						continue;
					}
					if(2 <= map[i][j] && map[i][j] <= 4){
						map[i][j]--;
					}
					
					if(map[i][j] == 1) {
						map[i][j] = 0;
						
						for(int k = 0; k < 4; k++) {
							int togoR = i + dr[k];
							int togoC = j + dc[k];
							
							if(togoR >= 0 && togoR < r && togoC >= 0 && togoC < c && map[togoR][togoC] > 2) {
								map[togoR][togoC] = 0;
							}
						}
					}
					
					
				}
			}
			
			plant *= -1;
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < r; i++) {
			for(int j = 0; j < c; j++) {
				if(map[i][j] == 0) {
					sb.append('.');
				} else {
					sb.append('O');
				}
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}

}
