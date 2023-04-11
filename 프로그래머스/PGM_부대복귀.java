package work;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
 * 그냥 단순히 다익스트라 돌려도 성공하긴 한다
 * 테스트 11 〉	통과 (9774.38ms, 614MB)
 * 테스트 12 〉	통과 (8046.54ms, 608MB)
 * 테스트 13 〉	통과 (9989.56ms, 572MB)
 * 테스트 14 〉	통과 (8214.21ms, 609MB)
 * 테스트 15 〉	통과 (8432.56ms, 660MB)
 * 
 * 근데 시간이 너무 오래 걸린다.
 * 각 출발지에서 목적지까지 얼마나 걸리는지를 하나하나 검사해서 그렇다. <-- 최대 500번 돌려야 함
 * 그냥 애초에 목적지에서 출발지들까지 얼마나 걸리는지를 보면 된다. <-- 한 번만 돌리면 됨
 */

public class PGM_부대복귀 {

    /**
    n : 노드 수
    roads : 간선 정보
    sources : 출발지 정보
    destination : 도착지
    */
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        
        ArrayList<Integer>[] list = new ArrayList[n];
        for(int i = 0; i < n; i++) {
            list[i] = new ArrayList<>();
        }
        
        destination--; //1부터 시작이니까 그냥 1 줄여버려
        
        for(int i = 0; i < roads.length; i++) {
            int first = roads[i][0] - 1;
            int second = roads[i][1] - 1;
            
            list[first].add(second);
            list[second].add(first);
        }
        
        int[] answer = dijstra(destination, sources, n, list);
        
        return answer;
    }
    
    public int[] dijstra(int start, int[] ends, int n, ArrayList<Integer>[] list) {
        int[] distance = new int[n];
        Arrays.fill(distance, 10000000);
        distance[start] = 0;
        
        Queue<Integer> que = new LinkedList<>();
        que.add(start);
        
        while(!que.isEmpty()) {
            int now = que.poll();
            
            for(int togo : list[now]) {
                
                if(1 + distance[now] < distance[togo]) {
                    distance[togo] = 1 + distance[now];
                    que.add(togo);
                }
                

            }
        }
        
        int index = 0;
        int[] answer = new int[ends.length];
        for(int i : ends) {
            answer[index++] = distance[i - 1] == 10000000 ? -1 : distance[i - 1] ;
        }
        
        return answer;
    }
}
