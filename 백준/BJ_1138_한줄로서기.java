package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 최대 10명밖에 안되므로 완탐
 * 
 * 순열로 모든 경우의 수를 구한 다음 하나하나 대조해보면서 맞는 답 찾기
 */
public class BJ_1138_한줄로서기 {

	static boolean c = true;
	static int[] answer;
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int[] goal = new int[n];
		answer = new int[n];
		st = new StringTokenizer(reader.readLine());
		for(int i = 0; i < n; i++) {
			goal[i] = Integer.parseInt(st.nextToken());
		}
		
		set(n, 0, new int[n], new boolean[n], goal);
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < n; i++) {
			sb.append(answer[i] + " ");
		}
		
		System.out.println(sb.toString());
	}
	
	public static void set(int n, int index, int[] list, boolean[] check, int[] goal) {
		if(index == n) {
			//검사
			for(int i = n - 1; i >= 0; i--) { //비교 기준
				int count = 0;
				for(int j = i - 1; j >= 0; j--) { //비교 대상
					if(list[i] < list[j]) {
						count++;
					}
				}
				
				if(count != goal[list[i] - 1]) {
					return;
				}
			}
			
			c = false;
			answer = Arrays.copyOf(list, n);
			
			return;
		}
		
		for(int i = 0; i < n; i++) {
			if(!check[i]) {
				list[index] = i + 1;
				check[i] = true;
				if(c) set(n, index + 1, list, check, goal);
				check[i] = false;
			}
		}
	}

}
