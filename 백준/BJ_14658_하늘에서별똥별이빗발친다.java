package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 점 두 개를 골라서 사각형 만드는 풀이를 할 때
 * 두 점이 꼭 모서리에 포함되지 않아도 된다
 * 그냥 사각형 만드는데 두 점을 사용하는 것 뿐이지
 * 예를 들어서 입력이
 * 4 4 2 4
 * 0 4
 * 0 3
 * 3 2
 * 4 3
 * 
 * 이렇다고 했을 때
 * 사각형 만들 때 쓸 두 개의 점으로 0 3 이랑 3 2를 골랐다고 해보자
 * 그러면 사각형의 스타트점(왼쪽 아래 점)이 3 3이 되는 것 뿐
 * 꼭 선택된 두 개의 점이 모서리에 포함되는 것은 아니다 
 */

public class BJ_14658_하늘에서별똥별이빗발친다 {

	static int max;
	static int mapSizeR, mapSizeC, tram, starN;
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		
		mapSizeC = Integer.parseInt(st.nextToken());
		mapSizeR = Integer.parseInt(st.nextToken());
		tram = Integer.parseInt(st.nextToken());
		starN = Integer.parseInt(st.nextToken());

		int[][] stars = new int[starN][2];
		
		for(int i = 0; i < starN; i++) {
			st = new StringTokenizer(reader.readLine());
			int starC = Integer.parseInt(st.nextToken());
			int starR = Integer.parseInt(st.nextToken());
			
			stars[i][0] = starR;
			stars[i][1] = starC;
		}
		
		for(int i = 0; i < starN; i++) {
			for(int j = 0; j < starN; j++) {
//				if(i == j) continue;
				
				int[] first = stars[i];
				int[] second = stars[j];
				
				int minR = first[0];
				int minC = second[1];
				int maxR = minR + tram;
				int maxC = minC + tram;
				int count = 0;
				for(int k = 0; k < starN; k++) {
					if(stars[k][0] >= minR && stars[k][1] >= minC && stars[k][0] <= maxR && stars[k][1] <= maxC) {
						count++;
					}
				}
				
				if(count > max) {
					max = count;
				}
			}
		}
		
		
		System.out.println(starN - max);
	}

	public static void partition() {
		
	}
}
