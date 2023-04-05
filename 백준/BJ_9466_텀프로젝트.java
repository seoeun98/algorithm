package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* 
 * 첫 바퀴 돌면서 visited 처리, finished면 더 깊게 들어가지마
 * 왔는데 재방문이야? 그러면 사이클에 해당 원소가 포함되어있다는 거. answer++
 * 두번째 바퀴 돌면서 finished 처리
 */

public class BJ_9466_텀프로젝트 {
	static boolean[] visited;
	static boolean[] finished;
	static int count;
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		
		int testCase = Integer.parseInt(st.nextToken());
		
		for(int test = 0; test < testCase; test++) {
			st = new StringTokenizer(reader.readLine());
			int n = Integer.parseInt(st.nextToken());
			int[] pick = new int[n];
					
			count = 0;
			visited = new boolean[n];
			finished = new boolean[n];
			
			st = new StringTokenizer(reader.readLine());	
			for(int i = 0; i < n; i++) {
				pick[i] = Integer.parseInt(st.nextToken()) - 1;
			}
			
			for(int i = 0; i < n; i++) {
				dfs(i, pick);
			}
			
			System.out.println(n - count);
		}

	}
	
	public static void dfs(int now, int[] next) {
		if(finished[now]) {
			return;
		}
		
		if(visited[now]) {
			count++;
			finished[now] = true;
		}
		
		visited[now] = true;
		dfs(next[now], next);
		visited[now] = false;
		
		finished[now]  = true;
	}
}
