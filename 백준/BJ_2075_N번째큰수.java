package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

/*
 * 이 문제... 뭐지..? 이게 왜 실버2지...?
 */

public class BJ_2075_N번째큰수 {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int[] list = new int[n * n];
		int index = 0;
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(reader.readLine());
			
			for(int j = 0; j < n; j++) {
				list[index++] = Integer.parseInt(st.nextToken());
			}
		}
		
		Arrays.sort(list);
		System.out.println(list[list.length - n]);
	}

}
