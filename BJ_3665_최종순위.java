package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_3665_최종순위 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		
		int testCase = Integer.parseInt(st.nextToken());
		
		for(int t = 0; t < testCase; t++) {
			st = new StringTokenizer(reader.readLine()); //팀의 수
			
			int n = Integer.parseInt(st.nextToken());
			
			ArrayList<Integer>[] list = new ArrayList[n]; //나보다 낮은 팀들
			int[] indegree = new int[n]; //나보다 순위가 높은 팀의 수
			Arrays.fill(indegree, n - 1);
			
			for(int i = 0; i < n; i++) {
				list[i] = new ArrayList<>();
			}
			
			int[] teams = new int[n];
			
			st = new StringTokenizer(reader.readLine()); //팀 순위 순서
			for(int i = 0; i < n; i++) {
				int team = Integer.parseInt(st.nextToken()) - 1;
				teams[i] = team;
			}
			
			for(int i = 0; i < n; i++) {
				for(int j = i + 1; j < n; j++) {
					list[teams[i]].add(teams[j]);
					indegree[teams[i]]--;
				}
			}
			
			st = new StringTokenizer(reader.readLine()); //순위 변경 수
			int changeN = Integer.parseInt(st.nextToken());
			
			for(int i = 0; i < changeN; i++) {
				st = new StringTokenizer(reader.readLine());
				int first = Integer.parseInt(st.nextToken()) - 1;
				int second = Integer.parseInt(st.nextToken()) - 1;
				
				if(list[first].contains(second)) { //첫번째에 적힌 팀이 원래는 이기고 있었다면
					int secondI = list[first].indexOf(second); 
					list[first].remove(secondI); //두 번째로 적힌 팀을 리스트(본인보다 낮은 팀)에서 지우고
					list[second].add(first); //두 번째로 적힌 팀 리스트에 첫 번째 팀을 넣는다
					indegree[first]++; //첫 번째 팀보다 높은 순위의 팀이 하나 늘었다
					indegree[second]--;
				} else { //첫 번째에 적힌 팀이 원래 지고 있었다면
					int firstI = list[second].indexOf(first);
					list[second].remove(firstI);
					list[first].add(second);
					indegree[first]--;
					indegree[second]++;
				}
//				list[first].add(second);
//				int loserI = list[loser].indexOf(winner);
//				list[loser].remove(loserI);
//				indegree[loser]++;
//				indegree[winner]--;
			}
			
			Queue<Integer> que = new LinkedList<>();
			int count0 = 0;
			for(int i = 0; i < n; i++) {
				if(indegree[teams[i]] == 0) {
					que.add(teams[i]);
					count0++;
				}
			}
			
			//순위를 비교할 수 없는 노드가 두 개 이상
			if(count0 > 1) {
				System.out.println("?");
				System.exit(0);
			}
			
			boolean[] checks = new boolean[n];
			int count = 0;
			
			String result = "";
			while(!que.isEmpty()) {
				int now = que.poll(); //현재 볼 팀
				count++;
				result += (now + 1) + " ";
				
				for(int team : list[now]) {
					indegree[team]--;
					
					if(!checks[team] && indegree[team] == 0) {
						checks[team] = true;
						que.add(team);
					}
				}
			}
			
			if(count != n) {
				System.out.println("IMPOSSIBLE");
			} else {
				System.out.println(result);
			}
		}
	}

}
