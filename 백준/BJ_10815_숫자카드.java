package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_10815_숫자카드 {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int[] Pnums = new int[10000000];
		int[] Mnums = new int[10000000];
		
		st = new StringTokenizer(reader.readLine());
		for(int i = 0; i < n; i++) {
			int now = Integer.parseInt(st.nextToken());
			
			if(now >= 0) {
				Pnums[now] = 1;
			} else {
				Mnums[Math.abs(now)] = 1;
			}
		}
		
		st = new StringTokenizer(reader.readLine());
		int m = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(reader.readLine());
		for(int i = 0; i < m - 1; i++) {
			int now = Integer.parseInt(st.nextToken());
			
			if(now >= 0) {
				System.out.print(Pnums[now] + " ");				
			} else {
				System.out.print(Mnums[Math.abs(now)] + " ");				
			}
		}
		int now = Integer.parseInt(st.nextToken());
		if(now >= 0) {
			System.out.print(Pnums[now] + " ");				
		} else {
			System.out.print(Mnums[Math.abs(now)] + " ");				
		}
	}

}
