package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BJ_2002_추월 {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		String[] income = new String[n];
		String[] outcome = new String[n];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(reader.readLine());
			
			income[i] = st.nextToken();
		}
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(reader.readLine());
			
			outcome[i] = st.nextToken();
		}
		
		int iIndex = 0;
		int oIndex = 0;
		int count = 0;
		HashSet<String> set = new HashSet<>();
		
		while(oIndex < n) {
			if(income[iIndex].equals(outcome[oIndex])) {
				iIndex++;
				oIndex++;
			} else {
				set.add(outcome[oIndex]);
				
				
				if(set.contains(income[iIndex])) {
					iIndex++;
				} else {
					oIndex++;
					count++;					
				}
			}
		}
		
		System.out.println(count);
	}
}
