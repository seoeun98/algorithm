package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_1753_최단경로 {
	
	private static final int INF = 100_000_000;
	
	static class Node implements Comparable<Node> {
		int weight;
		int destination;
		
		public Node(int weight, int destination) {
			super();
			this.weight = weight;
			this.destination = destination;
		}

		//오름차순
		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int v = Integer.parseInt(st.nextToken()); //정점 개수
		int e = Integer.parseInt(st.nextToken()); //간선 개수
		
//		int[][] matrix = new int[v][v]; //정점별 거리
		List<Node>[] list = new ArrayList[v];
		
		st = new StringTokenizer(br.readLine());
		int startV = Integer.parseInt(st.nextToken()) - 1; //출발 정점
		
		for(int i = 0; i < v; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken()) - 1;
			int end = Integer.parseInt(st.nextToken()) - 1;
			int distance = Integer.parseInt(st.nextToken());
			
//			matrix[start - 1][end - 1] = distance;
			list[start].add(new Node(distance, end));
		}
		
		PriorityQueue<Node> que = new PriorityQueue<>();
		que.add(new Node(startV, 0));
		
		boolean[] check = new boolean[v]; //방문 여부 체크 배열
		int[] distance = new int[v]; //정점별 최소 거리
		
		Arrays.fill(distance, INF);
		distance[startV] = 0;
		
		while(!que.isEmpty()) {
			//제일 가까운 정점
			Node n = que.poll();
			
			int now = n.destination;
			
			//방문했던 정점이면 최소거리 확정되어있는 상태이므로 건너뛰기
			if(check[now]) continue;
			check[now] = true;
						
			// 방문한 정점에서 갈 수 있는 정점들의 거리 최솟값 갱신
			for(Node node : list[now]) {
				if(distance[node.destination] > distance[now] + node.weight) {
					distance[node.destination] = distance[now] + node.weight;
//					que.add(node);
					que.add(new Node(distance[node.destination], node.destination));
				}
			}
		}
		
		for(int i = 0; i < v; i++) {
			if(distance[i] == INF) {
				System.out.print("INF");
			}
			else {
				System.out.print(distance[i]);
			}
			
			if(i < v - 1)
				System.out.println();
		}
	}

}
