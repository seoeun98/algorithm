# 서로소 집합(Disjont-sets)

## 서로소 or 상호배타 집합

- 서로 중복 포함된 원소가 없는 집합 (= 즉, 교집합이 없는 것)
    
    ![Untitled](%E1%84%89%E1%85%A5%E1%84%85%E1%85%A9%E1%84%89%E1%85%A9%20%E1%84%8C%E1%85%B5%E1%86%B8%E1%84%92%E1%85%A1%E1%86%B8(Disjont-sets)%200e0d09d9ef0c4691a6f7363cb2dd2de9/Untitled.png)
    

## 대표자(representative)

- 집합에 속한 하나의 특정 멤버를 통해서 각 집합들을 구분
    
    

## 서로소 집합 연산

- Make-Set(x) : 유일한 멤버 x를 포함하는 새로운 집합을 생성하는 연산
    
    ![Untitled](%E1%84%89%E1%85%A5%E1%84%85%E1%85%A9%E1%84%89%E1%85%A9%20%E1%84%8C%E1%85%B5%E1%86%B8%E1%84%92%E1%85%A1%E1%86%B8(Disjont-sets)%200e0d09d9ef0c4691a6f7363cb2dd2de9/Untitled%201.png)
    
- Find-Set(x) : x를 포함하는 집합을 찾는 연산(해당 노드의 부모 정보 갱신)
    
    ![Untitled](%E1%84%89%E1%85%A5%E1%84%85%E1%85%A9%E1%84%89%E1%85%A9%20%E1%84%8C%E1%85%B5%E1%86%B8%E1%84%92%E1%85%A1%E1%86%B8(Disjont-sets)%200e0d09d9ef0c4691a6f7363cb2dd2de9/Untitled%202.png)
    
- Union(x,y) : x와 y를 포함하는 두 집합을 통합하는 연산
    
    ![Untitled](%E1%84%89%E1%85%A5%E1%84%85%E1%85%A9%E1%84%89%E1%85%A9%20%E1%84%8C%E1%85%B5%E1%86%B8%E1%84%92%E1%85%A1%E1%86%B8(Disjont-sets)%200e0d09d9ef0c4691a6f7363cb2dd2de9/Untitled%203.png)
    

## 코드(JAVA)

- Make-Set(x)
    
    ```java
    //단위 집합 생성
    public static void makeSet() {
    	parents = new int[N];
    	//자신의 부모노드를 자신의 값으로 세팅
    	for(int i=0;i<N;i++) {
    		parents[i]=i;
    	}
    }
    ```
    
- Find-Set(x)
    
    ```java
    //a의 집합 찾기 : a의 대표자 찾기
    public static int findSet(int a) {
    	if(a==parents[a]) return a;
    	return parents[a] = findSet(parents[a]);
    }
    ```
    
- Union(x,y)
    
    ```java
    //a,b 두 집합 합치기
    public static boolean union(int a,int b) {
    	int aRoot = findSet(a);
    	int bRoot = findSet(b);
    	if(aRoot==bRoot) return false;
    	
    	parents[bRoot] = aRoot;
    	return true;
    }
    ```