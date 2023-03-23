package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_1516_게임개발 {


	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		
		int n = Integer.parseInt(st.nextToken()); //건물 종류 수
		
		int[] indegree = new int[n]; //이전에 지어야하는 건물 수
		int[] buildT = new int[n]; //각 건물별 건축 소요 시간
		int[] prevB = new int[n]; //해당 건물 이전에 지어야하는 건물들의 최장 건축 시간
		
		ArrayList<Integer>[] next = new ArrayList[n]; //다음에 지을 수 있는 건물
		
		for(int i = 0; i < n; i++) {
			next[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < n; i++) {
			int index = 0;
						
			st = new StringTokenizer(reader.readLine());
			
			int prev;
			while((prev = Integer.parseInt(st.nextToken())) != -1) {
				if(index == 0) {
					buildT[i] = prev;
					index++;
				} else {
					next[prev - 1].add(i); //prev 건물을 지은 후 i번쨰 건물을 지어야 함
					indegree[i]++; //prev 건물을 짓기 전에 지어야하는 건물 수를 증가
				}
			}
		}
		
		Queue<Integer> que = new LinkedList<>();
		for(int i = 0; i < n; i++) {
			if(indegree[i] == 0) { //이전에 지어야하는 건물 수 가 없다면 == 바로 지을 수 있다면
				que.add(i);
			}
		}
		
		while(!que.isEmpty()) {
			int now = que.poll();
			
			for(int i : next[now]) {
				prevB[i] = Math.max(prevB[i], prevB[now] + buildT[now]);
				indegree[i]--;
				
				if(indegree[i] == 0) {
					que.add(i);
				}
			}
		}
		
		for(int i = 0; i < n; i++) {
			System.out.println(prevB[i] + buildT[i]);
		}
		
		
	}
	
	
	
	/*
	 * (**)
	 * C 건물을 짓기 위해서는 A, B 건물을 먼저 지어야 한다고 가정하자.
	 * A 건물은 10시간, B 건물은 15시간이 소요된다. 이 때, 건물을 동시에 건설할 수 있으므로 C 건물을 짓기 전에 필요한 건축 시간은 최소 15시간이다.
	 * 
	 * 현재 지어야하는 건물을 now, 현재 건물을 짓고 난 뒤 지을 수 있는 건물을 next, 현재 짓고 싶은 건물 이전에 지어야 하는 건물을 prev라고 하자.
	 * buildT는 now의 건축 소요 시간 배열, prevT는 now를 짓기 전에 필수적으로 필요한 건물들의 최소 건축 소요 시간이다.
	 * 
	 * prevT[next] (다음 건물을 짓기 전에 지어져야하는 건물들의 최소 건축 소요 시간) = Math.max(prevT[next], prevT[now] + buildT[now)
	 * 다음 건물을 짓기 위해서는 현재 건물을 먼저 지어야한다. 근데 다음 건물에 필요한 건물이 현재 건물 뿐일까? 그건 모르는 일이다. 다음 건물의 선행 조건이 건물 20개일수도 있잖아?
	 * 그러니까 (현재 건물을 지었을 경우의 소요 시간)vs(다음 건물에 필요한 다른 이전 건물의 최대 소요 시간)을 비교하여 더 오래 걸리는 시간으로 갱신해준다. 왜? 동시에 건설할 수 있으니까. -> **
	 * 
	 * prevT 배열은 현재 건물을 짓기 전까지 필요한 '이전' 건물의 건축 소요 시간이므로 결과는 prevT에 buildT를 더해주어야 한다.
	 * 
	 */

}
