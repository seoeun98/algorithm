package work;

import java.util.*;

/*
 * 다익스트라 n번 돌리는 게 아니라
 * 카카오 등산코스 정하기마냥 que에 넣어서 해결
 * 
 */


class Solution {
    //n - 노드 갯수
    //edge - 간선 정보
    public int solution(int n, int[][] edge) {
        int answer = 0;
        
        ArrayList<Integer>[] list = new ArrayList[n];
        for(int i = 0; i < n; i++) {
            list[i] = new ArrayList<>();
        }
        
        for(int i = 0; i < edge.length; i++) {
            int start = edge[i][0] - 1;
            int end = edge[i][1] - 1;
            
            list[start].add(end);
            list[end].add(start);
        }
        
        
        return dijkstar(list, n);
    }
    
    public int dijkstar(ArrayList<Integer>[] list, int n) {
        int[] mins = new int[n];
        Arrays.fill(mins, Integer.MAX_VALUE);
        
        mins[0] = 0;
        
        Queue<Integer> que = new LinkedList<>();
        que.add(0);
        
        while(!que.isEmpty()) {
            int now = que.poll();
            
            for(int togo : list[now]) {
                if(mins[togo] > mins[now] + 1) {
                    mins[togo] = mins[now] + 1;
                    que.add(togo);
                }
            }
        }
        Queue<int[]> maxV = new LinkedList<>();
        maxV.add(new int[] {mins[0], 0});
        for(int i = 1; i < n; i++) {
            int[] top = maxV.peek();
            
            if(top[0] > mins[i]) {
                continue;
            }
            
            if(top[0] < mins[i]) {
                maxV.clear();
            } 
            maxV.add(new int[] {mins[i], i});
        }
        
        return maxV.size();
    }
}