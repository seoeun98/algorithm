# LIS(Longest Increasing Subsequence)

## 최장 증가 부분 수열 개념

---

- 원소가 `n`개인 배열의 일부 원소를 골라내서 만든 부분 수열 중, 각 원소가 이전 원소보다 크다는 조건을 만족하고, 그 길이가 최대인 부분 수열

## 최장 증가 부분 수열 구현

---

### 최장 증가 부분 수열

1. 과정
    1. 수열의 길이와 같은 메모이제이션 배열을 하나 선언한다.
    2. 수열을 처음부터 끝까지 순서대로 1개씩 탐색한다.(현재 위치 = i)
        1. `dp[i]`에 넣을 값을 초기화 해준다. `(val)`
        2. 현재 위치`(i)`보다 이전에 있는 원소`(j)` 중에서 현재 원소보다 작은지 체크한다.
        3. 현재 원소보다 작다면, `dp[j]`가 저장 되어있는 값보다 큰지 체크한다. 
        
        1. 현재 원소도 포함해주어야 하므로 최종적으로 `dp[i]`에 `val + 1` 할당한다.
    3. 메모이제이션 배열의 원소 중에서 가장 큰 값을 출력한다.
        
        ![Untitled](LIS(Longest%20Increasing%20Subsequence)%200e1aa1353051455f99590a200be87a07/Untitled.png)
        
2. 의사 코드
    
    ```java
    FOR i in 1 -> n
    	LIS[i] = 1
    	FOR j in 1 -> i - 1
    		IF a[j] < a[i] AND LIS[i] < 1 + LIS[j]
    			LIS[i] = LIS[j] + 1
    RETURN max LIS[]
    ```
    
3. 구현
    
    ```java
    public class LISTest1 {
    
    	public static void main(String[] args) {
    		Scanner s = new Scanner(System.in);
    		int N = s.nextInt();
    		int[] arr = new int[N];
    		int[] LIS = new int[N]; // 각수를 끝에 세울수 있는 최장길이
    		
    		for (int i = 0; i < N; i++) arr[i] = s.nextInt();
    		
    		int max = 0;
    		for(int i=0; i<N; ++i) { // 기존 증가수열에 덧붙일 대상
    			LIS[i] = 1; // 일단 현재의 수만 수열에 넣었을때 길이로 초기화.
    			for(int j=0; j<i; ++j) { // 결국, 자신의 앞쪽에 세울수 있는(자신보다 작은) 애 중에 가장 긴 최장길이에 자신을 붙인다.
    				if(arr[j]<arr[i] && LIS[i]<LIS[j]+1) {  // 최대값 갱신 
    					LIS[i] = LIS[j]+1;
    				}
    			}// 결국, LIS[i]에는 자신을 끝으로 하는 최대값이 저장되어 있음
    			
    			// 최장길이최대값 갱신 
    			if(max<LIS[i]) max = LIS[i];
    		}
    
    		System.out.println(max);
    	}
    
    }
    ```
    

### 최장 증가 부분 수열 - 이진 검색 활용

1. 과정
    1. `C[k]` : 길이 `k`의 증가 수열에 대하여 가장 작은 값을 `C[k]`에 저장
    2. 각 위치에서 `C[]`를 갱신하기 위해 이진 검색을 수행
        
        ![Untitled](LIS(Longest%20Increasing%20Subsequence)%200e1aa1353051455f99590a200be87a07/Untitled%201.png)
        
2. 구현
    
    ```java
    public class LISTest2_BinarySearch {
    
    	public static void main(String[] args) {
    		Scanner s = new Scanner(System.in);
    		int N = s.nextInt();
    		int[] arr = new int[N];
    		int[] C = new int[N];
    		
    		for (int i = 0; i < N; i++) arr[i] = s.nextInt();
    		
    		int size = 0;
            for (int i=0; i < N; i++) {
    
                int temp = Arrays.binarySearch(C, 0, size, arr[i]); // 리턴값 : -insertPoint -1
                temp = Math.abs(temp)-1;//삽입위치
                C[temp] = arr[i];// temp 자리에 값을 update 하면 그 의미는 
                			     // 0인덱스 위치부터 temp위치까지의 원소 갯수가  temp위치에 저장된 그 값을 마지막으로 하는 LIS 길이가 됨
                				 // 배열의 원소가 LIS를 이루는 구성요소와는 관계가 없다.
    
                if (size == temp) {// 삽입위치의 인덱스와 크기가 같으면(결국,마지막이 삽입위치라는 얘기임) 크기 1늘림.
                    size++;
                }
            }
            System.out.println(size);
    	}
    
    }
    ```
    

## 시간복잡도

---

- 원소가 N개인 배열
    - O(n^2)
    - O(nlogn), 이진 검색 활용