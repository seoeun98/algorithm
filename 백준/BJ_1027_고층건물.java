package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 건물 최대 갯수가 50개밖에 안되니까 완전탐색
 * 
 * i번째 건물에서 j 건물을 바라볼 수 있으면 count++
 * 바라볼 수 있는지 체크하기 위해 i와 j 사이의 1차 방정식 세워서 그 방정식 위로 지나가는 건물이 있는지 확인
 * 있으면 못봄, 없으면 볼 수 있음
 */

public class BJ_1027_고층건물 {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int[][] buildings = new int[n][2];
		
		st = new StringTokenizer(reader.readLine());
		for(int i = 0; i < n; i++) {
			buildings[i][0] = i;
			buildings[i][1] = Integer.parseInt(st.nextToken());
		}
		
		int max = 0;
		
		for(int i = 0; i < n; i++) { //이 건물에 서서
			int count = 0;
			for(int j = 0; j < n; j++) { //이 건물을 바라볼 때
				if(i == j) continue;
				
				double d = 0;
				double p = 0;
				int from = 0;
				int to = 0;
				int[] b1 = buildings[i];
				int[] b2 = buildings[j];
				
				if(i < j) { //i가 더 왼쪽에 있을 때
					d = (double)(b2[1] - b1[1]) / (double)(b2[0] - b1[0]);
					p = (b1[1] - d * b1[0]);
					from = i + 1;
					to = j;
				} else {
					d = (double)(b1[1] - b2[1]) / (double)(b1[0] - b2[0]);
					p = (b2[1] - d * b2[0]);
					from = j + 1;
					to = i;
				}
				
				boolean visible = true;
				for(int k = from; k < to; k++) {
					int[] b3 = buildings[k];
					
					if(d * b3[0] + p <= b3[1]) {
						visible = false;
					}
				
				}
				
				if(visible) {
					count++;
				}
				
			}
			
			if(count > max) {
				max = count;
			}
		}
		
		System.out.println(max);
	}

}
