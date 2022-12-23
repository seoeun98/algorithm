package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_2458_키순서 {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		
		int INF = 1000000000;
		
		int n = Integer.parseInt(st.nextToken()); //학생 수
		int m = Integer.parseInt(st.nextToken()); //키 표현
		
		int[][] smaller = new int[n][n];
		int[][] bigger = new int[n][n];
		
		for(int i = 0; i < n; i++) {
			Arrays.fill(smaller[i], INF);
			Arrays.fill(bigger[i], INF);
		}
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(reader.readLine());
			
			int small = Integer.parseInt(st.nextToken()) - 1;
			int big = Integer.parseInt(st.nextToken()) - 1;
			
			smaller[small][big] = 1;
			bigger[big][small] = 1;
		}
		
		for(int k = 0; k < n; k++) { //경유지
			for(int i = 0; i < n; i++) { //출발지
				if(k == i) continue;
				for(int j = 0; j < n; j++) { //도착지
					if(j == k || j == i) continue;
					
					if(smaller[i][j] == 1 || (smaller[i][k] == 1 && smaller[k][j] == 1))
						smaller[i][j] = 1;
				}
			}
		}
		
		for(int k = 0; k < n; k++) { //경유지
			for(int i = 0; i < n; i++) { //출발지
				if(k == i) continue;
				for(int j = 0; j < n; j++) { //도착지
					if(j == k || j == i) continue;
					
					if(bigger[i][j] == 1 || (bigger[i][k] == 1 && bigger[k][j] == 1))
						bigger[i][j] = 1;
				}
			}
		}
		
		int answer = 0;
		
		for(int i = 0; i < n; i++) {
			int count = 0;
			
			for(int j = 0; j < n; j++) {
				if(bigger[i][j] == 1)
					count++;
				if(smaller[i][j] == 1)
					count++;
			}
			
			if(count == n - 1) {
				answer++;
			}
		}
		
		System.out.println(answer);
	}

}
