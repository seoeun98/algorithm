package work;

/*
 * 최대 부분합 알고리즘인 '카데인 알고리즘'을 학습해보자
 */

public class PGM_연속펄스부분수열의합 {
    public static  long solution(int[] sequence) {
        int multi = -1;

        for(int i = 0; i < sequence.length; i++) {
        	sequence[i] *= multi;
            multi *= -1;
        }
        
        long answer = Math.abs(tmp(sequence));
        
        return answer;
    }
    
    public static long tmp(int[] list) {
        long[] plus = new long[list.length + 1]; 
        
        long max = plus[0];
        long min = plus[0];  

        for(int i = 0; i < list.length; i++) {
        	plus[i + 1] += plus[i] + list[i];
 
            if(plus[i + 1] > max) {
                max = plus[i + 1];
            }
            
            if(plus[i + 1] < min) {
            	min = plus[i + 1];
            }
        }
        
        return max - min;
    }
}
