# 배낭 문제(knapsack)

## 문제 링크

[www.acmicpc.net/problem/12865](https://www.acmicpc.net/problem/12865)

## 알고리즘[접근 방법]

- 배낭에 넣을 수 있는 최댓값이 정해지고 해당 한도 물건을 넣어 가치의 합이 최대가 되도록 고르는 방법을 찾는 것. 즉, 조합 최적화 문제이다.

- 고려해야 할 경우 2가지
    - 감당 못하는 무게일 때
    
    - 바로 전 물건이 현재 무게일 때 최대로 넣었던 가치를 갖고 온다
    
    - 감당 가능한 무게일 때
    
    - 바로 전 물건이 현재 무게일 때 최대로 넣었던 가치 , 
    
     현재 물건의 가치 + 남은 공간에 최대로 넣을 수 있는 가치 
    
    두 가지 중 더 큰 값으로 넣어주면 최대값이 된다.
    

- 예제

(Wi, Vi) = (6,13), (4,8), (3,6), (5,12) 이고 배낭 수용 가능한 최대 무게 K = 7

![Untitled](%E1%84%87%E1%85%A2%E1%84%82%E1%85%A1%E1%86%BC%20%E1%84%86%E1%85%AE%E1%86%AB%E1%84%8C%E1%85%A6(knapsack)%209f9c9c0d179f44519c939aad5cc783ca/Untitled.png)

위 두가지 고려 사항을 토대로 완성 된 최종 표

## 점화식

![Untitled](%E1%84%87%E1%85%A2%E1%84%82%E1%85%A1%E1%86%BC%20%E1%84%86%E1%85%AE%E1%86%AB%E1%84%8C%E1%85%A6(knapsack)%209f9c9c0d179f44519c939aad5cc783ca/Untitled%201.png)

<aside>
👉 k : dp[][] , W : 무게 , V : 가치

</aside>

## 구현1 (Bottom-Up 방식)

```java
for(int i=1; i<= N; i++){
	for(int j=1; j<=K; j++){

		//i번째 무게를 더 담을 수 없는 경우
		if(W[i] > J)
			dp[i][j] = dp[i-1][j];

		//i번째 무게를 더 담을 수 있는 경우
		else
			dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-W[i]] + V[i]);
	}
}
```

## 구현2(Top-Down 방식)

```java
static int knapsack(int i, int k){
	//i가 0미만, 즉 범위 밖이 된다면
	if( i < 0)
		return 0;

	// 탐색하지 않은 위치라면?
	if( dp[i][k] == null){
		//현재 물건(i)을 추가로 못담응 경우(이전 i값 탐색)
		if(W[i] > K)
			dp[i][k] = knapsack(i-1, k);

		//현재 물건(i)을 담을 수 있는 경우
		else if(W[i] <= k){
			//이전 i값과 이전 i값에 대한 k-W[i]의 값 + 현재 가치(V[i]중 큰 값을 저장
			dp[i][k] = Math.max(knapsack(i-1,k), knapsack(i-1, k-W[i]) + V[i]);
		}
	}
	return dp[i][k];
}
```