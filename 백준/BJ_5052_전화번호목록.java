package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BJ_5052_전화번호목록 {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		StringBuilder sb = new StringBuilder();
		
		int testC = Integer.parseInt(st.nextToken());
		
		for(int t = 0; t < testC; t++) {
			boolean check = true;
			
			st = new StringTokenizer(reader.readLine());
			
			int n = Integer.parseInt(st.nextToken());
			
			String[] numbers = new String[n];
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(reader.readLine());
				numbers[i] = st.nextToken();
			}
			
			Arrays.sort(numbers, new Comparator<String>() {
				@Override
				public int compare(String o1, String o2) {
					return o1.length() - o2.length();
				}
				
			});
			
			HashSet<String> list = new HashSet<>();
			
			A : for(int i = 0; i < n; i++) {
				String line = numbers[i];
				
				for(int j = 0; j < line.length(); j++) {	
					String tmp = (String)line.subSequence(0, j);
					
					if(list.contains(tmp)) {
						sb.append("NO\n");
						check = false;
						break A;
					}
				}
				
				list.add(line);
			}
			
			if(check) {
				sb.append("YES\n");				
			}
		}
		
		System.out.println(sb.toString());
	}

}
