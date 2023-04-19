package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 이분 탐색
 * 인덱스가 아닌 거리를 기준으로 탐색한다
 * 해당 거리만큼 차이나게 공유기를 설치할 수 있다면 거리를 늘리고,
 * 설치할 수 없다면 거리를 좁혀가면서 최대 거리 탐색
 */

public class BJ_2110_공유기설치 {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		
		int n = Integer.parseInt(st.nextToken()); //집의 수
		int c = Integer.parseInt(st.nextToken()); //공유기 수
		
		int[] house = new int[n];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(reader.readLine());
			house[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(house);
		
		int max = 0;
		int start = house[0];
		int end = house[house.length - 1];
		
		while(start <= end) {	
			int mid = (start + end) / 2;		
			int distance = mid - house[0];
			
			if(setable(distance, house, c)) {
				if(distance > max) {
					max = distance;
				}
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}
		
		System.out.println(max);
	}
	
	public static boolean setable(int distance, int[] house, int c) {
		int count = 1;
		
		int prev = 0;
		for(int i = 1; i < house.length; i++) {
			if(house[i] - house[prev] >= distance) {
				count++;
				prev = i;
				
				if(count == c)
					return true;
			}
		}
		
		return false;
	}

}
