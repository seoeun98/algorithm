package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 최장증가수열 LIS
 */
public class BJ_2631_줄세우기 {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		
		int[] sort = new int[n];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(reader.readLine());
			
			int num = Integer.parseInt(st.nextToken());
			
			sort[i] = num;
		}
		
		
		int[] dp = new int[n];
		int answer = 0;
		
		for(int i = 0; i < n; i++) {
			int max = 0;
			
			for(int j = 0; j < i; j++) {
				if(sort[j] < sort[i])
					max = Math.max(dp[j], max);
			}
			
			dp[i] = max + 1;
			
			answer = Math.max(dp[i], answer);
		}
		
		
		System.out.println(n - answer);
	}

}
