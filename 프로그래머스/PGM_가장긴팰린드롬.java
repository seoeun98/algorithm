package work;
/*
 * 시간초과에서 하나 틀림
 */

public class PGM_가장긴팰린드롬 {
	 public int solution(String s)
	    {
	        int answer = 0;

	        A: for(int i = s.length(); i > 0; i--) {//문자열 길이
	            for(int j = 0; j < s.length() - i + 1; j++) { //시작하는 위치
	                boolean check = true;
	                
	                int start = j;
	                int end = j + i - 1;
	                
	                while(start <= end) {
	                    if(s.charAt(start++) != s.charAt(end--)) {
	                        check = false;
	                    }
	                }
	                if(check) {
	                    answer = i;
	                    break A; 
	                } 
	                
	            }
	        }

	        return answer;
	    }
}
