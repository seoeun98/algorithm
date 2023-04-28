package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 나는 플로이드 워셜 알고리즘 사용
 * 정점 갯수가 최대 500이므로 500*500*500 해도 시간 초과 안뜸
 * 각 정점까지의 최소 거리 구함
 * 음의 사이클 있는지를 확인해야 하므로
 * [0][0] 혹은 [0][n]&&[n][n] 값이 음수인지를 확인
 * -->[0][0] : 시작점에서 시작점으로 오는 사이클이 음수인지
 * -->[0][n] && [n][n] : 시작점에서 특정 정점으로 진입한 뒤, 해당 정점에서 음의 사이클이 있는지를 확인
 * 
 * 만약 음의 사이클이 있다면 바로 -1 출력
 * 
 * 다른 사람들은 밸만 포드 알고리즘 사용함
 */

public class BJ_11657_타임머신 {
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		long MAX = 2100000000;
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		long[][] distances = new long[n][n];
		
		for(int i = 0; i < n; i++) {
			Arrays.fill(distances[i], MAX);
		}
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(reader.readLine());
			
			int first = Integer.parseInt(st.nextToken()) - 1;
			int second = Integer.parseInt(st.nextToken()) - 1;
			int distance = Integer.parseInt(st.nextToken());
			
			if(distances[first][second] > distance)
				distances[first][second] = distance;
		}
		
		for(int k = 0; k < n; k++) { //경유지
			for(int i = 0; i < n; i++) { //출발지
				if(i == k) continue;

				for(int j = 0; j < n; j++) { //도착지
					if(j == k) continue;
					
					if(distances[i][k] == MAX || distances[k][j] == MAX) continue;
					
					if(distances[i][j] > distances[i][k] + distances[k][j]) {
						distances[i][j] = distances[i][k] + distances[k][j];
					}
				}
			}
		}
		
		for(int i = 0; i < n; i++) {
			if(distances[0][0] < 0 || (distances[0][i] != MAX && distances[i][i] < 0)) {
				System.out.println(-1);
				System.exit(0);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i < n; i++) {
			sb.append(distances[0][i] < MAX ? distances[0][i] : -1);
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}

}
