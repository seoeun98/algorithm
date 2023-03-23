package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 길을 건너지 않으면 못만나는 소 쌍을 구하면 됨
 * 3차원 배열로 각 칸에서 위 아래 오른쪽 왼쪽으로 이어져있는 다리 체크
 * 다리 있는 길로는 그냥 못간다고 생각
 * 
 * bfs 돌려서 다리가 없는 곳으로만 뻗어나감
 * 다른 소들 위치까지 뻗어나갈 수 있는지 체크
 * 못만나는 소 있으면 count++
 * 
 */

public class BJ_14466_소가길을건너간이유6 {

	public static class Cow {
		int r, c;

		public Cow(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static int mapSize;
	static int cowN;
	static boolean[][][] reachableWithoutR;
	static Cow[] cows;
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		
		mapSize = Integer.parseInt(st.nextToken()); //맵 사이즈
		cowN = Integer.parseInt(st.nextToken()); //소의 수
		int roadN = Integer.parseInt(st.nextToken()); //길의 수
		
		//위 아래 오른쪽 왼쪽
		reachableWithoutR = new boolean[mapSize][mapSize][4];
		
		for(int i = 0; i < mapSize; i++) {
			for(int j = 0; j < mapSize; j++) {
				Arrays.fill(reachableWithoutR[i][j], true);
			}
		}
		
		for(int i = 0; i < roadN; i++) {
			st = new StringTokenizer(reader.readLine());
			
			int firstR = Integer.parseInt(st.nextToken()) - 1;
			int firstC = Integer.parseInt(st.nextToken()) - 1;
			int secondR = Integer.parseInt(st.nextToken()) - 1;
			int secondC = Integer.parseInt(st.nextToken()) - 1;
			
			if(firstR == secondR) { //옆으로 다리
				if(firstC > secondC) { //첫번째 기준 왼쪽, 두번째 기준 오른쪽
					reachableWithoutR[firstR][firstC][3] = false;
					reachableWithoutR[secondR][secondC][2] = false;
				} else { //위랑 반대
					reachableWithoutR[firstR][firstC][2] = false;
					reachableWithoutR[secondR][secondC][3] = false;
				}
			} else { //위아래로 다리
				if(firstR > secondR) { //첫번째 기준 위쪽, 두번째 기준 아래쪽
					reachableWithoutR[firstR][firstC][0] = false;
					reachableWithoutR[secondR][secondC][1] = false;
				} else { //위랑 반대
					reachableWithoutR[firstR][firstC][1] = false;
					reachableWithoutR[secondR][secondC][0] = false;
				}
			}
		}
		
		cows = new Cow[cowN];
		for(int i = 0; i < cowN; i++) {
			st = new StringTokenizer(reader.readLine());
			
			int cowR = Integer.parseInt(st.nextToken()) - 1;
			int cowC = Integer.parseInt(st.nextToken()) - 1;
			
			cows[i] = new Cow(cowR, cowC);
		}
		
		int answer = 0;
		for(int i = 0; i < cowN; i++) {
			answer += reachable(i);
		}
		
		System.out.println(answer);
	}
	
	//위 아래 오른쪽 왼쪽
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, 1, -1};
	public static int reachable(int cowIndex) {
		Cow fc = cows[cowIndex];
		
		boolean[][] check = new boolean[mapSize][mapSize];
		check[fc.r][fc.c] = true;
		Queue<int[]> que = new LinkedList<>();
		que.add(new int[] {fc.r, fc.c});
		
		while(!que.isEmpty()) {
			int[] now = que.poll();
			int nowR = now[0];
			int nowC = now[1];
			
			for(int i = 0; i < 4; i++) {
				int togoR = nowR + dr[i];
				int togoC = nowC + dc[i];
				
				if(togoR >= 0 && togoR < mapSize && togoC >= 0 && togoC < mapSize
						&& reachableWithoutR[nowR][nowC][i]
								&& !check[togoR][togoC]) {
					que.add(new int[] {togoR, togoC});
					check[togoR][togoC] = true;
				}
			}
		}
		
		int count = 0;
		for(int i = cowIndex + 1; i < cowN; i++) {
			if(!check[cows[i].r][cows[i].c]) {
				count++;
			}
		}
		
		return count;
	}

}
