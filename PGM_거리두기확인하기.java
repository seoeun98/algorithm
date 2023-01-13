package work;

public class PGM_거리두기확인하기 {

	public static void main(String[] args) {
		String[][] places = {
				{"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"}, 
				{"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"}, 
				{"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"}, 
				{"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"}, 
				{"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}};
		int[] answer = solution(places);
		
		for(int i : answer) {
			System.out.print(i + " ");
		}
	}

    public static int[] solution(String[][] places) {
        int[] answer = new int[5];
        
        for(int i = 0; i < 5; i++) {
        	String[] oneRoom = places[i];
        	
        	char[][] room = new char[5][5];
        	
        	//방 하나에 대한 정보를 담은 배열 생성
        	for(int j = 0; j < 5; j++) {
        		room[j] = oneRoom[j].toCharArray();
        	}
        	
        	answer[i] = checkRoom(room);
        }
        return answer;
    }
    
    //8방탐색 + 추가탐색
    static int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1, 0, 0, 2, -2};
    static int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1, 2, -2, 0, 0};
    public static int checkRoom(char[][] room) {
    	
    	for(int i = 0; i < 5; i++) {
    		for(int j = 0; j < 5; j++) {
    			if(room[i][j] == 'P') {
    				for(int k = 0; k < 12; k++) {
    					int togoR = i + dr[k];
    					int togoC = j + dc[k];
    					
    					//범위 내에 다른 P가 있는지 확인
    					if(togoR >= 0 && togoC >= 0 && togoR < 5 && togoC < 5 && room[togoR][togoC] == 'P') {
    						//있다면
    						//거리지키기를 할 수 없는, 딱 붙어있다면
    						if(Math.abs(dr[k] +dc[k]) == 1) {
    							return 0;
    						} else if(k < 8 && (room[togoR][j] != 'X' || room[i][togoC] != 'X')) { //8방탐색 범위 내이고, 가림막을 제대로 하지 않았다면
    							return 0;
    						} else if(k >= 8 && room[(togoR + i) / 2][(togoC + j) / 2] != 'X') {
    							return 0;
    						}
    					}
    				}
    			}
    		}
    	}
    	
    	return 1;
    }
}
