package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

/*
 * 처음에는 생각없이 이중반복문 돌면서 모든 합을 set에 넣고, 다시 반복문 돌면서 set에 있으면 ok 처리 했음
 * 2
 * 0 0
 * 
 * 3
 * 0 0 0
 * 
 * 3
 * 0 3 3
 * 
 * 이런 반례들 처리 필요
 * 
 * 0은 3개 이상 있어야 0도 ok 처리가 되고
 * 합에 하나라도 0이 있는 경우에는 0이 아닌 수 A가 2개 이상은 있어야 A를 ok 처리 할 수 있음
 * 
 * 처리를 위해 각 숫자의 갯수를 저장하는 Map 생성
 */

public class BJ_1253_좋다 {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		
		HashSet<Integer> set = new HashSet<>();
		HashMap<Integer, Integer> map = new HashMap<>();
		
		int n = Integer.parseInt(st.nextToken());
		int[] ns = new int[n];
		
		st = new StringTokenizer(reader.readLine());
		for(int i = 0; i < n; i++) {
			ns[i] = Integer.parseInt(st.nextToken());
			
			if(map.containsKey(ns[i])) {
				map.put(ns[i], map.get(ns[i]) + 1);
			} else {
				map.put(ns[i], 1);
			}
		}
		
		Arrays.sort(ns);
		
		for(int i = 0; i < n; i++) {
			for(int j = i + 1; j < n; j++) {
				if(ns[i] == 0 && ns[j] == 0 && map.get(ns[i]) < 3)
					continue;
				if((ns[i] == 0 && map.get(ns[j]) < 2) 
						|| (ns[j] == 0 && map.get(ns[i]) < 2))
					continue;
				set.add(ns[i] + ns[j]);
			}
		}
		
		int i = 0;
		int count = 0;
		while(i < ns.length) {
			int n1 = ns[i++];
			
			if(set.contains(n1))
				count++;
		}
		
		System.out.println(count);
	}

}
