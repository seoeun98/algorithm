package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_11404_플로이드 {

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		int INF = 10000001;
		
		int n = Integer.parseInt(st.nextToken()); //도시
		st = new StringTokenizer(bf.readLine());
		int m = Integer.parseInt(st.nextToken()); //버스
		
		int[][] map = new int[n + 1][n + 1];
		
		for(int i = 1; i <= n; i++) {
			Arrays.fill(map[i], INF);
		}
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(bf.readLine());
			int start = Integer.parseInt(st.nextToken()); //출발 도시
			int end = Integer.parseInt(st.nextToken()); //도착 도시
			int cost = Integer.parseInt(st.nextToken()); //비용
			
			if(map[start][end] > cost)
				map[start][end] = cost;
		}
		
		for(int i = 1; i <= n; i++) { //경유
			for(int j = 1; j <= n; j++) { //출발
				if(i == j) continue;
				for(int k = 1; k <= n; k++) { //도착
					if(k == i || k == j) continue;
					if(map[j][k] > map[j][i] + map[i][k]) {	
						map[j][k] = map[j][i] + map[i][k];
					}
				}
			}
		}
		
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				if(map[i][j] >= INF)
					System.out.print(0);
				else System.out.print(map[i][j]);
				
				if(j <= n - 1)
					System.out.print(" ");
			}
			System.out.println();
		}
		
	}

}
