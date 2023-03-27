package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 구현 문제
 * 
 * 아두이노가 차례차례 움직이는 것이 아닌 한 번에 움직이는 것에 유의
 */


public class BJ_8972_미친아두이노 {
	static class Robot {
		int r, c;

		public Robot(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static int startR, startC;
	static HashMap<Integer, Robot> robots;
	static int[][] map;
	static int mapSizeR, mapSizeC;
	static int[] dr = {1, 1, 1, 0, 0, 0, -1, -1, -1};
	static int[] dc = {-1, 0, 1, -1, 0, 1, -1, 0, 1};
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		
		mapSizeR = Integer.parseInt(st.nextToken());
		mapSizeC = Integer.parseInt(st.nextToken());
	
		//0 - 빈칸, 1 - 종수, 2 - 미친 아두이노
		map = new int[mapSizeR][mapSizeC];
		robots = new HashMap<>();
		
		int robotIndex = 2;
		
		for(int i = 0; i < mapSizeR; i++) {
			st = new StringTokenizer(reader.readLine());
			String oneLine = st.nextToken();
			
			for(int j = 0; j < mapSizeC; j++) {
				if(oneLine.charAt(j) == 'I') {
					map[i][j] = 1;
					startR = i;
					startC = j;
				} else if(oneLine.charAt(j) == 'R') {
					map[i][j] = robotIndex;
					robots.put(robotIndex++, new Robot(i, j));
				} else {
					map[i][j] = 0;
				}
			}
		}
		
		boolean[] canMoveRobot = new boolean[robots.size() + 2];
		Arrays.fill(canMoveRobot, true);
		
		String moving = new StringTokenizer(reader.readLine()).nextToken();
		
		for(int i = 0; i < moving.length(); i++) {
			int move = moving.charAt(i) - '0' - 1;
			
			if(!movable(move, canMoveRobot)) {
				System.out.print("kraj " + (i + 1));
				return;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < mapSizeR; i++) {
			for(int j = 0; j < mapSizeC; j++) {
				if(map[i][j] == 0) {
					sb.append(".");
				} else if(map[i][j] == 1) {
					sb.append("I");
				} else {
					sb.append("R");
				}
			}
			if(i < mapSizeR - 1)sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	public static boolean movable(int move, boolean[] canMoveRobot) {
		//종수 움직임
		int togoR = startR + dr[move];
		int togoC = startC + dc[move];
		
		if(map[togoR][togoC] == 2) { //아두이노 있는 칸
			return false;
		}
		map[startR][startC] = 0;
		startR = togoR;
		startC = togoC;
		map[startR][startC] = 1;
		
		
		Queue<int[]> boomR = new LinkedList<>();
		//로봇들 차례대로 하나씩 움직임
		A: for(Entry e : robots.entrySet()) {
			Robot robot = (Robot)e.getValue();
			
			if(canMoveRobot[(int)e.getKey()]) {
				int min = 99999999;
				for(int i = 0; i < 9; i++) {
					int tmpR = robot.r + dr[i];
					int tmpC = robot.c + dc[i];
					 
					//움직인 게 범위 내일 경우만
					if(tmpR >= 0 && tmpR < mapSizeR && tmpC >= 0 && tmpC < mapSizeC) {
						//종수랑 제일 가까워지는 거 찾는 중
						if(Math.abs(tmpR - startR) + Math.abs(tmpC - startC) < min) {
							min = Math.abs(tmpR - startR) + Math.abs(tmpC - startC);
							togoR = tmpR;
							togoC = tmpC;
						}
					}
				}
				
				//움직였는데 종수 만나면
				if(map[togoR][togoC] == 1) {
					return false;
				}
				
				//다른 아두이노 있으면
				if(map[togoR][togoC] >= 2 && map[togoR][togoC] < (int)e.getKey()) {

					boomR.add(new int[] {map[togoR][togoC], togoR, togoC});
					boomR.add(new int[] {(int)e.getKey(), togoR, togoC});
					
					if(map[robot.r][robot.c] == (int)e.getKey()) {
						map[robot.r][robot.c] = 0;
					}
					
					continue A;
				}
					
				if(map[robot.r][robot.c] == (int)e.getKey()) {
					map[robot.r][robot.c] = 0;
				}
				robot.r = togoR;
				robot.c = togoC;
				map[robot.r][robot.c] = (int)e.getKey();
			}
		}
		
		while(!boomR.isEmpty()) {
			int[] line = boomR.poll();
			int robotIndex = line[0];
			int zeroR = line[1];
			int zeroC = line[2];
			
			map[zeroR][zeroC] = 0;
			canMoveRobot[robotIndex] = false;
		}
		
		return true;
	}

}