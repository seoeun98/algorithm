package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_3020_개똥벌레 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken()); //길이 - 무조건 짝수
		int h = Integer.parseInt(st.nextToken()); //높이
		
		int[] bottom = new int[h + 1]; //석순
		int[] top = new int[h + 1]; //종유석
		int[] btmline = new int[h + 1];
		int[] topline = new int[h + 1];
		int[] line = new int[h + 1];
		
		int min = Integer.MAX_VALUE;
		
		//맵 구성하기
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			
			int height = Integer.parseInt(st.nextToken());
			
			if(i % 2 == 0) { //석순이면
				bottom[height]++;
			} else {
				top[height]++;
			}
		}
		
		btmline[h] = bottom[h];
		topline[h] = top[h];
		line[h] = btmline[h] + topline[h];
		for(int i = h - 1; i > 0; i--) {
			topline[i] = topline[i + 1] + top[i];
			btmline[i] = btmline[i + 1] + bottom[i];
		}
		
		for(int i = 1; i <= h; i++) {
			line[i] = topline[i] + btmline[h - i + 1];
			
			if(line[i] < min)
				min = line[i];
		}
		
		int count = 0;
		for(int i = 1; i <= h; i++) {
			if(line[i] == min)
				count++;
		}
		
		System.out.println(min + " " + count);
		
		
	}

	
}
