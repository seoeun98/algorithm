package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * stack에 하나씩 넣으면서 PPAP가 되었다면 P로 바꿔서 stack에 넣기.
 * 다 끝난 후에 stack에 A가 있다? 그러면 NP
 * 
 * 처리해야할 예외
 * 1. 처음에 글자 수가 4 미만일 때 - 어떻게 해도 PPAP가 될 수 없음
 * 2. 처음에 A가 존재하지 않을 때 - 어떻게 해도 PPAP가 될 수 없음
 * 3. PPAP를 P로 치환할 수 있다 == P도 PPAP 문자열이다
 */

public class BJ_16120_PPAP {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		
		String line = st.nextToken();
		
		if(line.equals("P")) {
			System.out.println("PPAP");
			System.exit(0);
		}
		
		if(line.length() < 4 || !line.contains("A")) {
			System.out.println("NP");
			System.exit(0);
		}
		
		Stack<Character> que = new Stack<>();
		
		char[] list = line.toCharArray();
		que.add(list[0]);
		que.add(list[1]);
		que.add(list[2]);
		
		for(int i = 3; i < list.length; i++) {			
			char now = list[i];
			
			if(que.size() < 3) {
				que.add(now);
				continue;
			}
			
			char prev1 = que.pop();
			char prev2 = que.pop();
			char prev3 = que.pop();
			
			if(now == 'P' && prev1 == 'A' && prev2 == 'P' && prev3 == 'P') {
				que.add('P');	
			} else {
				que.add(prev3);
				que.add(prev2);
				que.add(prev1);
				que.add(now);
			}
		}
		
		if(que.contains('A')) {
			System.out.println("NP");
		} else {
			System.out.println("PPAP");
		}
	}

}
