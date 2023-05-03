package work;

import java.util.HashMap;

/*
 * 곡괭이 순열 만들어서 완탐
 */

public class PGM_광물캐기 {

	public static void main(String[] args) {
		solution(new int[] {1, 3, 2}, new String[] {"diamond", "diamond", "diamond", "iron", "iron", "diamond", "iron", "stone"});

	}

	
	static int answer;
    static int[][] fatigues = new int[3][3];
    static HashMap<String, Integer> map = new HashMap<>();
    public static int solution(int[] picks, String[] minerals) {
        answer = Integer.MAX_VALUE;

        fatigues[0][0] = 1;
        fatigues[0][1] = 1;
        fatigues[0][2] = 1;
        fatigues[1][0] = 5;
        fatigues[1][1] = 1;
        fatigues[1][2] = 1;
        fatigues[2][0] = 25;
        fatigues[2][1] = 5;
        fatigues[2][2] = 1;
        
        map.put("diamond", 0);
        map.put("iron", 1);
        map.put("stone", 2);
        
        int picksN = 0;
        for(int i = 0; i < picks.length; i++) {
            picksN += picks[i];
        }
        int choiceN = 0;
        if(minerals.length % 5 == 0) {
            choiceN = minerals.length / 5;
        } else {
            choiceN = minerals.length / 5 + 1;
        }
        
        int[] choice = new int[choiceN];
        
        pick(0, picks, choice, minerals, picksN);
        
        return answer;
    }
    
    public static void pick(int index, int[] picks, int[] choice, String[] minerals, int restPN) {
        //더 이상 캘 광물이 없거나 사용할 수 있는 곡괭이가 없을 때
        if(index * 5 >= minerals.length || restPN == 0) {
            //피로도 계산
            int mIndex = 0;
            int total = 0;
            
            for(int i = 0; i < index; i++) {
                total += calculate(choice[i], minerals, mIndex);
                mIndex += 5;
            }
            
            answer = Math.min(answer, total);
            return;
        }
        
        for(int i = 0; i < picks.length; i++) {
            //특정 곡괭이를 선택
            if(picks[i] > 0) {
                choice[index] = i;
                picks[i]--;
                pick(index + 1, picks, choice, minerals, restPN - 1);
                picks[i]++;
            }
        }
    }
    
    public static int calculate(int pick, String[] minerals, int mIndex) {
        int endMIndex = Math.min(mIndex + 5, minerals.length);
        
        int total = 0;
        
        for(int i = mIndex; i < endMIndex; i++) {
            int m = map.get(minerals[i]);
            total += fatigues[pick][m];
        }
        
        return total;
    }
}
