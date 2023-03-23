package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_1939_중량제한 {
	
	static class Bridge {
		int destinationI;
		int BridgeW;
		
		public Bridge(int destinationI, int bridgeW) {
			this.destinationI = destinationI;
			BridgeW = bridgeW;
		}
	}

	static int n, m;
	static int[] weights;
	static ArrayList<Bridge>[] map;
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		n = Integer.parseInt(st.nextToken()); //섬의 수
		m = Integer.parseInt(st.nextToken()); //다리 수
		
		map = new ArrayList[n];
		for(int i = 0; i < n; i++) {
			map[i] = new ArrayList<>();
		}
		
		weights = new int[m]; //다리의 중량제한 배열
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(bf.readLine());
			int depI = Integer.parseInt(st.nextToken()) - 1; //출발 섬
			int desI = Integer.parseInt(st.nextToken()) - 1; //도착 섬
			int w = Integer.parseInt(st.nextToken()); //중량 제한
			
			weights[i] = w;
			map[depI].add(new Bridge(desI, w));
			map[desI].add(new Bridge(depI, w));
		}
		
		Arrays.sort(weights); //오름차순 정렬
		
		int start = 0;
		int end = m;
		
		st = new StringTokenizer(bf.readLine());
		int startI = Integer.parseInt(st.nextToken()) - 1; //출발섬
		int endI = Integer.parseInt(st.nextToken()) - 1; //도착섬
		
		int max = -1;
		
		//무게 찾기
		while(start < end) {
			int middle = (start + end) / 2;
			
			boolean enable = checkW(startI, endI, weights[middle]);
			
			if(enable) { //2-1
				if(max < weights[middle]) {
					max = weights[middle];
				}
				
				start = middle + 1;
			} else { //2-2
				end = middle;
			}
		}
		
		System.out.println(max);
		
	}
	
	/*
	 * 1. 실을 무게 w를 정한다.
	 * 2. w로 갈 수 있는지 bfs 탐색을 이용하여 확인한다. (start < end 일 때까지 반복)
	 * 2-1. 갈 수 있다면, 최댓값 갱신하고 start를 (middle + 1)로 바꾸어 다시 탐색 -> 1
	 * 2-2. 갈 수 없다면, end를 middle로 바꾸어 다시 탐색 -> 1
	 * 3. 최댓값 출력
	 */
	
	//2번 과정 수행 함수
	public static boolean checkW(int startI, int endI, int weight) {
		Queue<int[]> que = new LinkedList<>();
		boolean[] check = new boolean[n]; //해당 섬에 방문했는지
		
		boolean enableW = false;
		
		que.add(new int[] {startI, 1000000001});
		check[startI] = true;
		
		while(!que.isEmpty()) {
			int[] now = que.poll();
			int nowI = now[0];
			int w = now[1];
			
			if(nowI == endI) {
				enableW = true;
				break;
			}
			
			for(Bridge b : map[nowI]) {
				if(nowI == startI) {
					w = Math.min(b.BridgeW, weight);
				}
				
				if(b.BridgeW >= weight && !check[b.destinationI] && w <= b.BridgeW) {
					que.add(new int[] {b.destinationI, w});
					check[b.destinationI] = true;
				}
			}
			
		}
		
		return enableW;
	}

}
