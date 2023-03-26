package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;
/*
 * 시뮬레이션
 * 그대로 구현하면 됨
 */

public class BJ_2174_로봇시뮬레이션 {
	
	static class Robot {
		int r, c, d;

		public Robot(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		
		int mapSizeC = Integer.parseInt(st.nextToken());
		int mapSizeR = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(reader.readLine());
		int robotN = Integer.parseInt(st.nextToken());
		int orderN = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[mapSizeR][mapSizeC];
		HashMap<Integer, Robot> hashMap = new HashMap<>();
		
		for(int i = 1; i <= robotN; i++) {
			st = new StringTokenizer(reader.readLine());
			int robotC = Integer.parseInt(st.nextToken()) - 1;
			int robotR = mapSizeR - Integer.parseInt(st.nextToken());
			String robotD = st.nextToken();
			int robotDInt = 0;
			
			if(robotD.equals("E")) {
				robotDInt = 1;
			} else if(robotD.equals("S")) {
				robotDInt = 2;
			} else if(robotD.equals("W")) {
				robotDInt = 3;
			}
			
			hashMap.put(i, new Robot(robotR, robotC, robotDInt));
			map[robotR][robotC] = i;
		}
		
		int[] dr = {-1, 0, 1, 0};
		int[] dc = {0, 1, 0, -1};
		for(int i = 0; i < orderN; i++) {
			st = new StringTokenizer(reader.readLine());
			
			int rN = Integer.parseInt(st.nextToken()); //움직일 로봇 넘버
			String order = st.nextToken(); //명령
			int repeat = Integer.parseInt(st.nextToken()); //해당 명령 반복 횟수
			
			Robot robot = hashMap.get(rN);
			
			for(int j = 0; j < repeat; j++) {
				if(order.equals("R")) {
					int prevD = robot.d;
					
					robot.d = (prevD + 1) >= 4 ? 0 : prevD + 1;
				} else if(order.equals("L")) {
					int prevD = robot.d;
					
					robot.d = (prevD - 1) < 0 ? 3 : prevD - 1;
				} else {
					//벽에 부딪히면
					if(robot.r + dr[robot.d] < 0 || robot.r + dr[robot.d] >= mapSizeR || robot.c + dc[robot.d] < 0 || robot.c + dc[robot.d] >= mapSizeC) {
						System.out.println("Robot " + rN + " crashes into the wall");
						return;
					} 
					//다른 로봇에 부딪히면
					else if(map[robot.r + dr[robot.d]][robot.c + dc[robot.d]] != 0) {
						System.out.println("Robot " + rN + " crashes into robot " + map[robot.r + dr[robot.d]][robot.c + dc[robot.d]]);
						return;
					} else {
						map[robot.r][robot.c] = 0;
						map[robot.r + dr[robot.d]][robot.c + dc[robot.d]] = rN;
						robot.r += dr[robot.d];
						robot.c += dc[robot.d];
					}
				}
			}
			
		}
		System.out.println("OK");
	}

}
