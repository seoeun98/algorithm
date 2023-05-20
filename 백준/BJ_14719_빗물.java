package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/* 
 * 맵 크기가 최대 500*500이므로 완탐
 * 맵에 블록 세우고 높이별로 시작 블럭과 끝 블럭이 존재하는지 검사 및 블럭의 위치 인덱스 기록
 * 시작 블록과 끝 블록까지는 한 줄을 채울 수 있다는 뜻이니까 그 사이에 블록이 없는 칸이라면 물로 채우기 
 */

public class BJ_14719_빗물 {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		
		int mapH = Integer.parseInt(st.nextToken()); //세로
		int w = Integer.parseInt(st.nextToken()); //가로
		
		st = new StringTokenizer(reader.readLine());
		boolean[][] map = new boolean[w][mapH + 1];
		for(int i = 0; i < w; i++) {
			int h = Integer.parseInt(st.nextToken());
			
			for(int j = 1; j <= h; j++) {
				map[i][mapH - j] = true;
			}
		}
		
		int count = 0;
		for(int i = 0; i < mapH; i++) {
			int start = -1;
			int end = -1;
			
			for(int j = 0; j < w; j++) {
				if(map[j][i]) {
					if(start == -1) {
						start = j;
					} else if(end == -1 || end < j) {
						end = j;
					}
				}
			}
			
			for(int j = start + 1; j < end; j++) {
				if(!map[j][i]) {
					count++;
				}
			}
		}
		
		System.out.println(count);
		
	}

}
