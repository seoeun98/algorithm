package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 슬라이딩 윈도우
 */

public class BJ_2531_회전초밥 {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		
		int n = Integer.parseInt(st.nextToken()); //회전초밥 수
		int d = Integer.parseInt(st.nextToken()); //초밥 가짓수
		int k = Integer.parseInt(st.nextToken()); //연속해서 먹는 접시 수
		int c = Integer.parseInt(st.nextToken()) - 1; //쿠폰 번호
		int total = 0; //먹게되는 초밥의 총 가짓수
		
		int[] sushi = new int[n];
		int[] kind = new int[d];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(reader.readLine());
			
			sushi[i] = Integer.parseInt(st.nextToken()) - 1;
		}
		
		
		for(int i = 0; i < k; i++) { //먹기 시작하는 초밥의 인덱스
			kind[sushi[i]]++;
			
			if(kind[sushi[i]] == 1) {
				total++;
			}
		}
		
		int answer = total;
		
		//공짜 초밥
		kind[c]++;
		if(kind[c] == 1) {
			total++;
		}
		
		for(int i = 1; i < n; i++) {
			kind[sushi[i - 1]]--;
			
			if(kind[sushi[i - 1]] == 0) {
				total--;
			}
			
			int end = i + k - 1;
			if(end >= n) {
				end -= n;
			}
			
			kind[sushi[end]]++;
			
			if(kind[sushi[end]] == 1) {
				total++;
			}
			
			if(total > answer) {
				answer = total;
			}
		}
		
		System.out.println(answer);
	}

}
