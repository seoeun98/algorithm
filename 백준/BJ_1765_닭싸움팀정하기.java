package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 서로소로 해결
 * 
 * Queue배열 선언. 여기에 나를 적으로 두고 있는 애들을 넣는다 <-- 적끼리 서로 친구시켜주려고
 * 
 * F면 그냥 바로 묶어버리기
 * E면 que에 넣기. 적이 두 명 생기면 둘이 친구시켜주기 
 * 여기서 둘 다 poll로 꺼내면 안된다. 다음에 또 나를 적으로 두고 있는 애가 들어오면 걔도 같은 묶음에 넣어줘야하기 때문에 하나는 peek으로 봐서 다음에 적으로 들어올 애가 어디에 묶일지 남겨놔야 함
 * 
 * 
 * ex)
 * 5
 * 4
 * E 1 2
 * E 1 3
 * E 1 5
 * F 4 2
 * 
 * input이 이럴 때 2가 4 밑으로 들어가는데, 2 밑에 들어있던 애들은 부모 갱신이 안돼서 2와 3, 5가 다른 묶음 취급되어버림
 * 마지막에 부모찾기 한 번 더 해서 부모 갱신 해주고 묶음 수 세기
 * 
 */

public class BJ_1765_닭싸움팀정하기 {

	static int[] parent;
	static int n, m;
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		
		n = Integer.parseInt(st.nextToken()); //학생의 수
		Queue<Integer>[] que = new LinkedList[n];
		parent = new int[n];
		
		st = new StringTokenizer(reader.readLine());
		m = Integer.parseInt(st.nextToken()); //관계 수
		
		for(int i = 0; i < n; i++) {
			parent[i] = i;
			que[i] = new LinkedList<>();
		}
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(reader.readLine());
			
			String ForE = st.nextToken();
			int first = Integer.parseInt(st.nextToken()) - 1;
			int second = Integer.parseInt(st.nextToken()) - 1;
			
			if(ForE.equals("F") ) { //서로소 집합 합체
				union(first, second);
			} else { //서로 적
				que[first].add(second);
				
				if(que[first].size() == 2) {
					int firstE = que[first].poll();
					int secondE = que[first].peek();
					
					union(firstE, secondE);
				}
				
				que[second].add(first);
				
				if(que[second].size() == 2) {
					int firstE = que[second].poll();
					int secondE = que[second].peek();
					
					union(firstE, secondE);
				}
			}
		}
		
		for(int i = 0; i < n; i++) {
			parent[i] = findParent(i);
		}
		
		boolean[] check = new boolean[n];
		int answer = 0;
		for(int i = 0; i < n; i++) {
			if(!check[parent[i]]) {
				answer++;
				check[parent[i]] = true;
			}
		}
		
		System.out.println(answer);
	}
	
	public static void union(int a, int b) {
		int aRoot = findParent(a);
		int bRoot = findParent(b);
		
		if(aRoot != bRoot) {
			parent[bRoot] = aRoot;
		}
	}
	
	public static int findParent(int a) {
		if(parent[a] == a) {
			return a;
		}
	
		return parent[a] = findParent(parent[a]);
	}

}
