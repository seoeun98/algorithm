package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 정올에서는 정답 처리인데 백준에서는 메모리 초과 뜸...
 * 대체 어디서 메모리 터지는 건지 모르겠다
 */

public class BJ_9935_문자열폭발 {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		
		StringBuffer line = new StringBuffer(st.nextToken());
		
		List<Character> list = new ArrayList<>();
		
		st = new StringTokenizer(reader.readLine());
		String boom = st.nextToken();
		
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < line.length(); i++) {
			
			//지금 문자를 넣으면 폭발 문자열이 되는지 검사
			list.add(line.charAt(i));
			
			int size = list.size();
			
			if(size >= boom.length()) {
				for(int j = boom.length(); j > 0; j--) {
					sb.append(list.get(size - j));
				}
				
				//폭발 문자열이면
				if(sb.toString().equals(boom)) {
					for(int j = 0; j < boom.length(); j++) {
						list.remove(list.size() - 1);
					}
				}
			}
			
			sb.delete(0, sb.length());
		}
		
		if(list.size() == 0) {
			System.out.println("FRULA");
		} else {
			StringBuilder s = new StringBuilder();
			
			for(char c : list) {
				s.append(c);
			}
			
			System.out.println(s.toString());
		}
	}

}
