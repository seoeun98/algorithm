package work;

public class PGM_표현가능한이진트리 {

	public static void main(String[] args) {
		int[] answer = solution(new long[] {});
		
		for(int i : answer) {
			System.out.print(i + " ");
		}
	}
	
	static int canOrNot;
    public static int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        
        for(int numberIndex = 0; numberIndex < numbers.length; numberIndex++) {
        	long number = numbers[numberIndex];
        	
        	String bString = Long.toBinaryString(number); //수를 이진수로 변환
        	
        	int bStringSize = bString.length();
        	int pow = 1;
        	
        	while(true) {
        		if(bStringSize <= Math.pow(2, pow) - 1) {
        			break;
        		}
        		pow++;
        	}
        	
        	String tmp = "";
        	for(int i = 0; i < Math.pow(2, pow) - 1 - bStringSize; i++) {
        		tmp += "0";
        	}
        	
        	bString = tmp + bString;
        	
        	char[] bits = bString.toCharArray(); //이진수 배열
        	
        	canOrNot = 1;
       	
        	check(0, bits.length - 1, bits);
        	
        	answer[numberIndex] = canOrNot;
        }
        
        return answer;
    }
    
    public static void check(int start, int end, char[] bits) {
    	if(start == end) {
    		return;
    	}
    	
    	int mid = (start + end) / 2;
    	int leftChild = (start + mid - 1) / 2;
    	int rightChild = (mid + 1 + end) / 2;
    	
    	if(bits[mid] == '0') { //자식이 있으면 안되는데
    		if(bits[leftChild] == '1' || bits[rightChild] == '1') { //자식이 있네
    			canOrNot = 0;
    			return;
    		}
    	}
    	
    	check(start, mid - 1, bits);
    	
    	if(canOrNot != 0) {
    		check(mid + 1, end, bits);
    	}
    }
   
}
