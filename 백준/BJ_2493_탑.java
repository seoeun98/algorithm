package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;
/*
 * 스택
 */

public class BJ_2493_탑 {
	static class Building {
		int h, index;

		public Building(int h, int index) {
			this.h = h;
			this.index = index;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		Building[] buildings = new Building[n];
		
		st = new StringTokenizer(reader.readLine());
		for(int i = 0; i < n; i++) {
			buildings[i] = new Building(Integer.parseInt(st.nextToken()), i + 1);
		}
		
		Stack<Building> stack = new Stack<>();
		
		int[] answer = new int[n];
		
		stack.add(buildings[0]);
		
		for(int i = 1; i < n; i++) {
			while(!stack.isEmpty() && stack.peek().h <= buildings[i].h) {
				stack.pop();
			}
			if(!stack.isEmpty()) {
				answer[i] = stack.peek().index;
			}
			stack.add(buildings[i]);
		}
		
		for(int i = 0; i < n; i++) {
			System.out.print(answer[i] + " ");
		}
	}

}
