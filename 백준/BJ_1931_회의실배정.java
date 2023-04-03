package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_1931_회의실배정 {
	
	static class Room implements Comparable<Room> {
		int start, end;

		public Room(int start, int end) {
			this.start = start;
			this.end = end;
		}

		//끝나는 시간이 빠른 거 먼저-end 오름차순
		@Override
		public int compareTo(Room o) {
			if(this.end == o.end) {
				return this.start - o.start;
			}
			
			return this.end - o.end;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(reader.readLine());

		int n = Integer.parseInt(st.nextToken());
		PriorityQueue<Room> que = new PriorityQueue<>();
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(reader.readLine());
			
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			
			que.add(new Room(start, end));
		}
		
		int count = 0;
		int prevEnd = -1;
		while(!que.isEmpty()) {
			Room room = que.poll();
			
			//직전 회의가 끝난 이후에만 시작 가능
			if(prevEnd > room.start) {
				continue;
			}
			count++;
			
			prevEnd = room.end;
		}
		
		System.out.println(count);
		
	}

}
