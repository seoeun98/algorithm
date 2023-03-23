package work;

import java.util.ArrayList;
import java.util.Arrays;

/*
 * 다익스트라 사용
 * ① for문을 돌면서 경유지를 정한 뒤, 출발지에서 경유지까지의 최소 비용을 구한다 - d1
 * ② 해당 경유지에서 각자의 집까지 걸리는 최소 비용을 구한다 - d2, d3
 * ③ d1 + d2 + d3이 최솟값보다 작다면 갱신
 * 
 * 정확도 만점, 효율성 2개 틀림...
 */

public class PGM_합승택시요금 {

	static class Taxi {
		int end;
		int cost;
		
		public Taxi(int end, int cost) {
			this.end = end;
			this.cost = cost;
		}
	}
	
	public static void main(String[] args) {
		int[][] fares = {{5, 7, 9}, {4, 6, 4}, {3, 6, 1}, {3, 2, 3}, {2, 1, 6}};
		System.out.println(solution(7, 3, 4, 1, fares));
	}
	
	/*
	 * n == 지점 개수
	 * s == 출발지점
	 * a == A의 집
	 * b == B의 집
	 * fares == [1]과 [2] 사이의 비용 [3]
	 */
	
    public static int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;
        
        ArrayList<Taxi>[] costs = new ArrayList[n];
        
        for(int i = 0; i < n; i++) {
        	costs[i] = new ArrayList<>();
        }
        
        for(int i = 0; i < fares.length; i++) {
        	int start = fares[i][0] - 1;
        	int end = fares[i][1] - 1;
        	int cost = fares[i][2];
        	
        	costs[start].add(new Taxi(end, cost));
        	costs[end].add(new Taxi(start, cost));
        }
        
        for(int i = 0; i < n; i++) { //경유지를 정한다
        	int middle = dji(s - 1, i, costs);
        	int aD = dji(i, a - 1, costs);
        	int bD = dji(i, b - 1, costs);
        	
        	if(answer > middle + aD + bD) {
        		answer = middle + aD + bD;
        	}
        }
        
        return answer;
    }
    
    public static int dji(int start, int end, ArrayList<Taxi>[] costs) {
    	int n = costs.length;
    	
    	boolean[] check = new boolean[n];
    	int[] distance = new int[n];
    	
    	Arrays.fill(distance, Integer.MAX_VALUE);
    	
    	distance[start] = 0;
    	
    	for(int j = 0; j < n; j++) {
    		int min = 100001;
    		int now = start;
    		
    		for(int i = 0; i < n; i++) {
    			if(min > distance[i] && !check[i]) {
    				now = i;
    				min = distance[i];
    			}
    		}
    		
    		check[now] = true;
    		    		
    		for(Taxi t : costs[now]) {
    			if(!check[t.end] && t.cost + distance[now] < distance[t.end]) {
    				distance[t.end] = t.cost + distance[now];
    			}
    		}
    	}
    	
    	return distance[end];
    }

}
