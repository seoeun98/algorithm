package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 그래프 그려서 사이클이 있는 애들이면 집합 일치
 */

public class BJ_2668_숫자고르기 {
	static class Node {
		int num;
		Node next;
		
		public Node(int num) {
			this.num = num;
		}

		public Node(int num, Node next) {
			this.num = num;
			this.next = next;
		}
	}
	
	static ArrayList<Integer> intList = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		
		Node[] list = new Node[n + 1];
		for(int i = 1; i <= n; i++) {
			list[i] = new Node(i);
		}
		
		for(int i = 1; i <= n; i++) {
			st = new StringTokenizer(reader.readLine());
			int num = Integer.parseInt(st.nextToken());
			
			Node nowN = list[i];
			nowN.next = list[num];
		}
		
		boolean[] check = new boolean[n + 1];
		for(int i = 1; i <= n; i++) {
			Node now = list[i];
			
			dfs(now, check);
		}
		
		int[] answer = new int[intList.size()];
		int index = 0;
		for(int a : intList) {
			answer[index++] = a;
		}
		
		Arrays.sort(answer);
		System.out.println(answer.length);
		for(int i = 0; i < answer.length; i++) {
			System.out.println(answer[i]);
		}
		
	}
	
	public static void dfs(Node now, boolean[] check) {
		if(check[now.next.num]) {
			if(!intList.contains(now.num))
				intList.add(now.num);
			return;
		}
		
		check[now.num] = true;
		dfs(now.next, check);
		check[now.num] = false;
	}

}
