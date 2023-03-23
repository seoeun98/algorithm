package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 다익스트라 <-- 틀림
 * 
 * 행성 갯수가 최대 10개이므로 플로이드 워셜 돌려도 시간 내에 탐색 가능
 * 플로이드 워셜로 각 정점까지의 최소 비용 구한 뒤에 백트래킹으로 최솟값 구하기
 * 
 */


public class BJ_17182_우주탐사선 {

	static int max = 2147483647;
	static boolean[] visited;
	static int answer = Integer.MAX_VALUE, n;
	static int[][] distance;
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		
		n = Integer.parseInt(st.nextToken()); //행성 수
		int start = Integer.parseInt(st.nextToken()); //시작 행성 번호
		distance = new int[n][n];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(reader.readLine());
			for(int j = 0; j < n; j++) {
				distance[i][j] = Integer.parseInt(st.nextToken());
			}
		}
//		
		for(int k = 0; k < n; k++) { //경유지
			for(int i = 0; i < n; i++) { //출발지
				if(k == i) continue;
				
				for(int j = 0; j < n; j++) { //도착지
					if(k == j || k == i) continue;
					
					if(distance[i][j] > distance[i][k] + distance[k][j]) {
						distance[i][j] = distance[i][k] + distance[k][j];
					}
				}
			}
		}
		
		visited = new boolean[n]; //행성 방문 여부 표시
		visited[start] = true;
		
		function(start, 0, 0);
		
//		visited[start] = true; //시작 행성 방문 체크
//		int count = 1;
//		while(count++ != n) {
//			start = dijkstra(start);
//			
//			visited[start] = true;
//		}
//		
		System.out.println(answer);
		
	}
	
	/**
	 * @param index 현재 내가 있는 행성의 인덱스
	 */
	public static void function(int index, int count, int price) {
		if(count == n - 1) {
			if(price < answer) {
				answer = price;
			}
			return;
		}
		
		for(int i = 0; i < n; i++) {
			if(!visited[i] && index != i) { //방문한 적이 없고, 스스로가 아니라면
				if(price + distance[index][i] > answer) continue;
				visited[i] = true;
				function(i, count + 1, price + distance[index][i]);
				visited[i] = false;
			}
		}
	}
	
//	public static int dijkstra(int start) {
//		int[] dis = new int[n];
//		boolean[] check = new boolean[n];
//		
//		Arrays.fill(dis, max);
//		dis[start] = 0;
//		
//		for(int i = 0; i < n; i++) {
//			int index = start;
//			int min = max;
//			for(int j = 0; j < n; j++) {
//				if(min > dis[j] && !check[j]) {
//					index = j;
//					min = dis[j];
//				}
//			}
//				
//			check[index] = true;
//				
//			for(int j = 0; j < n; j++) {
//				if(!check[j] && distance[index][j] != max && dis[j] > dis[index] + distance[index][j]) {
//					dis[j] = dis[index] + distance[index][j];
//				}
//			}
//		}
//		
//		int min = 214748364;
//		int resultI = start;
//		for(int i = 0; i < n; i++) {
//			if(dis[i] < min && !visited[i]) {
//				min = dis[i];
//				resultI = i;
//			}
//		}
//		answer += min;
//		return resultI;
//	}
}
