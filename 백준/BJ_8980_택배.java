package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 그리디 접근 방식.
 * 
 * 여러 방식을 생각해볼 수 있다
 * 1. 출발지가 빠른 순서대로 싣기
 * 2. 많이or적게 전달해야하는 순서대로 싣기
 * 3. 도착지가 빠른 순서대로 싣기
 * 등등...
 * 
 * 이 문제에서는 3번 방식이 적합하다.
 * 도착치가 빠른 순서대로 정렬하는 우선순위큐에 넣는다
 * 
 * 1 2 3 4 5 6 7 8 9 10
 * 마을이 이렇게 있을 때 우선순위큐 제일 앞에 있는 게 1->4, 10이라고 가정하자
 * 그러면 1 마을 뿐만 아니라 2, 3 마을도 10의 배달공간이 필요하다(4 마을까지 가기 위해 무조건 거쳐야 하므로 어쩔 수 없다)
 * 
 * 일단 각 마을에서 보낼 수 있는 최대 배달량을 c로 잡아놓고,
 * 큐에서 하나씩 꺼내면서 각 마을의 배달 가능량을 줄여간다
 * 
 * 
 */

public class BJ_8980_택배 {
	
	static class Mail implements Comparable<Mail> {
		int start; //보내는 마을
		int end; //받는 마을
		int volume; //용량
		
		public Mail(int start, int end, int volume) {
			super();
			this.start = start;
			this.end = end;
			this.volume = volume;
		}

		@Override
		public int compareTo(Mail o) {
			if(this.end == o.end) { //이건 안해줘도 영향이 없다
				return this.start - o.start;
			}
			return this.end - o.end;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		
		int n = Integer.parseInt(st.nextToken()); //마을 수
		int c = Integer.parseInt(st.nextToken()); //트럭 용량
		
		st = new StringTokenizer(reader.readLine());
		
		int m = Integer.parseInt(st.nextToken());
		
		PriorityQueue<Mail> que = new PriorityQueue<Mail>();
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(reader.readLine());
			int start = Integer.parseInt(st.nextToken()) - 1;
			int end = Integer.parseInt(st.nextToken()) - 1;
			int v = Integer.parseInt(st.nextToken());
			
			que.add(new Mail(start, end, v));
		}
		
		int count = 0;
		
		//각 마을에서 보낼 수 있는 택배의 양. 마지막 마을은 택배를 보낼 수 없으므로 제외
		int[] sendMax = new int[n - 1]; 
		Arrays.fill(sendMax, c); //처음에는 모두 최대 택배 용량만큼 보낼 수 있음
		
		while(!que.isEmpty()) {
			Mail mail = que.poll();
			
			int start = mail.start;
			int end = mail.end;
			int v = mail.volume;
			
			int max = v;
			for(int i = start; i < end; i++) {
				if(sendMax[i] < max) {
					max = sendMax[i];
				}
			}
			
			count += max;
			for(int i = start; i < end; i++) {
				sendMax[i] -= max;
			}
		}

		System.out.println(count);
	}

}
