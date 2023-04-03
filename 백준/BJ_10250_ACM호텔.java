package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_10250_ACM호텔 {
	
	static class Room implements Comparable<Room> {
		int r, c;
		int roomIndex;
		
		public Room(int r, int c, int roomIndex) {
			this.r = r;
			this.c = c;
			this.roomIndex = roomIndex;
		}

		//엘베에서 가까운 게 먼저 - c 오름차순, 낮은 게 먼저 - r 오름차순
		@Override
		public int compareTo(Room o) {
			if(this.c == o.c) {
				return this.r - o.r;
			} else {
				return this.c - o.c;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		
		int testCase = Integer.parseInt(st.nextToken());
		
		for(int t = 0; t < testCase; t++) {
			st = new StringTokenizer(reader.readLine());
			
			int mapR = Integer.parseInt(st.nextToken());
			int mapC = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			
			PriorityQueue<Room> rooms = new PriorityQueue<>();
			
			for(int i = 1; i <= mapR; i++) {
				for(int j = 1; j <= mapC; j++) {
					int roomIndex = i * 100 + j;
					rooms.add(new Room(i, j, roomIndex));
				}
			}
			
			for(int i = 0; i < n - 1; i++) {
				rooms.poll();
			}
			Room room = rooms.poll();
			
			System.out.println(room.roomIndex);
		}
	}

}
