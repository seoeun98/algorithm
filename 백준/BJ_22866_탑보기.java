package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * BJ_2493_탑 의 응용 문제
 * 
 * 
 */

public class BJ_22866_탑보기 {

	static class Building {
		int index;
		int h;
		
		public Building(int index, int h) {
			this.index = index;
			this.h = h;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		Building[] list = new Building[n]; //각 건물의 높이
		int[] visible = new int[n]; //볼 수 있는 건물 수
		int[] closeN = new int[n]; //가장 가까운 건물 번호
		int[] closeD = new int[n];
		
		//건물 입력
		st = new StringTokenizer(reader.readLine());
		for(int i = 0; i < n; i++) {
			list[i] = new Building(i + 1, Integer.parseInt(st.nextToken()));
			closeD[i] = 99999999;
		}
		Stack<Building> stack = new Stack<>();
		
		//왼편에 보이는 건물 수
		for(int i = 0; i < n; i++) {
			while(stack.size() > 0 && stack.peek().h <= list[i].h) {
				stack.pop();
			}
			visible[i] += stack.size();
			
			if(stack.size() > 0 && list[i].index - stack.peek().index < closeD[i]) {
				closeN[i] = stack.peek().index;
				closeD[i] = list[i].index - stack.peek().index;
			}
			
			stack.add(list[i]);
		}
		
		//오른편에 보이는 건물 수
		stack.clear();
		for(int i = n - 1; i >= 0; i--) {
			while(stack.size() > 0 && stack.peek().h <= list[i].h) {
				stack.pop();
			}
			visible[i] += stack.size();
			
			if(stack.size() > 0 && stack.peek().index - list[i].index < closeD[i]) {
				closeN[i] = stack.peek().index;
				closeD[i] = stack.peek().index - list[i].index;
			}
			
			stack.add(list[i]);
		}
		
		//출력
		for(int i = 0; i < n; i++) {
			System.out.print(visible[i]);
			
			if(visible[i] != 0) {
				System.out.println(" " + closeN[i]);
			} else {
				System.out.println();
			}
		}
		
	}

}
