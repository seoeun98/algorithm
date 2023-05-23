package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 통과는 했지만 알고리즘상 최대 n*n*100이라서 데이터가 추가될 경우 시간제한에 걸릴듯
 */
public class BJ_2179_비슷한단어 {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		
		String[] list = new String[n];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(reader.readLine());
			
			list[i] = st.nextToken();
		}
		
		String first = "";
		String second = "";
		int max = 0;
		for(int i = 0; i < n; i++) { //기준
			for(int j = i + 1; j < n; j++) { //비교 대상
				int index = 0;
				while(list[i].length() > index &&
						list[j].length() > index &&
						list[i].charAt(index) == list[j].charAt(index)) {
					index++;
				}
				
				if(max < index) {
					max = index;
					first = list[i];
					second = list[j];
				}
			}
		}
		
		System.out.println(first);
		System.out.println(second);
	}

}
