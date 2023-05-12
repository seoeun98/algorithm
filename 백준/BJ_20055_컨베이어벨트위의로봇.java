package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 개같은 문제
 */
public class BJ_20055_컨베이어벨트위의로봇 {
	
	static class Belt {
		boolean used;
		int durablity;
		
		public Belt(boolean used, int durablity) {
			this.used = used;
			this.durablity = durablity;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		
		int n = Integer.parseInt(st.nextToken()); //컨베이어 벨트 길이
		int k = Integer.parseInt(st.nextToken()); //0인 칸의 갯수 제한
		
		int count = 0; //내구도가 0인 칸의 수
		
		st = new StringTokenizer(reader.readLine());
		Belt[] belts = new Belt[n * 2];
		for(int i = 0; i < n * 2; i++) {
			int durablity = Integer.parseInt(st.nextToken());
			
			belts[i] = new Belt(false, durablity);
			
			if(durablity == 0) {
				count++;
			}
		}
		
		int answer = 1;
		while(true) {
			//1단계 - 벨트 회전
			Belt tmp = belts[n * 2 - 1];
			
			for(int i = n * 2 - 1; i > 0; i--) {
				belts[i] = belts[i - 1];
				
				if(i == n && belts[i].used) {
					belts[i].used = false;
				}
			}
			
			belts[0] = tmp;
			
			//2단계 - 로봇 이동
			for(int i = n - 1; i >= 0; i--) {
				if(belts[i].used) {
					if(i + 1 == n) {
						belts[i].used = false;
					} else if(!belts[i + 1].used && belts[i + 1].durablity > 0) {
						belts[i + 1].used = true;
						belts[i + 1].durablity--;
						
						if(belts[i + 1].durablity == 0) {
							count++;
						}
						
						belts[i].used = false;
					}
				}
			}
			
			//3단계 - 로봇 올리기
			if(belts[0].durablity > 0) {
				belts[0].used = true;
				belts[0].durablity--;
				
				if(belts[0].durablity == 0) {
					count++;
				}
			}
			
			if(count >= k) {
				break;
			}
			
			answer++;
		}
		
		System.out.println(answer);
	}
}
