package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1756_피자굽기 {

	static int start;
	static int end;
	public static void main(String[] args) throws IOException{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		
		int ovenN = Integer.parseInt(st.nextToken());
		int pizzaN = Integer.parseInt(st.nextToken());
		
		start = 0;
		end = ovenN;
		
		int[] radius = new int[ovenN]; //들어갈 수 있는 피자의 지름 배열

		int maxR = Integer.MAX_VALUE;
		st = new StringTokenizer(reader.readLine());
		for(int i = 0; i < ovenN; i++) {
			int r = Integer.parseInt(st.nextToken());
			
			if(maxR > r) maxR = r;
			
			radius[i] = maxR;
		}
		
		st = new StringTokenizer(reader.readLine());
		for(int i = 0; i < pizzaN; i++) {
			int r = Integer.parseInt(st.nextToken());

			findP(radius, r);
			
			end--; //방금 넣은 피자 이후로 넣을 수 있는 피자들의 끝은 방금 넣은 피자 위로만 넣을 수 있기 때문에
			
			if(end == -1) {
				break;
			}
			
			start = 0;
		}
		
		System.out.println(end + 1);
	}
	
	public static void findP(int[] radius, int pizza) {
		while(start < end) {
			int middle = (start + end) / 2;
			
			if(radius[middle] < pizza) { //피자가 더 커서 들어갈 수 없다.  
				end = middle;
			} else { //피자가 더 작거나 같다. 들어갈 수 있다
				start = middle + 1;
			}
		}
	}
	
}
