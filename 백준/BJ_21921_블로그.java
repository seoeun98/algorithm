package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 슬라이딩 윈도우
 */

public class BJ_21921_블로그 {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int window = Integer.parseInt(st.nextToken());
		int[] list = new int[n];
		
		st = new StringTokenizer(reader.readLine());
		for(int i = 0; i < n; i++) {
			list[i] = Integer.parseInt(st.nextToken());
		}
		
		int tmp = 0;
		int part = 1;
		
		for(int i = 0; i < window; i++) {
			tmp += list[i];
		}
		
		int max = tmp;
		
		int start = 0;
		int end = window;
		
		while(end < n) {
			tmp += list[end];
			tmp -= list[start];
			
			if(tmp > max) {
				max = tmp;
				part = 1;
			} else if(tmp == max) {
				part++;
			}
			
			start++;
			end++;
		}
		
		
		if(max == 0) {
			System.out.println("SAD");
		} else {
			System.out.println(max);
			System.out.println(part);
		}
	}

}
