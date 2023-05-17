package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 투포인터
 * 
 * 수 범위 잘 생각하자. 무조건 int로 뽑는 건 잘못된 행동
 */
public class BJ_13144_ListOfUniqueNumbers {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int[] ns = new int[n];
		
		st = new StringTokenizer(reader.readLine());
		for(int i = 0; i < n; i++) {
			ns[i] = Integer.parseInt(st.nextToken());
		}
		
		int start = 0, end = 0;
		boolean[] check = new boolean[100001];
		long[] vs = new long[n];
		
		while(start < n) {
			if(end == n || check[ns[end]]) { //이미 선택되어있거나 end가 끝에 다다랐을 때
				check[ns[start]] = false;
				start++;
			} else { //선택할 수 있으면
				check[ns[end]] = true;
				vs[end] = end++ - start + 1;
			}
		}
		
		for(int i = 1; i < n; i++) {
			vs[i] += vs[i - 1];
		}
		
		System.out.println(vs[n - 1]);
	}

}
