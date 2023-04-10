package work;

/*
 * 가로세로 최대 20씩밖에 안되니까 완점탐색으로 가능
 * 슬라이딩 윈도우 응용
 * 자물쇠에 필요한 부분만 따서 필요부분 map를 만듦
 * 열쇠 90도씩 회전 + 슬라이딩 윈도우로 열쇠와 필요부분map을 비교
 * 만약 map이랑 열쇠가 같다면 호완x 다음 부분으로 넘겨서 또 확인
 * 
 * 자물쇠가 모두 1인 경우 조심
 */

class Solution {
    public boolean solution(int[][] key, int[][] lock) {
        int keyPopCount = 0;
        for(int i = 0; i < key.length; i++) {
            for(int j = 0; j < key.length; j++) {
                if(key[i][j] == 1) {
                    keyPopCount++;
                }
            }
        }
        
        int lockHollCount = 0;
        int minR = 9999;
        int minC = 9999;
        int maxR = -1;
        int maxC = -1;
        for(int i = 0; i < lock.length; i++) {
            for(int j = 0; j < lock.length; j++) {
                if(lock[i][j] == 0) {
                    lockHollCount++;
                    
                    minR = Math.min(minR, i);
                    minC = Math.min(minC, j);
                    maxR = Math.max(maxR, i);
                    maxC = Math.max(maxC, j);
                    
                }
            }
        }
        
        
        
        if(keyPopCount < lockHollCount) {
            return false;
        }
        
        int windowR = maxR - minR + 1;
        int windowC = maxC - minC + 1;
        
        if(lockHollCount == 0) {
            windowR = 0;
            windowC = 0;
        }
        
        if(windowR > key.length || windowC > key.length) {
            return false;
        }
        
        int[][] window = new int[windowR][windowC];

        for(int i = minR; i <= maxR; i++) {
            for(int j = minC; j <= maxC; j++) {
                window[i - minR][j - minC] = lock[i][j];
            }
        }
        
        for(int turn = 0; turn < 4; turn++) { //90도로 0, 1, 2, 3번 회전
            if(turn > 0) {
                key = rotateMap(key, turn);
            }
            
             for(int startR = 0; startR <= key.length - windowR; startR++) { //맞추기 시작하는 r
               A: for(int startC = 0; startC <= key.length - windowC; startC++) { //맞추기 시작하는 c
                    for(int r = 0; r < windowR; r++) {
                        for(int c = 0; c < windowC; c++) {
                            if(r + startR >= key.length || c + startC >= key.length) {
                                continue A;
                            }
                            if(window[r][c] == key[r + startR][c + startC]) {
                                continue A;
                            }
                        }
                    }
                    return true;
                }
            }
            
        }
        return false;
    }
    
    public int[][] rotateMap(int[][] map, int num) {
        int n = map.length;
        int[][] rotate = new int[n][n];
        
        for(int i = 0; i < num; i++) {
            for(int r = 0; r < n; r++) {
                for(int c = 0; c < n; c++) {
                    rotate[r][c] = map[n - 1 - c][r];
                }
            }
        }
        
        return rotate;
    }
}
