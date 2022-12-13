# Permutation(순열)

## **정의**

- 서로 다른 것들 중 몇개를 뽑아서 한 줄로 나열하는 것
- 서로 다른 n개 중 r개를 택하는 순열은 아래와 같이 표현

<aside>
👉 **nPr** = n * (n-1) * (n-2) * ... * (n-r+1)

</aside>

- **nPn** = n!이라고 표기하며 Factorial이라 부른다.

---

## 재귀를 활용한 순열

```jsx
public class Permutation(int depth, int n, int r){
	if(depth == r){
		print(arr);
		return ;
	}

	for(int i=0; i<n; i++){
		if(!visited[i]){
			visited[i] = true;
			Permutation(depth+1, n, r);
			visited[i] = false;
		}
	}
}
```

---

## **Swap을 이용한 순열**

### ****swap 함수를 만들어서 배열들의 값을 직접 바꾸는 방법

1. 배열의 첫 값부터 순서대로 하나씩 바꾸며 모든 값을 한번씩 swap 합니다.
2. depth를 기준 인덱스로 하여  depth보다 인덱스가 작은 값들은 그대로 고정
3. depth 보다 인덱스가 큰 값들만 가지고 다시 swap 을 진행

![순열(swap).PNG](Permutation(%E1%84%89%E1%85%AE%E1%86%AB%E1%84%8B%E1%85%A7%E1%86%AF)%2050d433ec79c144d9b93cebf87824a072/%EC%88%9C%EC%97%B4(swap).png)

### 예시 코드

```java
//n개 중에서 r개를 뽑는 경우
static void permutation(int[] arr, int depth, int n, int r){
		if(dept == r){
			print (arr, r);
			return ;
		}
		
		for(int i=dept; i<n; i++){
			swap(arr, depth, i);
			permutation(arr, depth+1, n, r);
			swap(arr, depth, i);
		}
}

static void swap(int[] arr, int depth, int i){
	int temp = arr[depth];
	arr[depth] = arr[i];
	arr[i] = temp;
}
```

---

## **NextPermutation(다음순열)**

### 현 순열에서 사전 순으로 다음 순열 생성

- 배열을 오름차순으로 정렬한 후 시작
- 아래 과정을 반복하여 사전 순으로 다음으로 큰 순열 생성

| 1.  뒤쪽부터 탐색하며 교환위치( i - 1 ) 찾기 ( i : 꼭대기) |
| --- |
| 2. 뒤쪽부터 탐색하며 교환위치( i - 1 )와 교환할 큰값 위치( j ) 찾기 |
| 3. 두 위치 값( i - 1, j ) 교환 |
| 4. 꼭대기위치( i ) 부터 맨 뒤까지 오름차순 정렬 |

```java
static boolean NextPermutation(int N){
	//1. 교환 위치 찾기
	int i = N-1;
	while(i>0 && input[i-1] >= input[i]) --i;
	
	if(i==0) return false;
	
	//2. 교환위치에 교환할 값 교환하기
	int j = N-1;
	while(input[i-1] >= inpupt[j]) --j;
	
	//3. 교환위치와 교환할 값 교환하기
	swap(i-1, j);
	
	//4. 교환한 뒤(꼭대기)부터 맨 뒤까지 만들 수 있는 가장 작은 순열 생성(오름차순 정렬)
	int k = N-1;
	while(i<k){
		swap(i++,k--);
	}
	return true;
}
```