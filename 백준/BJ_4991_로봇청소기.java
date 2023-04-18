package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 아기상어마냥 제일 가까운 위치로 가고, 해당 위치에서 제일 가까운 점 재탐색 하는 식으로 풀면 틀림
 * 1. 각 점에서 점까지의 최소 거리를 모두 구하고 이차원 배열에 저장 <-- A에서 B까지의 거리를 저장한 배열임
 * 1-1. 만약 n으로부터의 다른 점까지 최소 거리가 하나도 없다? 그러면 닦을 수 없는 먼지
 * 2. 순열로 시작점에서부터 어떤 순서로 다닐 건지 정한 다음
 * 3. 해당 순서의 이동 거리가 최소면 갱신
 * 4. 
 */

public class BJ_4991_로봇청소기 {
	
	static int answer;
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		
		int c = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());

		while(c != 0 && r != 0) {
			answer = 99999999;
			int[][] map = new int[r][c];
			
			ArrayList<int[]> distList = new ArrayList<>();
			int startR = 0, startC = 0;
			// 맵 설정
			for(int i = 0; i < r; i++) {
				st = new StringTokenizer(reader.readLine());
				String line = st.nextToken();

				for(int j = 0; j < line.length(); j++) {
					if(line.charAt(j) == '.') { //갈 수 있는 곳
						map[i][j] = 0;
					} else if(line.charAt(j) == '*') { //목적지
						distList.add(new int[] {i, j});
						map[i][j] = distList.size();
					} else if(line.charAt(j) == 'x') { //갈 수 없는 곳
						map[i][j] = -1;
					} else {
						map[i][j] = 0;
						startR = i;
						startC = j;
					}
				}
			}
			
			distList.add(0, new int[] {startR, startC});
			
			int[][] distance = new int[distList.size()][distList.size()];
			
			for(int i = 0; i < distance.length; i++) {
				bfs(distance, map, distList.get(i)[0], distList.get(i)[1], i);
			}
			
			boolean check = true;
			A: for(int i = 0; i < distance.length; i++) {
				for(int j = 0; j < distance.length; j++) {
					if(distance[i][j] != 0) continue A;
				}
				
				System.out.println(-1);
				check = false;
				break;
			}
			
			if(check) {
				permutation(0, distance, new int[distance.length], new boolean[distance.length]);
				
				System.out.println(answer == 0 ? -1 : answer);
			}
			
			st = new StringTokenizer(reader.readLine());
			c = Integer.parseInt(st.nextToken());
			r = Integer.parseInt(st.nextToken());
		}
	}
	
	static int[] dr = {0, 0, -1, 1};
	static int[] dc = {-1, 1, 0, 0};
	public static void bfs(int[][] distance, int[][] map, int startR, int startC, int index) {
		Queue<int[]> que = new LinkedList<>();
		boolean[][] check = new boolean[map.length][map[0].length];
		que.add(new int[] {startR, startC});
		check[startR][startC] = true;
		
		int count = 1;
		
		while(!que.isEmpty()) {
			int queSize = que.size();
			
			for(int size = 0; size < queSize; size++) {
				int[] now = que.poll();
				int nowR = now[0];
				int nowC = now[1];
				
				for(int i = 0; i < 4; i++) {
					int togoR = nowR + dr[i];
					int togoC = nowC + dc[i];
					
					if(togoR >= 0 && togoR < map.length && togoC >= 0 && togoC < map[0].length
							&& map[togoR][togoC] != -1
							&& !check[togoR][togoC]) {
						if(map[togoR][togoC] != 0) {
							distance[index][map[togoR][togoC]] = count;
						}
						
						que.add(new int[] {togoR, togoC});
						check[togoR][togoC] = true;
					}
				}
			}
			count++;
		}
	}
	
	public static void permutation(int index, int[][] distance, int[] list, boolean[] check) {
		if(index == distance.length - 1) {
			int total = 0;
			int prev = 0;
			for(int i = 0; i < list.length; i++) {
				if(distance[prev][list[i]] != 0) {
					total += distance[prev][list[i]];
					prev = list[i];
				}
				
			}
			
			if(total < answer)
				answer = total;
			
			return;
		}
		
		for(int i = 1; i < distance.length; i++) {
			if(!check[i - 1]) {
				list[index] = i;
				check[i - 1] = true;
				permutation(index + 1, distance, list, check);
				check[i - 1] = false;
			}
		}
	}

}
