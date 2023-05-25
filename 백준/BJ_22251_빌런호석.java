package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

/*
 * 완탐으로 돌려버리면 됨
 */
public class BJ_22251_빌런호석 {
	static int n, k, p; 
	static int[] xList;
	static int[][] display = {
			{1, 1, 1, 0, 1, 1, 1}, //0
			{0, 0, 1, 0, 0, 1, 0}, //1
			{1, 0, 1, 1, 1, 0, 1}, //2
			{1, 0, 1, 1, 0, 1, 1}, //3
			{0, 1, 1, 1, 0, 1, 0}, //4
			{1, 1, 0, 1, 0, 1, 1}, //5
			{1, 1, 0, 1, 1, 1, 1}, //6
			{1, 0, 1, 0, 0, 1, 0}, //7
			{1, 1, 1, 1, 1, 1, 1}, //8
			{1, 1, 1, 1, 0, 1, 1} //9
	};
	
	static HashSet<Integer> set = new HashSet<>();
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		
		n = Integer.parseInt(st.nextToken()); //빌딩의 최대 층수
		k = Integer.parseInt(st.nextToken()); //디스플레이 자릿수
		p = Integer.parseInt(st.nextToken()); //반전 가능 횟수
		String xs = st.nextToken(); //실제 층
		
		xList = new int[k]; //실제 층을 배열화
		int xtmp = k;
		
		for(int i = xs.length() - 1; i >= 0; i--) {
			xList[--xtmp] = xs.charAt(i) - '0';
		}
		
		dfs(p, 0, new int[k]);
		System.out.println(set.size() - 1);
	}
	
	public static void dfs(int chance, int index, int[] now) {
		if(index == k) {
			int nowI = 0;
			for(int i = 0; i < now.length; i++) {
				nowI = nowI * 10 + now[i];
			}
			
			if(nowI <= n && 0 < nowI)
				set.add(nowI);
			return;
		}
		
		int[] tmp = Arrays.copyOf(now, now.length);
		for(int i = 0; i < 10; i++) { //이 수로 바꿀 거임		
			int count = 0;
			for(int j = 0; j < 7; j++) {
				if(display[i][j] != display[xList[index]][j]) {
					count++;
				}
			}
			
			if(count > chance) continue;
			
			tmp[index] = i;
			dfs(chance - count, index + 1, tmp);
			tmp[index] = now[index];
		}
	}

}
