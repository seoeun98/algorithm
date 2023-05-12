package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.StringTokenizer;

/*
 * 이분 탐색으로 각 무게가 어디에 들어가야하는지를 탐색
 */

public class BJ_19637_IF문좀대신써줘 {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
				
		String[] names = new String[n];
		int[] powerLimits = new int[n];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(reader.readLine());
			
			String name = st.nextToken();
			int power = Integer.parseInt(st.nextToken());
			names[i] = name;
			powerLimits[i] = power;
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(reader.readLine());
			int power = Integer.parseInt(st.nextToken());
				
			int index = 0;
			int start = 0;
			int mid = 0;
			int end = n - 1;
			while(start <= end) {
				mid = (start + end) / 2;
				
				if(powerLimits[mid] >= power) {
					end = mid - 1;
					index = mid;
				} else {
					start = mid + 1;
				}
			}
			sb.append(names[index] + "\n");
		}
		
		System.out.println(sb.toString());
	}

}
