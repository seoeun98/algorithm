package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 첫 번째 스위치 상태를 고정한다. 킨 거(A)랑 안 킨거(B)
 * 그러면 첫 번째 전구에 영향을 줄 수 있는 건 두 번째 스위치뿐이다
 * 목표 상태와 A, B 상태를 비교해서 첫 번째 전구 상태가 다르다? 그러면 두 번째 스위치 눌러서 첫 번째 상태 맞춰주기
 * 다음 번호로 넘어가서 두 번째 전구 상태가 다르다? 세 번째 스위치 눌러서 맞춰주기...무한 반복
 */

public class BJ_2138_전구와스위치 {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		
		int n = Integer.parseInt(st.nextToken()); //전구와 스위치 갯수
		
		//true - 1, false - 0
		boolean[] ori = new boolean[n];
		boolean[] goal = new boolean[n];
		
		String o = new StringTokenizer(reader.readLine()).nextToken();
		String g = new StringTokenizer(reader.readLine()).nextToken();
		
		for(int i = 0; i < o.length(); i++) {
			ori[i] = o.charAt(i) - '0' == 0 ? false : true;
			goal[i] = g.charAt(i) - '0' == 0 ? false : true;
		}
		
		int countFirstOn = 1;
		int countFirstOff = 0;
		
		boolean[] firstOn = Arrays.copyOfRange(ori, 0, n);
		firstOn[0] = !firstOn[0];
		firstOn[1] = !firstOn[1];
		
		for(int i = 1; i < ori.length; i++) {
			if(ori[i - 1] != goal[i - 1]) {
				ori[i - 1] = !ori[i - 1];
				ori[i] = !ori[i];
				
				if(i + 1 < ori.length) {
					ori[i + 1] = !ori[i + 1];
				}
				
				countFirstOff++;
			}
			
			if(firstOn[i - 1] != goal[i - 1]) {
				firstOn[i - 1] = !firstOn[i - 1];
				firstOn[i] = !firstOn[i];
				
				if(i + 1 < firstOn.length) {
					firstOn[i + 1] = !firstOn[i + 1];
				}
				countFirstOn++;
			}
		}
		
		boolean firstOnPossible = true;
		boolean firstOffPossible = true;
		for(int i = 0; i < n; i++) {
			if(firstOn[i] != goal[i]) {
				firstOnPossible = false;
			}
			
			if(ori[i] != goal[i]) {
				firstOffPossible = false;
			}
		}
		
		if(firstOnPossible && firstOffPossible) {
			System.out.println(Math.min(countFirstOn, countFirstOff));
		} else if(firstOnPossible) {
			System.out.println(countFirstOn);
		} else if(firstOffPossible) {
			System.out.println(countFirstOff);
		} else {
			System.out.println(-1);
		}
		
	}

}
