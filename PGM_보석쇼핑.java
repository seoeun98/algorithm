package work;

import java.util.HashMap;
import java.util.Map.Entry;

public class PGM_보석쇼핑 {
	
	/*
	 * 문제를 딱 보자마자 슬라이딩 윈도우 방법이 떠올랐다.
	 * 부분 배열의 크기를 1부터 n까지 증가시키면서 현재의 부분 배열이 조건에 맞는지를 검사했고, 맞으면 start와 end의 인덱스를 리턴했다.
	 * 답은 맞게 나오는 것 같은데 시간 초과때문에 실패했다.
	 * 찾아보니 투포인터 기법이라는 게 있었다. 슬라이딩 윈도우와 비슷한데 살짝 달랐다.
	 * 슬라이딩 윈도우는 부분 배열의 크기가 고정적일 때, 투포인터는 부분 배열의 크기가 가변적일 때 사용한다고 한다. (물론 두 기법 다 연속적인 부분 배열에서 써야 한다.)
	 * 현재 부분 배열이 조건에 해당하지 않으면 end를 증가하여 탐색 범위를 넓히고, 조건에 해당하면 start를 증가하여 탐색 범위를 좁혀나가면서 부분 배열을 찾는다.
	 * 그리고 start가 배열 끝에 다다르면 탐색을 종료하면 되는 간단한 방식이다.
	 * 이 문제에서는 부분 배열의 크기까지 검사하여 제일 일찍 나오는 최소 범위를 찾도록 했다.
	 * 정확성 테스트에서는 잘 작동했지만, 효율성 테스트에서는 절반 이상 실패했다..
	 */
	
	
	public static void main(String[] args) {
		int[] result = solution(new String[] {"ZZZ", "YYY", "NNNN", "YYY", "BBB"});
		System.out.println(result[0] + " " + result[1]);
	}

    public static int[] solution(String[] gems) {
    	int[] answer = new int[2];
    	
    	HashMap<String, Integer> gemKind = new HashMap<>(); //있는 보석의 종류
    	
    	for(String gem : gems) {
    		gemKind.put(gem, 0);
    	}
    	
    	int start = 0;
    	int end = 0;
    	
    	for(int i = start; i <= end; i++) {
    		String gemName = gems[i];
    		int gemCount = gemKind.get(gemName) + 1;
    		gemKind.put(gemName, gemCount);	
    	}
    	
    	
    	int distance = gems.length;
    	
    	while(start < gems.length) {
    		boolean check = true;
    		
    		if(check(gemKind)) { //다 있으면
    			if(distance > (end - start)) {
    				distance = (end - start);
    				answer[0] = start + 1;
    				answer[1] = end + 1;
    			}
    			gemKind.put(gems[start], gemKind.get(gems[start]) - 1);
    			start++;
    			check = false;
    		} else if(check && end == gems.length - 1) {
    			break;
    		}
    		else { //다 없어
    			end++;
    			gemKind.put(gems[end], gemKind.get(gems[end]) + 1);
    		}
    		
    	}
    	
    	return answer;
    }
    
    public static boolean check(HashMap<String, Integer> kind) {
    	
		boolean check = true;
		//부분 배열에 모든 보석이 들어갔는지 확인
		for(Entry<String, Integer> gem : kind.entrySet()) {
			if(gem.getValue() < 1) { //하나라도 포함이 안되어있으면
				check = false;
			}
		}

    	return check;
    }
}




//import java.util.HashMap;
//import java.util.Map.Entry;
//class Solution {
//    public int[] solution(String[] gems) {
//    	HashMap<String, Integer> kind  = new HashMap<>(); //보석의 종류를 담고 있는 map
//    	int[] answer = new int[2];
//    	
//    	
//    	for(int i = 1; i <= gems.length; i++) { //부분배열의 크기
//    		for(String gem : gems) {
//    			kind.put(gem, 0);
//    		}
//    		
//    		String[] part = new String[i];
//    		
//    		int start = 0;
//    		int end = part.length;
//    		
//			for(int k = start; k < end; k++) { //부분 배열 안에 들어갈 보석들
//				String gemName = gems[k];
//				int gemCount = kind.get(gemName) + 1;
//				kind.put(gemName, gemCount);
//			}
//			
//			if(check(kind)) {
//				answer[0] = start + 1;
//				answer[1] = end;
//				return answer;
//			}
//			
//			while(++end <= gems.length) {
//				//시작 index를 하나 늘려줘야하니까 원래 start는 빼고
//				String gemName = gems[start];
//				int gemCount = kind.get(gemName) - 1;
//				kind.put(gemName, gemCount);
//				start++;
//				
//				//새로운 end를 추가
//				gemName = gems[end - 1];
//				gemCount = kind.get(gemName) + 1;
//				kind.put(gemName, gemCount);
//				
//				if(check(kind)) {
//					answer[0] = start + 1;
//					answer[1] = end;
//					return answer;
//				}
//			}
//    		
//    	}
//    	
//    	
//        return answer;
//    }
//    
//    public boolean check(HashMap<String, Integer> kind) {
//    	
//		boolean check = true;
//		//부분 배열에 모든 보석이 들어갔는지 확인
//		for(Entry<String, Integer> gem : kind.entrySet()) {
//			if(gem.getValue() < 1) { //하나라도 포함이 안되어있으면
//				check = false;
//			}
//		}
//
//    	return check;
//    }
//}
