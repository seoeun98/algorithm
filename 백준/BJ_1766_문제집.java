package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

import javax.management.Query;

/*
 * 위상 정렬 문제
 * 번호가 빠른 순서대로 우선순위
 * 
 * 선순위 문제가 없다면 우선순위 큐에 넣고, 제일 앞의 문제 풀고
 * 해당 문제를 풂으로써 시도할 수 있는 새로운 문제가 있는지(선순위 문제의 수가 0인 문제) 검사해서 있다면 우선순위 큐에 또 넣기
 */

public class BJ_1766_문제집 {

	static class Problem implements Comparable<Problem> {
		int num;
		ArrayList<Integer> next = new ArrayList<>(); //이후에 풀 수 있는 문제들
		
		public Problem(int num) {
			this.num = num;
		}
		
		@Override
		public int compareTo(Problem o) {
			return this.num - o.num;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		
		int n = Integer.parseInt(st.nextToken()); //문제 수
		int m = Integer.parseInt(st.nextToken()); //선순위 쌍의 수
		
		int[] prev = new int[n + 1]; //각 문제에 대한 선순위 문제의 수. 1번부터 시작
		Problem[] probs = new Problem[n + 1]; //각 문제 객체를 인덱스로 찾기 위한 배열
		
		for(int i = 1; i <= n; i++) {
			probs[i] = new Problem(i);
		}
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(reader.readLine());
			
			int first = Integer.parseInt(st.nextToken());
			int second = Integer.parseInt(st.nextToken());
			
			prev[second]++;
			probs[first].next.add(second);
		}
		
		PriorityQueue<Problem> solved = new PriorityQueue<>(); //선순위 문제가 없는 문제 Queue
		for(int i = 1; i <= n; i++) {
			if(prev[i] == 0) {
				solved.add(probs[i]);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		while(!solved.isEmpty()) {
			Problem now = solved.poll();
			
			sb.append(now.num + " ");
			
			for(int next : now.next) {
				prev[next]--;
				
				if(prev[next] == 0) {
					solved.add(probs[next]);
				}
			}
		}
		
		System.out.println(sb.toString());
		
	}
}
