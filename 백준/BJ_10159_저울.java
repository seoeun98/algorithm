package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BJ_10159_저울 {

	static LinkedList<Integer>[] heavierList;
	static LinkedList<Integer>[] smallerList;
	static int MAX = 100000000, n;
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		
		n = Integer.parseInt(st.nextToken()); //물건 개수
		st = new StringTokenizer(reader.readLine());
		int m = Integer.parseInt(st.nextToken()); //비교 수
		
		heavierList = new LinkedList[n];
		smallerList = new LinkedList[n];
		for(int i = 0; i < n; i++) {
			heavierList[i] = new LinkedList<>();
			smallerList[i] = new LinkedList<>();
		}
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(reader.readLine());
			
			int heavier = Integer.parseInt(st.nextToken()) - 1;
			int lighter = Integer.parseInt(st.nextToken()) - 1;
			
			heavierList[heavier].add(lighter);
			smallerList[lighter].add(heavier);
		}
		
		for(int i = 0; i < n; i++) {
			System.out.println(n - prim(i, true) - prim(i, false) + 1);
		}
	}
	
	public static int prim(int start, boolean isBig) {
		LinkedList<Integer>[] list;
		
		if(isBig) {
			list = heavierList;
		} else {
			list = smallerList;
		}
		
		int result = 0;
		
		int[] distance = new int[n];
		boolean[] check = new boolean[n];
		
		Arrays.fill(distance, MAX);
		distance[start] = 0;
	
		for(int i = 0; i < n; i++) {
			int index = start;
			int smaller = MAX;
			
			for(int j = 0; j < n; j++) {
				if(distance[j] < smaller && !check[j]) {
					smaller = distance[j];
					index = j;
				}
			}
			
			check[index] = true;
			
			for(int j = 0; j < n; j++) {
				if(list[index].contains(j) && !check[j] && distance[j] > distance[index] + 1) {
					distance[j] = distance[index] + 1;
				}
			}
		}
		
		for(int i = 0; i < n; i++) {
			if(distance[i] < MAX) {
				result++;
			}
		}
		
		return result;
	}

}
