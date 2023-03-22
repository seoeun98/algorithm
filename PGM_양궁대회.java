package work;

import java.util.Arrays;

/*
 * 라이언은 특정 점수에 대해 둘 중 하나를 해야 한다
 * 해당 점수를 아예 버리기 or 어피치보다 하나라도 더 많이 맞추기
 * 
 * 어피치보다 적게 쏘거나 같게 쏴봤자 어차피 어피치가 점수 먹으니까 어피치보다 많이 쏠 수 없다면 그냥 아예 버려버리기
 * 
 * 범위 좁으니까 완탐으로 모든 경우의 수를 구해본다
 * 
 */

public class PGM_양궁대회 {

	public static void main(String[] args) {
		solution(5, new int[] {2,1,1,1,0,0,0,0,0,0,0});
	}
	
    static int[] answer;
    public static int[] solution(int n, int[] info) {
        function(n, 10, new int[11], info);
        
        if(answer == null) {
            answer = new int[1];
            answer[0] = -1;
        }
        
        return answer;
    }
    
    static int maxGap = 0;
    public static void function(int arrow, int index, int[] lion, int[] apeach) {
        if(index == -1) {       
            int lionCount = 0;
            int apeachCount = 0;
            for(int i = 0; i < 11; i++) {
                if(lion[i] > apeach[i]) {
                    lionCount += (10 - i);
                } else if(apeach[i] > 0) {
                    apeachCount += (10 - i);
                }
            }
            
            if(lionCount > apeachCount && lionCount - apeachCount > maxGap) {
            	if(arrow != 0) {
            		lion[10] += arrow;
            	}
                answer = Arrays.copyOfRange(lion, 0, lion.length);  
                maxGap = lionCount - apeachCount;
                lion[10] -= arrow;
            }
            return;
        }
        
        int apeachArrow = apeach[index];
        if(arrow >= apeachArrow + 1) {
	        lion[index] = apeachArrow + 1;
	        function(arrow - lion[index], index - 1, lion, apeach);
        }
        
        lion[index] = 0;
        function(arrow, index - 1, lion, apeach);
    }
}
