package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.StringTokenizer;

/*
 * 문제 이해가 너무 오래 걸림
 * 
 * dfs -> 시간초과
 * 현재 입력 받는 묶음의 인원 수를 출력하는 문제
 * 
 * 서로소 집합으로 접근
 */

public class BJ_4195_친구네트워크 {
	static int max = 0;
	
	static int[] parents;
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		StringBuilder sb = new StringBuilder();
		
		int test = Integer.parseInt(st.nextToken());
		
		for(int t = 0; t < test; t++) {
			max = 0;
			HashMap<String, Integer> map = new HashMap<>();
			HashMap<Integer, Integer> answers = new HashMap<>();
			
			st = new StringTokenizer(reader.readLine());
			int n = Integer.parseInt(st.nextToken());
			int countIndex = 1;
			
			parents = new int[n * 2 + 1]; //최대 n * 2개의 원소가 생길 수 있음
			for(int i = 0; i < n; i++) {
				
				st = new StringTokenizer(reader.readLine());
				
				String first = st.nextToken();
				String second = st.nextToken();
				
				int firstIndex = 0;
				int secondIndex = 0;
				if(map.containsKey(first)) {
					firstIndex = map.get(first);
				} else {
					firstIndex = countIndex;
					parents[firstIndex] = firstIndex;
					map.put(first, countIndex++);
				}
				
				if(map.containsKey(second)) {
					secondIndex = map.get(second);
				} else {
					secondIndex = countIndex;
					parents[secondIndex] = secondIndex;
					map.put(second, countIndex++);
				}
				
				union(firstIndex, secondIndex, answers);
							
				sb.append(answers.get(findParent(firstIndex)) + "\n");
			}
		}
		System.out.println(sb.toString());
	}
	
	public static int findParent(int index) {
		if(parents[index] == index) {
			return index;
		}
		
		return parents[index] = findParent(parents[index]);
	}
	
	public static void union(int a, int b, HashMap<Integer, Integer> answers) {
		int aRoot = findParent(a);
		int bRoot = findParent(b);
		
		//이미 같아서 합칠 필요가 없음
		if(aRoot == bRoot) {
			return;
		}
		
		int num;
		//달라서 합치는 경우
		if(!answers.containsKey(bRoot)) {
			num = 1;
		} else {
			num = answers.get(findParent(bRoot));
		}
		parents[bRoot] = aRoot;
		
		if(!answers.containsKey(aRoot)) {
			answers.put(aRoot, 1);
		}
		
		answers.put(aRoot, answers.get(aRoot) + num);
	}

}
