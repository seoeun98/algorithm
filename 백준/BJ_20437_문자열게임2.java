package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_20437_문자열게임2 {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		
		int testCase = Integer.parseInt(st.nextToken());
		
		StringBuilder sb = new StringBuilder();
		
		for(int t = 0; t < testCase; t++) {
			String line = reader.readLine();
			int k = Integer.parseInt(reader.readLine()); //만족해야 하는 알파벳 수
			
			if(k == 1) {
				sb.append("1 1\n");
				continue;
			}
			
			int[] alpha = new int[26]; //알파벳별 출현 횟수
			
			for(int i = 0; i < line.length(); i++) {
				alpha[line.charAt(i) - 'a']++;
			}
			
			int min = 2100000000;
			int max = -1;
			A : for(int i = 0; i < line.length(); i++) { //비교 기준이 될 알파벳
				if(alpha[line.charAt(i) - 'a'] < k) continue;
				
				int count = 1;
				for(int j = i + 1; j < line.length(); j++) { //비교할 알파벳
					if(line.charAt(i) == line.charAt(j)) {
						count++;
						
						if(count == k) {
							min = Math.min(min, j - i + 1);
							max = Math.max(max, j - i + 1);
							continue A;
						}
					}
				}
			}
			
			if(min == 210000000) {
				sb.append("-1\n");
			} else {
				sb.append(min + " " + max + "\n");
			}
		}
		
		System.out.print(sb.toString());
	}

}
