# LCS(Longest Common Subsequence)

---

## LCS란?

- Longest Common Subsequence **최장 공통 부분수열**

---

## LCS 길이 구하기

- **ACAYKP** 와 **CAPCAK** 의 LCS 구하기
    - 이전 행의 값을 기반으로 계산
    - 같은 문자가 나오면 이전까지의 LCS의 길이(왼쪽 위) + 1
        - 해당 두 문자를 비교하기 전의 LCS 길이에 +1을 하기 위해
    - 문자가 다르다면, 위와 왼쪽 위를 비교하여 큰 값을 가져온다.
- **String1[n] == String2[k] : [n, k] = [n-1, k-1] + 1**
- **String1[n] ≠ String2[k] : [n, k] = Math.max( [n-1, k], [n, k-1] )**
- DP 테이블
    
    
    |  | 0 | A | C | A | Y | K | P |
    | --- | --- | --- | --- | --- | --- | --- | --- |
    | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
    | C | 0 | 0 | 1 | 1 | 1 | 1 | 1 |
    | A | 0 | 1 | 1 | 2 | 2 | 2 | 2 |
    | P | 0 | 1 | 1 | 2 | 2 | 2 | 3 |
    | C | 0 | 1 | 2 | 2 | 2 | 2 | 3 |
    | A | 0 | 1 | 2 | 3 | 3 | 3 | 3 |
    | K | 0 | 1 | 2 | 3 | 3 | 4 | 4 |
- 코드
    
    ```java
    static int[][] dp;
    
    public static void main(String[] args) throws IOException{
    		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    		String str1 = br.readLine();
    		String str2 = br.readLine();
    		System.out.println(LCS(str1, str2));
    }
    
    static int LCS(String str1, String str2) {
    		int n1 = str1.length();
    		int n2 = str2.length();
    		
    		dp = new int[n1+1][n2+1];
    		for(int i=1; i<n1+1; i++) {
    			for(int j=1; j<n2+1; j++) {
    				if(str1.charAt(i-1) == str2.charAt(j-1)) {
    					dp[i][j] = dp[i-1][j-1]+1;
    				}else {
    					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
    				}
    			}
    		}
    		return dp[n1][n2];
    }
    ```
    
    [https://sskl660.tistory.com/90](https://sskl660.tistory.com/90)
    
    참고하면 좋은 사이트
    

---

## LCS 구하기

- **가장 큰 숫자가 시작 된 곳을 체크**한다.
- **이전 숫자를 찾아가며 체크**한다.
- **각 행과 열에는 하나의 체크만** 있어야 한다.
- 같은 길이의 **다른 해가 있을 수 있다**
    
    ![화면 캡처 2022-04-25 213000.jpg](LCS(Longest%20Common%20Subsequence)%200de1da2ef11648a7a3cb12d0c5170157/%ED%99%94%EB%A9%B4_%EC%BA%A1%EC%B2%98_2022-04-25_213000.jpg)
    
- 코드
    
    ```java
    static int[][] dp;
    static StringBuilder sb = new StringBuilder();
    
    public static void main(String[] args) throws IOException{
    		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    		String str1 = br.readLine();
    		String str2 = br.readLine();
    		LCS2(str1,str2);
    		getLCSToString(str1, str1.length(), str2.length());
    		System.out.println(sb.toString());
    }
    	
    static void LCS2 (String str1, String str2) {
    		int n1 = str1.length();
    		int n2 = str2.length();
    		
    		dp = new int[n1+1][n2+1];
    		int max =-1;
    		for(int i=1; i<n1+1; i++) {
    			for(int j=1; j<n2+1; j++) {
    				if(str1.charAt(i-1) == str2.charAt(j-1)) {
    					dp[i][j] = dp[i-1][j-1] +1;
    				}else {
    					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
    				}
    			}
    		}
    		sb.append(dp[n1][n2] + "\n");
    }
    	
    static void getLCSToString(String str, int i, int j) {
    		Stack<Character> st = new Stack<>();
    		while(i>0 && j>0) {
    			if(i == 0 || j ==0)break;
    			
    			if(dp[i][j] == dp[i-1][j]) {
    				i--;
    			}else if(dp[i][j] == dp[i][j-1]) {
    				j--;
    			}else {
    				st.push(str.charAt(i-1));
    				i--;
    				j--;
    			}
    		}
    		while(!st.isEmpty()) {
    			sb.append(st.pop());
    		}
    }
    ```
    

---

## LCS의 시간 복잡도

- O(N^2)