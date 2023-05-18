package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * 다익스트라 문제
 * 
 * 역주행이 불가능하므로 지름길을 이용했을 때 도착 지점을 넘거나, 지름길이 아닐 경우는 스킵
 * 
 * 1. distance 배열(각 지점까지의 최소 거리 저장 배열)은 i로 초기화
 *  --> 지름길을 사용하지 않는다면 i번째 정점까지의 거리는 당연히 i니까
 *  
 *  2. 첫 번째 정점(0)부터 끝까지 돌면서 i번째 정점에서 이용할 수 있는 지름길이 있는지,
 * 	   해당 지름길을 이용할 경우 더 빠르다면 최소 거리 갱신
 * 
 *  3. 2번까지만 할 경우, 특정 지름길을 이용하지 않을 때가 더 빠른 경우나 연결된 지름길이 하나도 없는 경우는 고려하지 못한다.
 *     따라서 (j)=> (i + 1) 번째 정점부터 끝까지 돌면서 i번째 정점에서 j번째 정점까지 그냥 갔을 때의 최소 거리를 다시 갱신한다.
 */
public class BJ_1446_지름길 {
	
	static class Road{
		int end;
		int distance;
		
		public Road(int end, int distance) {
			this.end = end;
			this.distance = distance;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int goal = Integer.parseInt(st.nextToken());
		
		ArrayList<Road>[] roads = new ArrayList[10001];
		for(int i = 0; i < 10001; i++) {
			roads[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(reader.readLine());
			
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			if(end - start <= d) continue; //지름길이 그냥 가는 것보다 더 오래 걸리면
			if(end > goal) continue; //지름길을 통해서 갔을 때 목적지를 넘겨버리면
			
			roads[start].add(new Road(end, d));
		}
		
		System.out.println(djikstra(roads, goal));
		
	}
	
	public static int djikstra(ArrayList<Road>[] roads, int goal) {
		int[] distances = new int[goal + 1];
		for(int i = 0; i < goal + 1; i++) {
			distances[i] = i;
		}
		
		for(int i = 0; i < goal + 1; i++) {
			for(Road r : roads[i]) {
				int end = r.end;
				int distance = r.distance;
				
				if(distances[end] > distances[i] + distance) {
					distances[end] = distances[i] + distance;
				}
			}
			
			for(int j = i + 1; j < goal + 1; j++) {
				if(distances[j] > distances[i] + j - i) {
					distances[j] = distances[i] + j - i;
				}
			}
		}
		
		return distances[goal];
	}

}
