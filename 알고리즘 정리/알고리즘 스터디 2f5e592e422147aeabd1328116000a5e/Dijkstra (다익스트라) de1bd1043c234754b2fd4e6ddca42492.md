# Dijkstra (다익스트라)

## 최단 거리란?

- 간선의 가중치가 있는 그래프에서 두 정점 사이의 경로들 중에 가중치의 합이 최소인 경로

---

## 정의

- 음의 가중치가 없는 그래프의 한 노드에서 각 모든 노드까지의 최단거리를 구하는 알고리즘
- 다이나믹 프로그래밍
    - 하나의 최단 거리를 구할 때 이전까지 구했던 최단 거리 정보를 그대로 사용
- 시간복잡도
    
    <aside>
    💡 O( (V+E) log V)      (V는 정점의 개수, E는 한 정점의 주변 노드
    
    </aside>
    

## 작동 과정

1. 출발 노드를 설정
2. 출발 노드를 기준으로 각 노드의 최소 비용을 저장
3. 방문하지 않은 노드 중에서 가장 비용이 적은 노드를 선택
4. 해당 노드를 거쳐서 특정한 노드로 가는 경우를 고려하여 최소 비용을 갱신
5. 3번~4번을 반복

## 예시

![Untitled](Dijkstra%20(%E1%84%83%E1%85%A1%E1%84%8B%E1%85%B5%E1%86%A8%E1%84%89%E1%85%B3%E1%84%90%E1%85%B3%E1%84%85%E1%85%A1)%20de1bd1043c234754b2fd4e6ddca42492/Untitled.png)

- 위와 같이 노드 1을 선택한 상태에서 배열의 변화

1번 노드에서 다른 정점으로 가는 최소 비용

![Untitled](Dijkstra%20(%E1%84%83%E1%85%A1%E1%84%8B%E1%85%B5%E1%86%A8%E1%84%89%E1%85%B3%E1%84%90%E1%85%B3%E1%84%85%E1%85%A1)%20de1bd1043c234754b2fd4e6ddca42492/Untitled%201.png)

---

방문하지 않은 노드 중 가장 비용이 적은 노드 선택

선택한 노드를 거쳐 다음 노드로 가는 경우 비용 최솟값으로 갱신

- 4번 노드 선택
    
    ![Untitled](Dijkstra%20(%E1%84%83%E1%85%A1%E1%84%8B%E1%85%B5%E1%86%A8%E1%84%89%E1%85%B3%E1%84%90%E1%85%B3%E1%84%85%E1%85%A1)%20de1bd1043c234754b2fd4e6ddca42492/Untitled%202.png)
    

![Untitled](Dijkstra%20(%E1%84%83%E1%85%A1%E1%84%8B%E1%85%B5%E1%86%A8%E1%84%89%E1%85%B3%E1%84%90%E1%85%B3%E1%84%85%E1%85%A1)%20de1bd1043c234754b2fd4e6ddca42492/Untitled%203.png)

---

- 2번 노드 선택
    
    ![Untitled](Dijkstra%20(%E1%84%83%E1%85%A1%E1%84%8B%E1%85%B5%E1%86%A8%E1%84%89%E1%85%B3%E1%84%90%E1%85%B3%E1%84%85%E1%85%A1)%20de1bd1043c234754b2fd4e6ddca42492/Untitled%204.png)
    

![Untitled](Dijkstra%20(%E1%84%83%E1%85%A1%E1%84%8B%E1%85%B5%E1%86%A8%E1%84%89%E1%85%B3%E1%84%90%E1%85%B3%E1%84%85%E1%85%A1)%20de1bd1043c234754b2fd4e6ddca42492/Untitled%205.png)

---

- 5번 노드 선택
    
    ![Untitled](Dijkstra%20(%E1%84%83%E1%85%A1%E1%84%8B%E1%85%B5%E1%86%A8%E1%84%89%E1%85%B3%E1%84%90%E1%85%B3%E1%84%85%E1%85%A1)%20de1bd1043c234754b2fd4e6ddca42492/Untitled%206.png)
    

![Untitled](Dijkstra%20(%E1%84%83%E1%85%A1%E1%84%8B%E1%85%B5%E1%86%A8%E1%84%89%E1%85%B3%E1%84%90%E1%85%B3%E1%84%85%E1%85%A1)%20de1bd1043c234754b2fd4e6ddca42492/Untitled%207.png)

---

- 3번 노드 선택
    
    ![Untitled](Dijkstra%20(%E1%84%83%E1%85%A1%E1%84%8B%E1%85%B5%E1%86%A8%E1%84%89%E1%85%B3%E1%84%90%E1%85%B3%E1%84%85%E1%85%A1)%20de1bd1043c234754b2fd4e6ddca42492/Untitled%208.png)
    

![Untitled](Dijkstra%20(%E1%84%83%E1%85%A1%E1%84%8B%E1%85%B5%E1%86%A8%E1%84%89%E1%85%B3%E1%84%90%E1%85%B3%E1%84%85%E1%85%A1)%20de1bd1043c234754b2fd4e6ddca42492/Untitled%209.png)

---

- 6번 노드 선택
    
    ![Untitled](Dijkstra%20(%E1%84%83%E1%85%A1%E1%84%8B%E1%85%B5%E1%86%A8%E1%84%89%E1%85%B3%E1%84%90%E1%85%B3%E1%84%85%E1%85%A1)%20de1bd1043c234754b2fd4e6ddca42492/Untitled%2010.png)
    

최종 배열은 다음과 같이 형성됩니다.

![Untitled](Dijkstra%20(%E1%84%83%E1%85%A1%E1%84%8B%E1%85%B5%E1%86%A8%E1%84%89%E1%85%B3%E1%84%90%E1%85%B3%E1%84%85%E1%85%A1)%20de1bd1043c234754b2fd4e6ddca42492/Untitled%2011.png)

## 예시 코드

```java
//number : node 갯수
//matrix[][] : 인접행렬
//v[] : 방문한 노드, d[] : 거리 

void dijkstra(int start){
	for(int i=0; i<number; i++){
		d[i] = matrix[start][i];
	}

	v[start] = true;

	for(int i=0; i<number -2; i++){
		int cur = getSmallIndex();
		v[cur] = true;

		for(int j=0; j< number; j++){
			if(!v[j]){
				if(d[cur] + matrix[cur][j] < d[j])
					d[j] = d[cur] + matrix[cur][j];
			}
		}
	}
}

//최소 거리를 가지는 정점 반환
void getSmallIndex();
```

/그림 과정 추가