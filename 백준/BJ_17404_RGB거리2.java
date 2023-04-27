package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * dp
 * 첫 번째 집과 마지막 집의 색이 같으면 안되므로 첫 번째 집의 색을 고정한다
 * 
 * 색을 고정하기 위해서는?
 * 색이 총 3개이므로 반복문을 3번 돌리면서 해당 색을 제외한 다른 색의 dp값을 MAX로 설정한다
 * 
 * 마지막 집과 첫 번째 집의 색을 다르게 하기 위해서는?
 * 마지막 집의 dp 값이 다 갱신된 이후에 첫 번째 집이 선택한 색 index의 dp 값을 MAX로 바꿔서 해당 색에는 접근할 수 없도록 한다
 */

public class BJ_17404_RGB거리2 {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int[][] houses = new int[n][3];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(reader.readLine());
			
			houses[i][0] = Integer.parseInt(st.nextToken()); //빨간색
			houses[i][1] = Integer.parseInt(st.nextToken()); //초록색
			houses[i][2] = Integer.parseInt(st.nextToken()); //파란색
		}
		
		int[][] dp = new int[n][3]; //[현재 집 인덱스][현재 집에서 선택할 색 인덱스]

		int answer = Integer.MAX_VALUE;
		
		for(int k = 0; k < 3; k++) {
			for(int i = 0; i < 3; i++) {
				if(k != i) {
					dp[0][i] = 100000000;
				} else {
					dp[0][i] = houses[0][i];
				}
			}
			
			for(int i = 1; i < n; i++) {
				dp[i][0] = houses[i][0] + Math.min(dp[i - 1][1], dp[i - 1][2]);
				dp[i][1] = houses[i][1] + Math.min(dp[i - 1][0], dp[i - 1][2]);
				dp[i][2] = houses[i][2] + Math.min(dp[i - 1][0], dp[i - 1][1]);
				
				if(i == n - 1) {
					dp[i][k] = 100000000;
				}
			}
			
			for(int i = 0; i < 3; i++) {
				if(answer > dp[n - 1][i]) {
					answer = dp[n - 1][i];
				}
			}
			
		}
		
		System.out.println(answer);
	
	}

}
