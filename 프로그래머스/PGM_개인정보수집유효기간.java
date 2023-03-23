package work;

import java.util.*;

public class PGM_개인정보수집유효기간 {

	public static void main(String[] args) {
		solution("2022.05.19", 
				new String[] {"A 6", "B 12", "C 3"},
				new String[] {"2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C"});
	}

	/**
	 * @param today 오늘 날짜
	 * @param terms 약관의 유효기간
	 * @param privacies 수집된 개인정보 정보
	 * @return 파기해야 할 개인정보 정보를 오름차순으로
	 */
    public static int[] solution(String today, String[] terms, String[] privacies) {
        int[] answer = new int[privacies.length];
        HashMap<Integer, Integer> pri = new HashMap<>();
        HashMap<String, Integer> term = new HashMap<>();
        
        StringTokenizer st;
        
        st = new StringTokenizer(today, ".");
        int todayInt = Integer.parseInt(st.nextToken()) * 12 * 28 
        		+ Integer.parseInt(st.nextToken()) * 28
        		+ Integer.parseInt(st.nextToken());
        
        
        for(int i = 0; i < terms.length; i++) {
        	st = new StringTokenizer(terms[i]);
        	String name = st.nextToken();
        	int accessable = Integer.parseInt(st.nextToken());
        	
        	term.put(name, accessable * 28);
        }
        
        for(int i = 0; i < privacies.length; i++) {
        	st = new StringTokenizer(privacies[i]);
        	String when = st.nextToken();
        	String name = st.nextToken();
        	
        	st = new StringTokenizer(when, ".");
        	int year = Integer.parseInt(st.nextToken());
        	int month = Integer.parseInt(st.nextToken());
        	int day = Integer.parseInt(st.nextToken());
        	
        	pri.put(i, year * 12 * 28 + month * 28 + day + term.get(name));
        }
        
        int count = 0;
        for(int i = 0; i < privacies.length; i++) {
        	if(todayInt > pri.get(i)) {
        		answer[count++] = i + 1;
        	}
        }
        
        answer = Arrays.copyOfRange(answer, 0, count);
        Arrays.sort(answer);
        return answer;
    }
}
