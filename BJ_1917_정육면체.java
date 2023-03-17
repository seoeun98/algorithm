package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 주사위 전개도는 총 11
 * 
 * 하나하나 검사
 * 4방향으로 돌린 거&뒤집은 거 검사해서 하나라도 맞으면 true
 * 다 틀리면 false
 */


public class BJ_1917_정육면체 {

	static ArrayList<int[][]> list;
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		list = new ArrayList<>();
		
		//11개의 전개도 저장
		list.add(new int[][] {
				{0, 0, 0, 1}, 
				{1, 1, 1, 1},
				{1, 0, 0, 0}
		});
		
		list.add(new int[][] {
				{0, 0, 1, 0},
				{1, 1, 1, 1},
				{0, 1, 0, 0}
		});
		
		list.add(new int[][] {
				{0, 0, 0, 1}, 
				{1, 1, 1, 1}, 
				{0, 0, 0, 1}
		});
		
		list.add(new int[][] {
				{0, 0, 1, 1}, 
				{1, 1, 1, 0}, 
				{0, 0, 1, 0}
		});
		
		list.add(new int[][] {
				{0, 0, 0, 1},
				{1, 1, 1, 1}, 
				{0, 1, 0, 0}
		});
		
		list.add(new int[][] {
				{0, 0, 1, 0},
				{1, 1, 1, 1},
				{0, 0, 1, 0}
		});
		
		list.add(new int[][] {
				{0, 0, 0, 1},
				{1, 1, 1, 1},
				{0, 0, 1, 0}
		});
		
		list.add(new int[][] {
				{0, 0, 1, 1}, 
				{0, 1, 1, 0}, 
				{1, 1, 0, 0}
		});
		
		list.add(new int[][] {
				{0, 0, 1, 1}, 
				{1, 1, 1, 0},
				{0, 1, 0, 0}
		});
		
		list.add(new int[][] {
				{0, 0, 1, 1}, 
				{1, 1, 1, 0}, 
				{1, 0, 0, 0}
		});
		
		list.add(new int[][] {
				{0, 0, 1, 1, 1}, 
				{1, 1, 1, 0, 0}
		});
		
		for(int i = 0; i < 3; i++) { //3개의 입력 데이터
			int[][] dice;
			
			int startR = 10000;
			int startC = 10000;
			int endR = 0;
			int endC = 0;
			
			int[][] original = new int[6][6];
			
			for(int j = 0; j < 6; j++) { //한 개당 6줄
				st = new StringTokenizer(reader.readLine());
				
				for(int k = 0; k < 6; k++) { //한 줄당 6칸
					int tmp = Integer.parseInt(st.nextToken());
					
					if(tmp == 1) {
						if(startR > j) startR = j;
						if(endR < j) endR = j;
						if(startC > k) startC = k;
						if(endC < k) endC = k;
						
						original[j][k] = 1;
					}
				}
			}
			
			dice = new int[endR - startR + 1][endC - startC + 1];
			
			for(int j = 0; j < dice.length; j++) {
				for(int k = 0; k < dice[j].length; k++) {
					dice[j][k] = original[startR + j][startC + k];
				}
			}
			
			if(check(dice)) {
				System.out.println("yes");
			} else {
				System.out.println("no");
			}
		}
	}

	
	public static boolean check(int[][] dice) {
		int notsameCnt = 0;
		
		for(int[][] map : list) {
			for(int i = 0; i < 4; i++) {
				int[][] tmpMap = new int[map.length][map[0].length];
				for(int l = 0; l < map.length; l++) {
					tmpMap[l] = Arrays.copyOfRange(map[l], 0, map[l].length);
				}
				
				tmpMap = turn(tmpMap, i);
				
				if(dice.length == tmpMap.length && dice[0].length == tmpMap[0].length) {
					A: for(int k = 0; k < dice.length; k++) {
						for(int j = 0; j < dice[k].length; j++) {
							if(dice[k][j] != tmpMap[k][j]) {
								notsameCnt++;
								break A;
							}
						}
					}
				} else {
					notsameCnt++;
				}
				
				tmpMap = mirror(tmpMap);
				if(dice.length == tmpMap.length && dice[0].length == tmpMap[0].length) {
					A: for(int k = 0; k < dice.length; k++) {
						for(int j = 0; j < dice[k].length; j++) {
							if(dice[k][j] != tmpMap[k][j]) {
								notsameCnt++;
								break A;
							}
						}
					}
				} else {
					notsameCnt++;
				}
			}
			
			
			
//			for(int i = 0; i < 4; i++) {
//				int[][] tmpMap = new int[map.length][map[0].length];
//				for(int l = 0; l < map.length; l++) {
//					tmpMap[l] = Arrays.copyOfRange(map[l], 0, map[l].length);
//				}
//				
//				tmpMap = mirror(tmpMap);
//				tmpMap = turn(tmpMap, i);
//				
//				if(dice.length == tmpMap.length && dice[0].length == tmpMap[0].length) {
//					A: for(int k = 0; k < dice.length; k++) {
//						for(int j = 0; j < dice[k].length; j++) {
//							if(dice[k][j] != tmpMap[k][j]) {
//								notsameCnt++;
//								break A;
//							}
//						}
//					}
//				} else {
//					notsameCnt++;
//				}
//			}
			
		}
		
		if(notsameCnt == 88)
			return false;
		return true;
	}
	
	public static int[][] mirror(int[][] dice) { //반대로 뒤집기
		int[][] mirrored = new int[dice.length][dice[0].length];
		
		for(int i = 0; i < dice.length; i++) {
			for(int j = 0; j < dice[i].length; j++) {
				mirrored[i][dice[i].length - j - 1] = dice[i][j];
			}
		}
		
		return mirrored;
	}
	
	public static int[][] turn(int[][] dice, int count) {
		int[][] turned;
		
		if(count == 0) {
			return dice;
		}
		
		if(count % 2 == 1) {
			turned = new int[dice[0].length][dice.length];
		} else {
			turned = new int[dice.length][dice[0].length];
		}
		
		if(count == 1) {
			for(int i = 0; i < dice.length; i++) {
				for(int j = 0; j < dice[i].length; j++) {
					turned[j][dice.length - i - 1] = dice[i][j];
				}
			}
		} else if(count == 2) {
			for(int i = 0; i < dice.length; i++) {
				for(int j = 0; j < dice[i].length; j++) {
					turned[dice.length - i - 1][dice[i].length - j - 1] = dice[i][j];
				}
			}
		} else {
			for(int i = 0; i < dice.length; i++) {
				for(int j = 0; j < dice[i].length; j++) {
					turned[dice[i].length - j - 1][dice.length - i - 1] = dice[i][j];
				}
			}
		}
		
		return turned;
	}
}
