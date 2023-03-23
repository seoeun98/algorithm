package work;

public class PGM_다트게임 {

	public static void main(String[] args) {
		solution("1S2D*3T");
	}

	public static int solution(String dartResult) {        
        int[] results = new int[3];
        char[] cs = dartResult.toCharArray();
        int count = 0;
        
        boolean check = true;
        for(int i = 0; i < cs.length; i++) {	
        	if(cs[i] >= '0' && cs[i] <= '9') {
        		if(check) {
        			results[count] = cs[i] - '0';
        			check = false;
        		} else {
        			results[count] = results[count] * 10 + (cs[i] - '0');
        		}
        	}
        	
        	else if(cs[i] >= 'D' && cs[i] <= 'T') {
        		if(cs[i] == 'D') {
        			results[count] *= results[count];
        		} else if(cs[i] == 'T') {
        			results[count] *= results[count] * results[count];
        		}
        		check = true;
        		count++;
        	}
        	
        	else {
        		count--;
        		if(cs[i] == '*') {
        			results[count] *= 2;
        			
        			if(count != 0) {
        				results[count - 1] *= 2;
        			}
        		} else {
        			results[count] *= -1;
        		}
        		count++;
        	}
        	
        }
        
        return results[0] + results[1] + results[2];
    }
}
