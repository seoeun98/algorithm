package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 간단한 그래프 문제
 */

public class BJ_14725_개미굴 {
	
	static class Node implements Comparable<Node>{
		String name;
		PriorityQueue<Node> childs = new PriorityQueue<>();
		HashMap<String, Node> childsName = new HashMap<>();
		
		public Node(String name) {
			this.name = name;
		}

		@Override
		public int compareTo(Node o) {
			return this.name.compareTo(o.name);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		
		int n = Integer.parseInt(st.nextToken()); //먹이의 갯수
		
		Node root = new Node("root");
		
		for(int i = 0; i < n; i++) {
			Node r = root;
			
			st = new StringTokenizer(reader.readLine());
			
			int m = Integer.parseInt(st.nextToken());
			String childName = st.nextToken();
			
			if(!r.childsName.containsKey(childName)) {				
				Node child = new Node(childName);
				r.childsName.put(childName, child);
				r.childs.add(child);
				r = child;				
			} else {
				r = r.childsName.get(childName);
			}
			
			for(int j = 1; j < m; j++) {
				childName = st.nextToken();
				
				if(!r.childsName.containsKey(childName)) {
					Node child = new Node(childName);
					r.childs.add(child);
					r.childsName.put(childName, child);
					r = child;
				} else {
					r = r.childsName.get(childName);
				}
			}
		}
		
		while(!root.childs.isEmpty()) {
			Node childN = root.childs.poll();
			
			dfs(0, childN);
		}
	}

	public static void dfs(int depth, Node now) {
		for(int i = 0; i < depth; i++) {
			System.out.print("--");
		}
		
		System.out.println(now.name);
		
		while(!now.childs.isEmpty()) {
			Node childN = now.childs.poll();
			
			dfs(depth + 1, childN);
		}
	}
}
