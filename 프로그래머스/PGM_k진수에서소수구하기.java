package work;

import java.util.StringTokenizer;

public class PGM_k진수에서소수구하기 {

	public static void main(String[] args) {
		solution(437674, 3);
	}

    public static int solution(int n, int k) {
        int answer = 0;
        
        //k진수 구하기 - 나머지 얻기
        String list = "";
        while(n >= 1) {
        	list += n % k;
        	n /= k;
        }
        //k진수 구하기 - 뒤집어서 k진수 완성
        char[] chList = list.toCharArray();
        String result = ""; //k진수 완성본
        for(int i = chList.length - 1; i >= 0; i--) {
        	result += chList[i];
        }
        
        StringTokenizer st = new StringTokenizer(result, "0"); //0으로 나누기
        
        while(st.hasMoreTokens()) {
        	int number = Integer.parseInt(st.nextToken());
        	if(isIt(number)) answer++;
        }
        
        return answer;
    }
    
    public static boolean isIt(int n) {
    	boolean check = true;
    	
    	for(int i = 2; i < n; i++) {
    		if(n % i == 0) {
    			check = false;
    			break;
    		}
    	}
    	
    	return check;
    }
}
