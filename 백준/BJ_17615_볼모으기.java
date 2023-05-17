package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 답이 될 수 있는 경우는 3가지
 * 1. 더 적은 수의 색깔 공을 옮기기
 * 2. 제일 왼쪽으로 특정 색의 공을 몰기
 * 3. 제일 오른쪽으로 특정 색의 공을 몰기
 */

public class BJ_17615_볼모으기 {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(reader.readLine());
		String line = st.nextToken();
		
		int[] balls = new int[n]; //R==1, B==2
		int countR = 0, countB = 0; //수가 더 적은 게 정답 후보 1
		for(int i = 0; i < line.length(); i++) {
			if(line.charAt(i) == 'R') { 
				balls[i] = 1;
				countR++;
			} else {
				balls[i] = 2;
				countB++;
			}
		}
		
		int index = 1;
		int countL = 1;
		while(index < n && balls[index++] == balls[0]) {
			countL++;
		}
		
		if(balls[0] == 1) {
			countL = countR - countL;
		} else {
			countL = countB - countL;
		}
		
		index = n - 2;
		int countRight = 1;
		while(index >= 0 && balls[index--] == balls[n - 1]) {
			countRight++;
		}
		
		if(balls[n - 1] == 1) {
			countRight = countR - countRight;
		} else {
			countRight = countB - countRight;
		}
	
		int answer = Math.min(Math.min(countR, countB), Math.min(countRight, countL));
		System.out.println(answer);
	}

}
