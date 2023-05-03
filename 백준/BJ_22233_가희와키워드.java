package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/*
 * 그냥 해쉬맵
 */
public class BJ_22233_가희와키워드 {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		HashMap<String, Integer> map = new HashMap<>();
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(reader.readLine());
			
			map.put(st.nextToken(), 0); //0 == 지워진 적이 없다, 1 == 지워진 적이 있다
		}
		
		int keywordN = map.size();
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(reader.readLine(), ",");
			
			while(st.hasMoreTokens()) {
				String word = st.nextToken();
				
				if(map.containsKey(word) && map.get(word) == 0) {
					keywordN--;
					map.put(word, 1);
				}
			}
			
			sb.append(keywordN + "\n");
		}
		
		System.out.println(sb.toString());
	}

}
