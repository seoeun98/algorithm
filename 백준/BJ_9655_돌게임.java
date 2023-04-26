package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_9655_돌게임 {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		
		boolean isSK = false;
		while(n > 0) {
			if(n >= 3) {
				n -= 3;
			} else {
				n--;
			}
			isSK = !isSK;
		}
		
		if(isSK) {
			System.out.println("SK");
		} else {
			System.out.println("CY");
		}
	}

}
