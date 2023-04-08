package work;

/*
 * 그냥 무지성 돌리기 해도
 * 최대 100*100*10000이므로 1초 이하로 가능
 */

public class PGM_행렬테두리회전 {
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = {};
        
        int[][] map = new int[rows][columns];
        
        for(int i = 1; i <= rows; i++) {
            for(int j = 1; j <= columns; j++) {
                map[i- 1][j - 1] = (i - 1) * columns + j;
            }
        }
        
        int[] mins = new int[queries.length];
        int minsIndex = 0;
        for(int i = 0; i < queries.length; i++) {
            int[] start = new int[] {queries[i][0], queries[i][1]}; //회전 시작 지점
            int[] end = new int[] {queries[i][2], queries[i][3]}; //회전 끝 지점
            
            turn(map, start, end, mins, minsIndex++);
        }
        
        return mins;
    }

    //테두리만 회전
    public void turn(int[][] map, int[] start, int[] end, int[] mins, int minsIndex) {
        int startR = start[0] - 1;
        int startC = start[1] - 1;
        int endR = end[0] - 1;
        int endC = end[1] - 1;
        
        //회전시킬 수가 없다면 return;
        if(endR == startR || endC == startC) {
            return;
        }
        int min = 99999999;
        
        //위 테두리 회전
        int rightTop = map[startR][endC];
        for(int i = endC; i > startC; i--) {
            map[startR][i] = map[startR][i - 1];
            
            if(min > map[startR][i]) {
                min = map[startR][i];
            }
        }
        //오른쪽 테두리 회전
        int rightBottom = map[endR][endC];
        for(int i = endR; i > startR; i--) {
            map[i][endC] = map[i - 1][endC];
            
            if(min > map[i][endC]) {
                min = map[i][endC];
            }
        }
        map[startR + 1][endC] = rightTop;
        if(min > rightTop) {
            min = rightTop;
        }
        
        //아래 테두리 회전
        int leftBottom = map[endR][startC];
        for(int i = startC; i < endC; i++) {
            map[endR][i] = map[endR][i + 1];
            
            if(min > map[endR][i]) {
                min = map[endR][i];
            }
        }
        map[endR][endC - 1] = rightBottom;
        if(min > rightBottom) {
            min = rightBottom;
        }
        
        //왼쪽 테두리 회전
        for(int i = startR; i < endR; i++) {
            map[i][startC] = map[i + 1][startC];
            
            if(min > map[i][startC]) {
                min = map[i][startC];
            }
        }
        map[endR - 1][startC] = leftBottom;
        if(min > leftBottom) {
            min = leftBottom;
        }
        
        mins[minsIndex] = min;
    }
}
