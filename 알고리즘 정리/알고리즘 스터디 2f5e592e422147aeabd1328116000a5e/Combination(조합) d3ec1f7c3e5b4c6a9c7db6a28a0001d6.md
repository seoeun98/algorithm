# Combination(조합)

## 정의

- 조합이란 `n` 개의 숫자 중에서 `r` 개의 수를 순서 없이 뽑는 경우

## 조합의 수식

$$
nCr = {n!\over(n-r)!r!},(n\geq r)
$$

### 핵심

1. 현재 인덱스를 선택하는 경우
2. 현재 인덱스를 선택하지 않는 경우

이렇게 두 가지로 모든 경우를 완전탐색 해주면 된다.

### 예시 코드(백트래킹을 이용하여 구현)

start 인덱스를 기준으로 start 보다 작으면 뽑을 후보에서 제외, start 보다 크면 뽑을 후보

```java
//백트래킹 사용
static void combination(int[] arr, boolean[] visited, int start, int n, int r){
	if(r == 0){
		print(arr, visited, n);
		return ;
	}
	
	for(int i=start; i<n; i++){
		visited[i] = true;
		combination(arr, visited, i+1, n, r-1);
		visited[i] = false;
	}
}
```

## 재귀적 표현

$$
nCr = _{n-1}C_{r-1} + _{n-1}C_r 
$$

### 예시 코드(재귀를 이용하여 구현)

depth 변수는 현재 인덱스

현재 인덱스를 뽑으면 `visited[depth] = true`, 뽑지 않는다면 `visited[depth] = false`

```java
//재귀 사용
static void combination(int[] arr, boolean[] visited, int dept, int n, int r){
	if(r == 0){
		print(arr, visited, n);
		return ;
	}

	if(depth == n) return;

	visited[depth] = true;
	combination(arr, visited, depth+1, n, r-1);
	
	visited[depth] = false;
	combination(arr, visited, depth+1, n, r);
}
```

---

## 부분 집합

### 집합의 원소를 선택하는 것

공집합을 포함한 모든 원소의 경우의 수를 의미

예를 들어 {1,2,3}의 부분집합으로는

<aside>
👉 {}, {1}, {2}, {3}, {1,2}, {1,3}, {2,3}, {1,2,3}

</aside>

- **부분집합의 수**

집합의 원소가 n개일 때, 공집합을 포함한 부분집합의 수는 **2^n**개 이다

### 핵심

1. 현재 인덱스를 포함하는 경우
2. 현재 인덱스를 포함하지 않는 경우

예시 코드(재귀를 이용한 구현)

```java
//재귀 사용
static void powerSet(int[] arr, boolean[] visited, int n, int idx){
	if(idx == n){
		print(arr, visited, n);
		return ;
	}

	visited[idx] = false;
	powerSet(arr, visited, n, idx+1);

	visited[idx] = true;
	powerSet(arr, visited, n, idx+1);
}
```

combination

nextpermutaition 활용한