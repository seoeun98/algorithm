package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_2252_줄세우기 {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		
		int n = Integer.parseInt(st.nextToken()); //학생 수
		int m = Integer.parseInt(st.nextToken()); //키 비교 수
		
		int[] indegree = new int[n];
		ArrayList<Integer>[] students = new ArrayList[n];
		
		for(int i = 0; i < n; i++) {
			students[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(reader.readLine());
			int start = Integer.parseInt(st.nextToken()) - 1; //작은 애
			int end = Integer.parseInt(st.nextToken()) - 1; //큰 애
			
			students[start].add(end);
			indegree[end]++;
		}
		
		Queue<Integer> que = new LinkedList<>();
		int[] result = new int[n]; //답 배열
		int resultIndex = 0;
		
		for(int i = 0; i < n; i++) {
			if(indegree[i] == 0) {
				que.add(i);
				result[resultIndex++] = i;
			}
		}
		
		
		while(!que.isEmpty()) {
			int now = que.poll();
			
			for(int bigger : students[now]) {
				indegree[bigger]--;
				
				if(indegree[bigger] == 0) {
					que.add(bigger);
					result[resultIndex++] = bigger;
				}
			}
		}
		
		for(int i : result) {
			System.out.print((i + 1) + " ");
		}
	}

}
