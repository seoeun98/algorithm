package work;

public class PGM_문자열압축 {

	public static void main(String[] args) {
		System.out.println(solution("aabbaccc"));
	}

    public static int solution(String s) {
        int answer = 99999999;
        
        int size = s.length();
        
        //길이가 1일 경우 바로 1 리턴
        if(size == 1) {
        	return 1;
        }
        
        for(int length = size / 2; length >= 1; length--) { //비교할 문자열의 길이
        	String pattern = s.substring(0, length); //비교할 문자열
        	
        	int sameCount = 1; //같은 경우 카운트
        	
        	String maked =""; //만들어진 문자열
        	
        	int compareStart;
        	for(compareStart = length; compareStart <= size - length; compareStart += length) { //비교되는 문자열의 시작 위치
        		String compared = s.substring(compareStart, compareStart + length); //비교되는 문자열
        		
        		if(compared.equals(pattern)) { //같다면
        			sameCount++;
        		} else { //다르다면
        			//만들어진 문자열에 추가해주고
        			if(sameCount >= 2) {
        				maked += sameCount;
        			}
        			maked += pattern; 
        			
        			//비교할 문자열 갱신. 방금 비교당한 문자열이 비교될 문자열이 된다
        			pattern = compared; 
        			sameCount = 1;
        		}
        	}
        	
        	//for문 조건상 제일 마지막의 pattern과 동일횟수는 maked에 추가되지 못한 상태이므로 추가해줘야 함.
        	if(sameCount >= 2) {
        		maked += sameCount;
        	}
        	maked += pattern; 
        	        	
        	//만들어진 문자열이 최소 길이라면 답 갱신
        	if(answer > maked.length() + (size % length)) {
        		answer = maked.length() + (size % length);
        	}
        }
        
        return answer;
    }
}
