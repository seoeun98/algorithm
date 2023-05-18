package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_15989_123더하기4 {
	static int count;
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(reader.readLine());	
		int t = Integer.parseInt(st.nextToken());
		
		StringBuilder sb = new StringBuilder();
		for(int test = 0; test < t; test++) {
			st = new StringTokenizer(reader.readLine());	
			int n = Integer.parseInt(st.nextToken());		
			
			//인덱스를 0부터 하는 것이 아니라 쉽게 1부터 하기 위해서
			int[][] dp = new int[n + 1][4];
			dp[0][1] = 1;
			
			for(int i = 1; i <= n; i++) {
				if(i >= 1) dp[i][1] = dp[i - 1][1];
				if(i >= 2) dp[i][2] = dp[i - 2][1] + dp[i - 2][2];
				if(i >= 3) dp[i][3] = dp[i - 3][1] + dp[i - 3][2] + dp[i - 3][3];
			}
			
			sb.append(dp[n][1] + dp[n][2] + dp[n][3] + "\n");
		}
		
		System.out.println(sb.toString());
	}
}
