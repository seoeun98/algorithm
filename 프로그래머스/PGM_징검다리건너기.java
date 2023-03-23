package work;

import java.util.Deque;
import java.util.LinkedList;


/*
 * 이분탐색으로 수 정해가면서 건널 수 있는 최댓값을 탐색
 * 1. 수 정하기 middle
 * 2. 건널 수 있는지 확인 (sliding window)
 * 3. 건널 수 없다면 end = middle, 건널 수 있다면 answer = middle 갱신 / start = middle + 1 로 재탐색
 */
public class PGM_징검다리건너기 {

	public static void main(String[] args) {
		System.out.println(solution(new int[] {2}, 1));
	}

    public static int solution(int[] stones, int k) {
        int answer = 0;
        
        int end = 0;
        for(int i : stones) {
        	if(i > end) {
        		end = i;
        	}
        }
        
        int start = 0;
        
        if(stones.length == 1) {
        	return stones[0];
        }
        
        while(start < end) {
        	int middle = (start + end) / 2;
        	
        	if(check(stones, middle, k)) {
        		answer = middle;
        		start = middle + 1;
        	} else { //통과 못함. end 줄이기
        		end = middle;
        	}
        }
        return answer;
    }
    
    
    public static boolean check(int[] stones, int middle, int k) {
    	int count = 0;
    	Deque<Integer> que = new LinkedList<>();
    	
    	for(int i = 0; i < k; i++) {
    		int j = stones[i] - middle;
    		que.addLast(j);
    		
    		if(j < 0) count++;
    		
    		if(count == k) { 
    			return false;
    		}
    		
    	}
    	
    	
    	for(int i = k; i < stones.length; i++) {
    		int j = stones[i] - middle;
    		if(que.pollFirst() < 0) {
    			count--;
    		}
    		que.addLast(j);
    		
    		if(j < 0) count++;
    		
    		if(count == k) { 
    			return false;
    		}
    		
    	}
    	
    	return true;
    }
    
}
