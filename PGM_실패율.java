package work;

import java.util.Arrays;

public class PGM_실패율 {

	public static void main(String[] args) {
		int n = 5;
		int[] stages = {4, 4, 4, 4, 4};
		
		int[] result = solution(n, stages);
		
		for(int r : result) {
			System.out.print(r + " ");
		}
	}

	public static class Map implements Comparable<Map>{
		int index;
		double fail = 1;
		
		@Override
		public int compareTo(Map o) {
			return Double.compare(o.fail, this.fail);
		}
		
	}
	
    public static int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
        
        int[] reached = new int[N + 1]; //해당 스테이지에 도달한 사람 수
        int[] clear = new int[N + 1]; //해당 스테이지를 클리어한 사람 수
        
        for(int i = 0; i < stages.length; i++) {
        	int now = stages[i] - 1;
        	
        	for(int j = 0; j <= now; j++) {
        		reached[j]++;
        		
        		if(j < now) {
        			clear[j]++;
        		}
        	}
        }
        
        Map[] list = new Map[N];
        
        for(int i = 0; i < N; i++) {
        	list[i] = new Map();
        	
        	if(reached[i] != 0) {
        		list[i].fail = ((double)(reached[i] - clear[i]) / reached[i]);        		
        	}
        	list[i].index = i + 1;
        }
        
        Arrays.sort(list);
        
        for(int i = 0; i < N; i++) {
        	answer[i] = list[i].index;
        }
        
        return answer;
    }
}
