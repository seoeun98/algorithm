package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * 투포인터
 */

public class BJ_1806_부분합 {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		
		int n = Integer.parseInt(st.nextToken()); //수의 갯수
		int s = Integer.parseInt(st.nextToken()); //최소로 넘겨야하는 수
		
		int[] list = new int[n];
		st = new StringTokenizer(reader.readLine());
		for(int i = 0; i < n; i++) {
			list[i] = Integer.parseInt(st.nextToken());
		}
		
		int answer = 99999999;
		
		int start = 0;
		int end = 1;
		int sum = list[0];
		int count = 1;
		while(true) {
			if(end == n && sum < s) {
				break;
			}
			
			if(sum < s) {
				sum += list[end++];
				count++;
			} else { //sum >= s
				if(answer > count) {
					answer = count;
				}
				
				sum -= list[start++];
				count--;
			}
		}
		
		System.out.println(answer == 99999999 ? 0 : answer);
	}

}
