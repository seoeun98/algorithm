package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_11660_누적합구하기5 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[][] map = new int[n + 1][n + 1]; //입력받은 지도
		int[][] sum = new int[n + 1][n + 1]; //누적합 지도
		
		for(int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());

			for(int j = 1; j <= n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				sum[i][j] = sum[i][j - 1] + sum[i - 1][j] - sum[i - 1][j - 1] + map[i][j];
			}
		}
		
		for(int k = 0; k < m; k++) {
			st = new StringTokenizer(br.readLine());
			
			int startI = Integer.parseInt(st.nextToken());
			int startJ = Integer.parseInt(st.nextToken());
			int endI = Integer.parseInt(st.nextToken());
			int endJ = Integer.parseInt(st.nextToken());
			
			int total = 0;
			
			total += (sum[endI][endJ] - sum[startI - 1][endJ] - sum[endI][startJ - 1] + sum[startI - 1][startJ - 1]);
			
			System.out.println(total);
		}
	}

}
