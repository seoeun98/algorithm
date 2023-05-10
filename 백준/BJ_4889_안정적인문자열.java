package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 괄호 문제가 나오면 stack/que 접근!
 * 
 * que에는 {만 삽입
 * 
 * while문을 통해 문자열 탐색
 * {가 들어올 경우 : 그냥 que에 넣어줌
 * }가 들어올 경우 : que에 {가 있는지 확인. 있으면 que에서 하나 빼고 없으면 }를 {로 변환
 * 
 * while이 끝난 이후에도 que에 담겨있는 애들 == 불안정한 애들
 * que에 담겨있는 요소 반을 }로 바꿔버리면 갯수 딱 맞게 안정적으로 됨
 */

public class BJ_4889_안정적인문자열 {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
		int index = 1;
		
		while(true) {
			StringTokenizer st = new StringTokenizer(reader.readLine());
			String line = st.nextToken();
			
			if(line.contains("-")) {
				break;
			}
			
			Queue<Character> que = new LinkedList<>();
			
			int answer = 0;	
			for(int i = 0; i < line.length(); i++) {
				if(line.charAt(i) == '{') {
					que.add('{');
				} else {
					if(que.isEmpty()) {
						answer++;
						que.add('{');
					} else {
						que.poll();
					}
				}
			}
			
			answer += que.size() / 2;
			
			System.out.println(index + ". " + answer);
			index++;
		}
	}
}
