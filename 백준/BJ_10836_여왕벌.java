package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 문제의
 * [자라는 크기를 제일 왼쪽 아래 칸에서 시작해서 위쪽으로 올라가서 
 * 제일 위쪽에 도착하면 오른쪽으로 이동하며 읽었다고 하자. 이 값들은 감소하지 않는다] 가 힌트이다.
 * 
 * 제일 왼쪽에 있는 애들을 제외하고는 무조건 위를 따라가는 게 이득이다.
 * 
 */

public class BJ_10836_여왕벌 {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		
		int m = Integer.parseInt(st.nextToken()); //벌통 크기
		int n = Integer.parseInt(st.nextToken()); //날짜 수
		
		int[] r = new int[m];
		int[] c = new int[m];
		
		for(int i = 0; i < n; i++) { // 1,000,000
			st = new StringTokenizer(reader.readLine());
			
			int zeroN = Integer.parseInt(st.nextToken());
			int oneN = Integer.parseInt(st.nextToken());
			int twoN = Integer.parseInt(st.nextToken());
			
			int rIndex = m - 1;
			for(int j = 0; j < m - 1; j++) {
				if(zeroN > 0) {
					zeroN--;
				} else if(oneN > 0) {
					r[rIndex] += 1;
					oneN--;
				} else if(twoN > 0) {
					r[rIndex] += 2;
					twoN--;
				}
				
				rIndex--;
			}
			
			for(int j = 0; j < m; j++) {
				if(zeroN > 0) {
					zeroN--;
				} else if(oneN > 0) {
					c[j] += 1;
					oneN--;
				} else if(twoN > 0) {
					c[j] += 2;
					twoN--;
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < m; j++) {
				if(j == 0 && i != 0) {
					sb.append((1 + r[i]) + " ");
				} else {
					sb.append((1 + c[j]) + " ");
				}
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}

}
