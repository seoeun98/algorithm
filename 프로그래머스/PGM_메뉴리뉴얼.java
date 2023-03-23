package work;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class PGM_메뉴리뉴얼 {

	static HashMap<String, Integer> map; //해당 세트메뉴가 불린 횟수
	static HashMap<Integer, Integer> Max = new HashMap<>(); //n개로 구성되어야하는 세트 메뉴가 불린 최대 횟수
	public static void main(String[] args) {
		map = new HashMap<>();
		
		solution(new String[] {"XYZ", "XWY", "WXA"}, new int[] {2,3,4});
	}

    public static String[] solution(String[] orders, int[] course) {
        String[] answer = {};
        
        ArrayList<String> tmp = new ArrayList<>();
        
        //map 구성
        for(int i = 0; i < orders.length; i++) {
        	char[] order = orders[i].toCharArray();
        	part(order, new boolean[order.length], 0, course, 0);
        }
        
        for(String key : map.keySet()) {
        	int count = map.get(key);
        	
        	if(Max.get(key.toCharArray().length) == count && count >= 2) {
        		tmp.add(key);
        	}
        }
        
        answer = new String[tmp.size()];
        
        int index = 0;
        for(String name : tmp) {
        	answer[index++] = name;
        }
        
        Arrays.sort(answer);
        
        return answer;
    }
    
    public static void part(char[] menu, boolean[] selected, int prev, int[] course, int count) {
    	if(prev == menu.length) {
    		for(int i = 0; i < course.length; i++) {
    			//만들기로 한 코스와 동일하면
    			if(course[i] == count) {
    				Arrays.sort(menu);
    				
    				String set = "";
    				for(int j = 0; j < selected.length; j++) {
    					if(selected[j]) {
    						set += menu[j];
    					}
    				}
    				
    				if(!map.containsKey(set)) {
    					map.put(set, 1);
    				} else {
    					map.put(set, map.get(set) + 1);
    				}
    				
    				if(!Max.containsKey(count)) {
    					Max.put(count, map.get(set));
    				}
    				else if(Max.get(count) < map.get(set)) { //count개로 구성되어야하는 세트가 불린 최대 횟수 갱신
    					Max.put(count, map.get(set));
    				}
    			}
    		}
    		
    		return;
    	}
    	
    	//해당 메뉴를 코스에 넣기로 했을 때
    	selected[prev] = true;
    	part(menu, selected, prev + 1, course, count + 1);
    	
    	//해당 메뉴를 코스에 넣지 않기로 했을 때
    	selected[prev] = false;
    	part(menu, selected, prev + 1, course, count);
    	
    }
}
