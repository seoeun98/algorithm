package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 다익스트라
 * pq나 check배열 안쓸 거면 que에 넣을 때 처리 제대로 해줘야 한다 
 */

public class BJ_5972_택배배송 {
	
	public static class Node {
		int end;
		int d;
		
		public Node(int end, int d) {
			this.end = end;
			this.d = d;
		}	
	}

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		ArrayList<Node>[] list = new ArrayList[n];
		
		for(int i = 0; i < n; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(reader.readLine());
			
			int start = Integer.parseInt(st.nextToken()) - 1;
			int end = Integer.parseInt(st.nextToken()) - 1;
			int d = Integer.parseInt(st.nextToken());
			
			list[start].add(new Node(end, d));
			list[end].add(new Node(start, d));
		}
		
		System.out.println(djistra(list));
	}

	public static int djistra(ArrayList<Node>[] list) {
		int[] min = new int[list.length];
		
		Arrays.fill(min, 50000001);
		min[0] = 0;
		
		Queue<int[]> que = new LinkedList<>();
		que.add(new int[] {0, 0});
		
		while(!que.isEmpty()) {
			int[] nowL = que.poll();
			int now = nowL[0];
			int nowD = nowL[1];
			
			if(nowD > min[now]) {
				continue;
			}
						
			for(Node n : list[now]) {
				if(min[n.end] > min[now] + n.d) {
					min[n.end] = min[now] + n.d;
					
					que.add(new int[] {n.end, min[n.end]});
				}
			}
		}

		return min[list.length - 1];
	}
}
