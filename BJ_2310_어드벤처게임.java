package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * 같은 곳을 재방문해야 도착할 수 있는 테스트 케이스까지 고려하면 시간초과
 * 같은 노드는 한 번만 방문할 수 있다고 가정하고 풀면 success
 * 테스트 케이스 부족으로 성공한듯
 */

public class BJ_2310_어드벤처게임 {
	
	static class Room {
		String type;
		int money;
		ArrayList<Integer> door;
		
		public Room(String type, int money) {
			this.type = type;
			this.money = money;
			door = new ArrayList<>();
		}
	}

	static Room[] rooms;
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(reader.readLine());
		int roomN = Integer.parseInt(st.nextToken());
				
		if(roomN != 0) {
			do {
				rooms = new Room[roomN];
				
				//각 방에 대한 정보 삽입
				for(int i = 0; i < roomN; i++) {
					st = new StringTokenizer(reader.readLine());
					
					String type = st.nextToken();
					int money = Integer.parseInt(st.nextToken());
					
					rooms[i] = new Room(type, money);
					
					int doorN = Integer.parseInt(st.nextToken()) - 1;
					while(doorN != -1) {
						rooms[i].door.add(doorN);
						doorN = Integer.parseInt(st.nextToken()) - 1;
					}
				}
				
				boolean[] check = new boolean[roomN];
				check[0] = true;
				if(dfs(0, check, roomN, 0)) {
					System.out.println("Yes");
				} else {
					System.out.println("No");
				}
				
				st = new StringTokenizer(reader.readLine());
				roomN = Integer.parseInt(st.nextToken());

			} while(roomN != 0);
		}
	}
	
	/**
	 * 
	 * @param index 현재 내가 있는 방 번호
	 * @param maxMoney 
	 * @param roomN 도달해야 하는 방 번호 + 1
	 * @param money 현재 내가 가지고 있는 돈
	 * @return
	 */
	public static boolean dfs(int index, boolean[] check, int roomN, int money) {
		if(index == roomN - 1) {
			return true;
		}
		
		for(int door : rooms[index].door) {
			Room room = rooms[door];
			
			if(!check[door]) {
				
				int tmpMoney = money;
				
				if(room.type.equals("L")) {
					tmpMoney = Math.max(tmpMoney, room.money);
				}
				if(room.type.equals("T")) {
					if(room.money <= money)
						tmpMoney = money - room.money;
					else continue;
				}
				
				check[door] = true;
				if(dfs(door, check, roomN, tmpMoney)) {
					return true;
				}
				check[door] = false;
			}
			
		}
		
		return false;
	}
	
	
//	public static boolean dfs(int index, boolean[] check, int roomN, int money, int[] maxMoney) {
//		if(index == roomN - 1) {
//			return true;
//		}
//		
//		for(int door : rooms[index].door) {
//			Room room = rooms[door];
//			
//			if(!check[door] || maxMoney[door] < money) {
//				
//				int tmpMoney = money;
//				
//				if(room.type.equals("L")) {
//					tmpMoney = Math.max(tmpMoney, room.money);
//				}
//				if(room.type.equals("T")) {
//					if(room.money <= money)
//						tmpMoney = money - room.money;
//					else continue;
//				}
//				
//				int tmpMaxMoney = maxMoney[door];
//				maxMoney[door] = tmpMoney;
//				check[door] = true;
//				if(dfs(door, check, roomN, tmpMoney, maxMoney)) {
//					return true;
//				}
//				check[door] = false;
//				maxMoney[door] = tmpMaxMoney;
//			}
//			
//		}
//		
//		return false;
//	}

}
