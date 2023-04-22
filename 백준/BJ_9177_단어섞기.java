package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 처음에는 dfs로 접근 --> 시간초과
 * bfs 접근
 * --> visited 배열 따로 선언x. 왜? 뒷 순서 알파벳만 볼테니까 굳이 필요 없다고 생각함 --> 메모리 초과
 * 
 * visited 배열이 왜 필요한가
 * 1
 * ttt tt ttttt
 * 를 입력했다고 가정해보자
 * 
 * 큐에 들어가는 순서를 살펴보면
 * f	s
 * 0	0
 * 1	0
 * 0	1
 * 2	0
 * 1	1 <--
 * 1	1 <-- 중복!
 * 0	2
 * 3	0
 * 2	1 <--
 * 2	1 <-- 중복!
 * 
 * 이런 식으로 que에 자꾸 중복 데이터가 들어가게 됨. 이를 방지하기 위해서 visited 선언 필요
 * 
 */

public class BJ_9177_단어섞기 {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		StringBuilder sb = new StringBuilder();
		Queue<int[]> que = new LinkedList<>();
		
		int n = Integer.parseInt(st.nextToken());
		
		char[] first;
		char[] second;
		char[] goal;
		
		for(int i = 1; i <= n; i++) {
			st = new StringTokenizer(reader.readLine());
			
			first = st.nextToken().toCharArray();
			second = st.nextToken().toCharArray();
			goal = st.nextToken().toCharArray();
			
			que.add(new int[] {0, 0, 0});
			boolean[][] visited = new boolean[first.length + 1][second.length + 1];
			visited[0][0] = true;
			
			boolean check = false;
			while(!que.isEmpty()) {
				int[] now = que.poll();
				int f = now[0]; //첫 번째 문자열의 인덱스
				int s = now[1]; //두 번째 문자열의 인덱스
				int g = now[2]; //목표 문자열의 인덱스
				
				//목표 문자열 끝까지 다 완성되었다면? 만들 수 있다는 얘기. que 탈출
				if(g == goal.length) {
					check = true;
					que.clear();
					break;
				}
				
				/*
				 * 비교 대상의 인덱스 문자와 목표의 인덱스 문자가 같다면 다음 문자 비교할 필요가 있는 거
				 * 다르면? 벌써 다른데 다음 거 비교할 이유가 없지
				 * 
				 * 다음 거 비교하려고 하는데 이미 비교한 전적이 있다? 그럼 또 비교할 이유가 없지
				 */
				if(f < first.length && first[f] == goal[g] && !visited[f + 1][s]) {
					visited[f + 1][s] = true;
					que.add(new int[] {f + 1, s, g + 1});
				}
				
				if(s < second.length && second[s] == goal[g] && !visited[f][s + 1]) {
					visited[f][s + 1] = true;
					que.add(new int[] {f, s + 1, g + 1});
				}
			}
			
			sb.append("Data set " + i + ": ");
			if(check) {
				sb.append("yes\n");
			} else {
				sb.append("no\n");
			}
			
		}
		
		System.out.println(sb.toString());
	}

}
