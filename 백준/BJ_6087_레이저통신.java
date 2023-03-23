package work;

//import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BJ_6087_레이저통신 {
	
//	static class Node {
//		int r, c;
//		int parent; //부모 인덱스
//		
//		public Node(int r, int c, int parent) {
//			this.r = r;
//			this.c = c;
//			this.parent = parent;
//		}
//	}

//	static int answer = 0;
	static char[][] map;
	static int mapSizeR, mapSizeC;
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		mapSizeC = input.nextInt();
		mapSizeR = input.nextInt();
		
		map = new char[mapSizeR][mapSizeC];
		
		int[] firstC = new int[2];
		int[] secondC = new int[2];
		boolean isFirstC = true;
		for(int i = 0; i < mapSizeR; i++) {
			String oneLine = input.next();
			
			for(int j = 0; j < mapSizeC; j++) {
				map[i][j] = oneLine.charAt(j);
				
				if(map[i][j] == 'C') {
					if(isFirstC) {
						firstC[0] = i;
						firstC[1] = j;
						isFirstC = false;						
					} else {
						secondC[0] = i;
						secondC[1] = j;
					}
				}
			}
		}
		
		int answer = bfs(firstC[0], firstC[1], secondC[0], secondC[1]);
		System.out.println(answer);
	}
	
	
	/*
	 * 카카오의 경주로 건설하기와 비슷한 문제
	 * 3차원으로 4방향에서 들어오는 값들을 개별로 관리
	 * 
	 * 3차원 배열에는 특정 방향에서 들어오는 값이 얼마인지가 저장된다.
	 *   
	 *     B
	 *     ↑
	 * C ← A → D
	 *     ↓
	 *     F
	 *   
	 * 위처럼 되어있을 때, A에서 B로 간다고 가정해보자
	 * 어느 방향에서 들어왔는가(x) 어떤 방향으로 들어왔는가(o)이므로 B의 ↑에 값이 저장된다.
	 * 
	 * 방금 A로 들어온 방향의 값 + B로 이동하는 이동값 = B의 ↑ 값
	 * 
	 * 시작점의 경우 '어떤 방향에서 어떤 값이 들어왔는가'가 존재하지 않는다.
	 * 그러므로 que의 첫 시작은 시작점이 아닌 '시작점 주변'이 된다.
	 */
	
	
	//왼쪽, 오른쪽, 위, 아래
	static int[] dr = {0, 0, -1, 1};
	static int[] dc = {-1, 1, 0, 0};
	
	static int max = 1000000000;
	public static int bfs(int startR, int startC, int endR, int endC) {
		Queue<int[]> que = new LinkedList<>();
		int[][][] count = new int[mapSizeR][mapSizeC][4];
		
		for(int i = 0; i < mapSizeR; i++) {
			for(int j = 0; j < mapSizeC; j++) {
				Arrays.fill(count[i][j], max);
			}
		}
		count[startR][startC][0] = 0;
		count[startR][startC][1] = 0;
		count[startR][startC][2] = 0;
		count[startR][startC][3] = 0;
		
		for(int i = 0; i < 4; i++) {
			int tmpR = startR + dr[i];
			int tmpC = startC + dc[i];
			
			if(tmpR >= 0 && tmpR < mapSizeR && tmpC >= 0 && tmpC < mapSizeC && map[tmpR][tmpC] != '*') {
				count[tmpR][tmpC][i] = 0;
				que.add(new int[] {tmpR, tmpC, i}); 
			}
		}
		
		while(!que.isEmpty()) {
			int[] now = que.poll();
			int nowR = now[0];
			int nowC = now[1];
			int nowD = now[2];
			
			for(int i = 0; i < 4; i++) {
				int togoR = nowR + dr[i];
				int togoC = nowC + dc[i];
				
				if(!(togoR >= 0 && togoR < mapSizeR && togoC >= 0 && togoC < mapSizeC && map[togoR][togoC] != '*')) { //갈 수 있는 위치가 아니면
					continue;
				}
				
				if(i + nowD == 1 || i + nowD == 5) { //역방향이면
					continue;
				}
				
				int mirrorN = 0;
				
				if(i != nowD) {
					if(i + nowD >= 2 && i + nowD <= 4) { //꺾이는 거면
						mirrorN = 1;
					}
				}
				
				if(count[nowR][nowC][nowD] + mirrorN < count[togoR][togoC][i]) {
					count[togoR][togoC][i] = count[nowR][nowC][nowD] + mirrorN;
					que.add(new int[] {togoR, togoC, i});
				}
				
				
			}
		}
		
		int min = 100000000;
		for(int i = 0; i < 4; i++) {
			if(count[endR][endC][i] < min) {
				min = count[endR][endC][i];
			}
		}
		
		return min;
	}
	
	
	/*
	 * bfs 경로 탐색하기
	 * 부모 인덱스를 저장하는 배열을 따로 만들고, (크기는 n * m. 왜? 한 칸은 한 번만 가니까 부모도 하나만 있음)
	 * 다음 경로(자식)를 탐색할 때 현재 경로(부모)를 저장한다.
	 * 마지막에 부모 배열을 통해서 역추적하면 됨.
	 * 
	 */
	
//	public static void bfs(int startR, int startC, int endR, int endC) {
//		int count = 0;
//		
//		Queue<int[]> que = new LinkedList<>();
//		que.add(new int[] {startR, startC, count});
//		
//		boolean[][] check = new boolean[mapSizeR][mapSizeC];
//		check[startR][startC] = true;
//		
//		ArrayList<Node> path = new ArrayList<>();
//		int index = -1;
//		path.add(new Node(startR, startC, index));
//		
//		while(!que.isEmpty()) {
//			index++;
//			int[] now = que.poll();
//			int nowR = now[0];
//			int nowC = now[1];
//			
//			if(nowR == endR && nowC == endC) {			
//				getTurnN(path, now[2]);
//				
//				return;
//			}
//			
//			for(int i = 0; i < 4; i++) {
//				int togoR = nowR + dr[i];
//				int togoC = nowC + dc[i];
//				
//				if(togoR >= 0 && togoR < mapSizeR && togoC >= 0 && togoC < mapSizeC && !check[togoR][togoC] && map[togoR][togoC] != '*') {
//					que.add(new int[] {togoR, togoC, ++count});
//					check[togoR][togoC] = true;
//					
//					path.add(new Node(togoR, togoC, index));
//				}
//			}
//		}
//	}
	
//	public static void getTurnN(ArrayList<Node> path, int startI) {
//		int index = startI;
//			
//		ArrayList<int[]> list = new ArrayList<>();
//		
//		while(true) {
//			Node n = path.get(index);
//			
//			list.add(new int[] {n.r, n.c});
//			
//			index = n.parent;
//			
//			if(index == -1) {
//				break;
//			}
//		}
//		
//		int sizeOfList = list.size();
//		
//		for(int i = sizeOfList - 1; i >= 2; i--) {
//			int[] nodeFirst = list.get(i);
//			int[] nodeSecond = list.get(i - 2);
//			
//			if(nodeFirst[0] != nodeSecond[0] && nodeFirst[1] != nodeSecond[1]) {
//				answer++;
//			}
//		}
//	}
	
}
