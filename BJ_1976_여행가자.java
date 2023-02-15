package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_1976_여행가자 {

	static int townN;
	static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		
		townN = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(reader.readLine());
		int travelN = Integer.parseInt(st.nextToken());
		
		map = new int[townN][townN];
		
		for(int i = 0; i < townN; i++) {
			st = new StringTokenizer(reader.readLine());
			
			for(int j = 0; j < townN; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(reader.readLine());
		int[] travelOrder = new int[travelN];
		for(int i = 0; i < travelN; i++) {
			travelOrder[i] = Integer.parseInt(st.nextToken()) - 1;
		}
	
		for(int i = 0; i < travelN - 1; i++) {
			if(!canGo(travelOrder[i], travelOrder[i + 1])) {
				System.out.println("NO");
				System.exit(0);
			}
		}
		System.out.println("YES");
	}
	
	public static boolean canGo(int start, int end) {
//		int[][] value = new int[townN][townN];
//		int max = 100000;
//		
//		for(int i = 0; i < townN; i++) {
//			for(int j = 0; j < townN; j++) {
//				if(i != j && map[i][j] == 0) {
//					value[i][j] = max;
//				}
//			}
//		}
//		
//		for(int i = 0; i < townN; i++) { //경
//			for(int j = 0; j < townN; j++) { //출
//				if(i == j) continue;
//				
//				for(int k = 0; k < townN; k++) { //도
//					if(i == k || j == k) continue;
//					
//					if(value[j][k] > value[j][i] + value[i][k]) {
//						value[j][k] = value[j][i] + value[i][k];
//					}
//				}
//			}
//		}
//		
//		if(value[start][end] < max)
//			return true;
//		else return false;
		
		int max = 1000000;
		int[] value = new int[townN];
		boolean[] check = new boolean[townN];
		
		Arrays.fill(value, max);
		value[start] = 0;
		
		for(int i = 0; i < townN; i++) {
			int index = start;
			int min = max;
			
			for(int j = 0; j < townN; j++) {
				if(value[j] < min && !check[j]) {
					index = j;
					min = value[j];
				}
			}
			
			check[index] = true;
			
			for(int j = 0; j < townN; j++) {
				if(!check[j] && map[index][j] != 0 && value[j] > value[index] + map[index][j]) {
					value[j] = value[index] + map[index][j];
				}
			}
		}
		
		if(value[end] >= max) {
			return false;
		}
		return true;
	}

}
