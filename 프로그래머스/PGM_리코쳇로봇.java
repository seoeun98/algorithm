package work;

import java.util.LinkedList;
import java.util.Queue;

public class PGM_리코쳇로봇 {

	public static void main(String[] args) {
		System.out.println(solution(new String[] {
				"..R",
				"...",
				"...",
				"...",
				"...",
				"..D",
				"DGD"}));
	}

    public static int solution(String[] board) {      
        //0 : 빈 칸, 1 : 장애물. 2 : 목적지
        int[][] map = new int[board.length][board[0].length()];
        int startR = 0, startC = 0;
        
        for(int i = 0; i < board.length; i++) {
            String line = board[i];
            
            for(int j = 0; j < board[i].length(); j++) {
                switch(line.charAt(j)) {
                    case 'D' :
                        map[i][j] = 1;
                        break;
                    case 'G' :
                        map[i][j] = 2;
                        break;
                    case 'R' :
                        startR = i;
                        startC = j;
                        break;
                }
            }   
        }

        int answer = bfs(startR, startC, map.length, map[0].length, map);

        return answer;
    }
    
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    //0 : 빈 칸, 1 : 장애물. 2 : 목적지
    static int bfs(int startR, int startC, int n, int m, int[][] map) {
        Queue<int[]> que = new LinkedList<>();
        boolean[][] check = new boolean[n][m];
        que.add(new int[]  {startR, startC});
        check[startR][startC] = true;
        
        int count = 0;
        
        while(!que.isEmpty()) {
            int queSize = que.size();
            
            while(queSize-- > 0) {
                int[] now = que.poll();
                int nowR = now[0];
                int nowC = now[1];
                
                if(map[nowR][nowC] == 2) {
                    return count;
                }
                
                for(int i = 0; i < 4; i++) {
                    int togoR = nowR + dr[i];
                    int togoC = nowC + dc[i];

                    boolean reachable = false;
                    while(togoR >= 0 && togoR < n && togoC >= 0 && togoC < m //범위 내에 있고
                         && map[togoR][togoC] != 1 //장애물에 걸리기 전까지
                         && !check[togoR][togoC]
                         && !(togoR == startR && togoC == startC)) { 
                        togoR += dr[i];
                        togoC += dc[i];
                        reachable = true;
                    }
                    
                    if(reachable
                    		&& togoR >= 0 && togoR < n && togoC >= 0 && togoC < m
                    		&& check[togoR][togoC]) {
                    	reachable = false;
                    }
                    
                    togoR -= dr[i];
                    togoC -= dc[i];
                    
                    if(reachable
                    		&& togoR >= 0 && togoR < n && togoC >= 0 && togoC < m 		
                    		&& map[togoR][togoC] != 1
                    		&& !check[togoR][togoC]
            				&& !(togoR == startR && togoC == startC)) {
                		que.add(new int[] {togoR, togoC});
                		check[togoR][togoC] = true;
                    }
                }
            }
            
            count++;
        }
        
        return -1;
    }
}
