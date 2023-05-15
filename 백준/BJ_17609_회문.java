package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 봐주는 건 하나만.
 * 
 * 끝과 끝에서 시작해서 문자가 다를 경우에 아래 과정을 시행한다
 * 왼쪽 문자를 삭제했을 때/오른쪽 문자를 삭제했을 때를 비교한다.
 * 만약에 두 경우 중 하나라도 1이 리턴되는 경우(하나를 삭제하면 나머지는 똑같은 경우)가 있다면 ok
 * 두 경우 모두에서 2가 리턴되는 경우 == 회문을 만들기 위해서는 2개 이상의 문자를 삭제해야 함
 * 
 */
public class BJ_17609_회문 {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		
		int t = Integer.parseInt(st.nextToken());
		
		StringBuilder sb = new StringBuilder();
		for(int test = 0; test < t; test++) { // 30
			st = new StringTokenizer(reader.readLine());
			
			String string = st.nextToken();
			
			int right = string.length() - 1;
			int left = 0;
			
			int count = 0;
			
			while(right > left) {
				if(string.charAt(right) == string.charAt(left)) {
					right--;
					left++;
				} else {
					count = Math.min(check(string, right - 1, left), check(string, right, left + 1));
					break;
				}
			}
			
			if(count >= 2) {
				sb.append("2\n");
			} else if(count == 1) {
				sb.append("1\n");
			} else {
				sb.append("0\n");
			}
		}
		
		System.out.println(sb.toString());
	}
	
	public static int check(String chars, int right, int left) {
		
		while(right > left) {
			if(chars.charAt(right) == chars.charAt(left)) {
				right--;
				left++;
			} else {
				return 2;
			}
		}
		
		return 1;
	}

}
