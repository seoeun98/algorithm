package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 이진탐색 + 다익스트라
 * 
 * 이진탐색으로 지불해야 하는 최대값 A을 구함
 * 
 * 다익스트라를 이용해서 N지점까지 잇는데 필요한 A보다 비싼 최소 간선의 갯수 B를 구함
 * 
 * 만약에 B가 k 이하이다? 그러면 A를 내고 N지점까지 연결할 수 있다는 것
 * B가 k를 넘는다면 A를 내고는 N지점까지 연결 못함(더 비싸게 내야 함)
 * 
 * 이진탐색 start, end값 조정해서 재탐색
 * 
 */
public class BJ_1800_인터넷설치 {
	
	public static class Node {
		int destination;
		int cost;
		
		public Node(int destination, int cost) {
			this.destination = destination;
			this.cost = cost;
		}
	}

	static int n;
	static ArrayList<Node>[] list;
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		
		n = Integer.parseInt(st.nextToken()); //노드 수
		int p = Integer.parseInt(st.nextToken()); //간선 수
		int k = Integer.parseInt(st.nextToken()); //무료 연결 수
		
		list = new ArrayList[n];
		
		for(int i = 0; i < n; i++) {
			list[i] = new ArrayList<>();
		}
		
		int maxCost = 0;
		for(int i = 0; i < p ; i++) {
			st = new StringTokenizer(reader.readLine());
			
			int start = Integer.parseInt(st.nextToken()) - 1;
			int end = Integer.parseInt(st.nextToken()) - 1;
			int cost = Integer.parseInt(st.nextToken());
			
			if(cost > maxCost) {
				maxCost = cost;
			}
			
			list[start].add(new Node(end, cost));
			list[end].add(new Node(start, cost));
		}
		
		int minCost = 0;
		
		int answer = 10000001;
		while(minCost <= maxCost) {
			int mid = (minCost + maxCost) / 2;
			
			if(canReach(mid, k)) {
				maxCost = mid - 1;

				if(answer > mid) {
					answer = mid;
				}
				
			} else {
				minCost = mid + 1;
			}
		}
		
		System.out.println((answer == 10000001 ? -1 : answer));
		
	}
	
	public static boolean canReach(int cost, int count) {
		boolean[] visited = new boolean[n];
		int[] costs = new int[n];
		Arrays.fill(costs, Integer.MAX_VALUE);
		costs[0] = 0;
		
		for(int i = 0; i < n; i++) {
			int index = 0;
			int minCost = Integer.MAX_VALUE;
			
			for(int j = 0; j < n; j++) {
				if(costs[j] < minCost && !visited[j]) {
					index = j;
					minCost = costs[j];
				}
			}
			
			visited[index] = true;
			
			for(Node n : list[index]) {
				int tmp = n.cost > cost ? 1 : 0;
				if(costs[n.destination] > costs[index] + tmp && !visited[n.destination]) {
					costs[n.destination] = costs[index] + tmp;
				}
			}
			
		}
		
		if(costs[n - 1] <= count)
			return true;
		return false;
	}

}
