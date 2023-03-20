package work;

public class PGM_파괴되지않은건물 {

	public static void main(String[] args) {

	}

    public static int solution(int[][] board, int[][] skill) {
        int answer = 0;
        
        int[][] sumMap = new int[board.length][board[0].length];
        
        for(int i = 0; i < skill.length; i++) {
        	int hillordeal = skill[i][0]; //1이면 딜, 2면 힐
        	int r1 = skill[i][1];
        	int c1 = skill[i][2];
        	int r2 = skill[i][3];
        	int c2 = skill[i][4];
        	int degree = skill[i][5];
        	
        	if(hillordeal == 1) {
        		degree *= -1;
        	}
        	
        	if(r2 + 1 < board.length) {
        		sumMap[r2 + 1][c1] -= degree;
        	}
        	
        	if(c2 + 1 < board[0].length) {
        		sumMap[r1][c2 + 1] -= degree;
        	}
        	
        	sumMap[r1][c1] += degree;
        	
        	if(r2 + 1 < board.length && c2 + 1 < board[0].length) {
        		sumMap[r2 + 1][c2 + 1] += degree;
        	}
        }
        
        for(int i = 0; i < board.length; i++) {
        	for(int j = 0; j < board[i].length - 1; j++) {
        		sumMap[i][j + 1] += sumMap[i][j];
        	}
        }
        
        for(int i = 0; i < board[0].length; i++) {
        	for(int j = 0; j < board.length; j++) {
        		sumMap[j + 1][i] += sumMap[j][i];
        	}
        }
        
        for(int i = 0; i < board.length; i++) {
        	for(int j = 0; j < board[0].length; j++) {
        		if(board[i][j] + sumMap[i][j] >= 1) answer++;
        	}
        }
        
        return answer;
    }
}
