package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 공들을 크기별 오름차순 정렬한다
 * 배열이 두 개 필요하다
 * 1. 같은 색 공들의 사이즈 합 배열
 * 2. 결과를 저장할 배열
 * 
 * 공들이 크기별 오름차순 정렬되어있기에 A보다 앞에 있는 공들은 A보다 작은 공들. 즉, A가 먹을 수 있는 공들
 * A 앞에 있는 공들의 크기를 다 더하면 A가 얻을 수 있는 점수.
 * 근데 여기서 A와 같은 색의 공 크기들은 빼야 함. <-- 여기서 1번 배열 사용
 * 
 * 1부터 n번 공까지의 사이즈 합을 다 더해놓는 sum 변수 선언.
 * prevBall 변수 추가 선언 <-- sum에 어디까지 넣었는지를 체크하는 변수
 * 
 * for(i : 1~n) 돌면서 i번째 공의 크기가 prevBall번째 공보다 크다면(먹을 수 있는 공이라면) sum에 저장. prevBall번째 공을 sum에 넣었으니까 prevBall++
 * 크지 않다면(같다면) 먹을 수 없는 공이므로 패스 
 * result[i]는 sum에서 같은 색들의 사이즈 합을 뺀 값
 * 
 */


public class BJ_10800_컬러볼 {
	static class Ball implements Comparable<Ball> {
		int index;
		int color;
		int size;
		
		public Ball(int index, int color, int size) {
			this.index = index;
			this.color = color;
			this.size = size;
		}

		@Override
		public int compareTo(Ball o) {
			return this.size - o.size;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		
		Ball[] balls = new Ball[n + 1]; //n개의 Ball 배열
		balls[0] = new Ball(0, 0, 0);
		for(int i = 1; i <= n; i++) {
			st = new StringTokenizer(reader.readLine());
			
			int color = Integer.parseInt(st.nextToken());
			int size = Integer.parseInt(st.nextToken());
			
			balls[i] = new Ball(i, color, size);
		}
		
		Arrays.sort(balls);
		
		int[] sameColor = new int[n]; //같은 색 & 더 작은 공들의 사이즈 합
		int[] result = new int[n + 1]; //n개의 공들이 얻을 수 있는 점수
		
		int sum = 0;
		int prevB = 0;
		for(int i = 1; i <= n; i++) {
			Ball now = balls[i];
			
			while(balls[prevB].size < now.size) {
				sum += balls[prevB].size;
				sameColor[balls[prevB].color] += balls[prevB].size;
				prevB++;
			}
			
			result[now.index] = sum - sameColor[now.color];
		}
		
		for(int i = 1; i <= n; i++) {
			System.out.println(result[i]);
		}

	}

}
