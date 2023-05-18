package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * 스택
 */
public class BJ_1863_스카이라인쉬운거 {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		
		Stack<Integer> stack = new Stack<>();
		int count = 0;
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(reader.readLine());
			
			int c = Integer.parseInt(st.nextToken()); //건물의 위치
			int r = Integer.parseInt(st.nextToken()); //건물의 높이
		
			while(!stack.isEmpty() && stack.peek() > r) {
				count++;
				stack.pop();
			}
			
			if((stack.isEmpty() || r > stack.peek()) && r > 0) stack.add(r);
				
		}
		
		count += stack.size();
		
		
		System.out.println(count);
	}

}
