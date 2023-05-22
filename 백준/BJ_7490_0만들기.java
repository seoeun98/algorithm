package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * n이 최대 9 이므로 완탐
 */

public class BJ_7490_0만들기 {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		
		int t = Integer.parseInt(st.nextToken());
		
		for(int tt = 0; tt < t; tt++) {
			st = new StringTokenizer(reader.readLine());
			
			int n = Integer.parseInt(st.nextToken());
			int[] ns = new int[n];
			
			for(int i = 0; i < n; i++) {
				ns[i] = i + 1;
			}
			
			dfs(0, ns, new char[2 * n - 1], 0);
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	static StringBuilder sb = new StringBuilder();
	public static void dfs(int indexN, int[] ns, char[] list, int indexL) {
		if(indexN == ns.length) {
			int total = 0;
			int prev = 0;
			boolean plus = true, minus = false;
			
			for(int i = 0; i < list.length; i++) {
				if(list[i] == ' ') continue;
				
				if(list[i] - '0' >= 1 && list[i] - '0' <= 9) {
					if(prev == 0) prev = list[i] - '0';
					else prev = prev * 10 + (list[i] - '0');
				} else {
					if(plus) {
						total += prev;
						prev = 0;
						plus = false;
					}
					if(minus) {
						total -= prev;
						prev = 0;
						minus = false;
					}
					
					if(list[i] == '+') plus = true;
					else if(list[i] == '-') minus = true;
				}
			}
			if(plus) {
				total += prev;
			}
			if(minus) {
				total -= prev;
			}
			
			if(total == 0) {
				sb.append(new String(list) + "\n");
			}
			
			return;
		}
		
		if(indexL % 2 == 0) {
			list[indexL] = (char)(ns[indexN] + '0');
			dfs(indexN + 1, ns, list, indexL + 1);
		} else {
			list[indexL] = ' ';
			dfs(indexN, ns, list, indexL + 1);
			
			list[indexL] = '+';
			dfs(indexN, ns, list, indexL + 1);
			
			list[indexL] = '-';
			dfs(indexN, ns, list, indexL + 1);
		}
	}
}
