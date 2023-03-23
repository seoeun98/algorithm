package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_1504_특정한최단경로 {
	private static final int INF = 100_000_000;
	
	static class Node implements Comparable<Node> {
		int end;
		int w;
		
		public Node(int end, int w) {
			this.end = end;
			this.w = w;
		}

		@Override
		public int compareTo(Node o) {
			return this.w - o.w;
		}
	}

	static int n;
	static List<Node>[] list;
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		
		n = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[n];
		for(int i = 0; i < n; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < e; i++) {
			st = new StringTokenizer(reader.readLine());
			int start = Integer.parseInt(st.nextToken()) - 1;
			int end = Integer.parseInt(st.nextToken()) - 1;
			int w = Integer.parseInt(st.nextToken());
			
			list[start].add(new Node(end, w));
			list[end].add(new Node(start, w));
		}
		
		st = new StringTokenizer(reader.readLine());
		int firstP = Integer.parseInt(st.nextToken()) - 1;
		int secondP = Integer.parseInt(st.nextToken()) - 1;
		
		int one = 0;
		one += dij(0, firstP);
		one += dij(firstP, secondP);
		one += dij(secondP, n - 1);
		
		int two = 0;
		two += dij(0, secondP);
		two += dij(secondP, firstP);
		two += dij(firstP, n - 1);
		
		int ans = (one >= INF && two >= INF) ? -1 : Math.min(one, two);
		System.out.println(ans);

	}
	
	public static int dij(int start, int endP) {
		PriorityQueue<Node> que = new PriorityQueue<>();
		int[] distance = new int[n];
		boolean[] check = new boolean[n];
		
		Arrays.fill(distance, INF);
		que.add(new Node(start, 0));
		distance[start] = 0;
		
		while(!que.isEmpty()) {
			Node node = que.poll();
			int now = node.end;
			
			if(check[now]) continue;
			
			check[now] = true;
			
			for(Node n : list[now]) {
				if(distance[n.end] > distance[now] + n.w) {
					distance[n.end] = distance[now] + n.w;
					que.add(new Node(n.end, distance[n.end]));
				}
			}
			
		}
		
		return distance[endP];
	}

}
