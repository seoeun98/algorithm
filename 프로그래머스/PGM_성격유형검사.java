package work;

import java.util.HashMap;

public class PGM_성격유형검사 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public String solution(String[] survey, int[] choices) {
        String answer = "";

        HashMap<Character, Integer> map = new HashMap<>();
        map.put('A', 0);
        map.put('N', 0);
        map.put('C', 0);
        map.put('F', 0);
        map.put('M', 0);
        map.put('J', 0);
        map.put('R', 0);
        map.put('T', 0);
        
        for(int i = 0; i < survey.length; i++) {
        	int firstS = 8 - choices[i];
        	int secondS = choices[i];
        	
        	char firstC = survey[i].charAt(0);
        	char secondC = survey[i].charAt(1);
        	
        	map.put(firstC, map.get(firstC) + firstS);
        	map.put(secondC, map.get(secondC) + secondS);
        }
        
        if(map.get('R') >= map.get('T')) {
        	answer += 'R';
        } else {
        	answer += 'T';
        }
        
        if(map.get('C') >= map.get('F')) {
        	answer += 'C';
        } else {
        	answer += 'F';
        }
        
        if(map.get('J') >= map.get('M')) {
        	answer += 'J';
        } else {
        	answer += 'M';
        }
        
        if(map.get('A') >= map.get('N')) {
        	answer += 'A';
        } else {
        	answer += 'N';
        }
        
        
        return answer;
    }
}
