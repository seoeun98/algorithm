package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 그냥 무지성 완탐
 */

public class BJ_1747_소수팰린드롬 {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		
		String n = st.nextToken();
		
		while(true) {
			int tmp = Integer.parseInt(n) == 1 ? 2 : Integer.parseInt(n);
			
			//팰린드롬인가
			if(isPalindrom(n)) {
				//소수인가
				if(isPrime(tmp)) {
					System.out.println(tmp);
					break;
				}
			}
			
			n = ++tmp + "";
		}	
	}

	public static boolean isPalindrom(String n) {
		int size = n.length() / 2;
		
		for(int i = 0; i < size; i++) {
			if(n.charAt(i) != n.charAt(n.length() - i - 1)) {
				return false;
			}
		}
		
		return true;
	}
	
	public static boolean isPrime(int tmp) {
		for(int i = 2; i < tmp; i++) {
			if(tmp % i == 0) {
				return false;
			}
		}
		
		return true;
	}
}
