package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2607_비슷한단어 {

	static int[] oriCount = new int[26];
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		
		int n = Integer.parseInt(st.nextToken()); //단어의 수
		
		st = new StringTokenizer(reader.readLine());
		int count = 0;
		String ori = st.nextToken(); //기준
		
		for(int i = 0; i < ori.length(); i++) {
			oriCount[ori.charAt(i) - 'A']++;
		}
		
		for(int i = 1; i < n; i++) {
			st = new StringTokenizer(reader.readLine());
			String com = st.nextToken();
			
			int[] comCount = new int[26];
			for(int j = 0; j < com.length(); j++) {
				comCount[com.charAt(j) - 'A']++;
			}
			
			if(sameSet(comCount) || ifUpdatethenSameSet(comCount)) {
				count++;
			}
		}
		
		System.out.println(count);
	}
	
	//원래 같은 구성인지
	public static boolean sameSet(int[] com) {
		for(int i = 0; i < 26; i++) {
			if(com[i] != oriCount[i]) {
				return false;
			}
		}
		
		return true;
	}
	
	//수정 한 번 하면 같은 구성이 되는지
	public static boolean ifUpdatethenSameSet(int[] com) {
		int count = 0;
		
		int needAddCount = 0;
		int needLoseCount = 0;
		
		for(int i = 0; i < 26; i++) {
			if(Math.abs(com[i] - oriCount[i]) >= 2) {
				return false;
			}
			
			if(com[i] - oriCount[i] >= 1) {
				needLoseCount += com[i] - oriCount[i];
			}
			
			if(oriCount[i] - com[i] >= 1) {
				needAddCount += oriCount[i] - com[i];
			}
		}
		
		if(needAddCount > 1 || needLoseCount > 1) {
			return false;
		}
		
		return true;
	}
}
