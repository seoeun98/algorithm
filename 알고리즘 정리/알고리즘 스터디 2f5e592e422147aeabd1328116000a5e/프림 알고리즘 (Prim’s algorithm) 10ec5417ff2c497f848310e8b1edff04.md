# 프림 알고리즘 (Prim’s algorithm)

## 정의

---

- 하나의 정점에서 연결된 간선들 중 하나씩 선택하면서 [최소 신장 트리(MST)](%E1%84%8E%E1%85%AC%E1%84%89%E1%85%A9%20%E1%84%89%E1%85%B3%E1%84%91%E1%85%A2%E1%84%82%E1%85%B5%E1%86%BC%20%E1%84%90%E1%85%B3%E1%84%85%E1%85%B5%20da8d45e9b4af46db84ba95b99041efc7.md)를 만들어 가는 알고리즘
- 서로소인 2개의 집합 정보를 유지
    - 트리 정점들 (Tree vertices): MST를 만들기 위해 선택된 정점들
    - 비트리 정점들 (Non-tree vertices): 선택되지 않은 정점들

## 절차

---

1. 임의의 정점을 하나 선택
2. 선택한 정점과 인접하는 정점들 중 최소 비용의 간선이 존재하는 정점 선택
3. 처음 고른 정점과 선택된 간선에 연결된 정점까지 모든 정점에 연결된 간선 중 가장 비용이 적은 간선을 선택 후 연결
4. 모든 정점이 선택될 때까지 2, 3번 과정 반복

## 알고리즘 적용 예시

---

![Frame 54.png](%E1%84%91%E1%85%B3%E1%84%85%E1%85%B5%E1%86%B7%20%E1%84%8B%E1%85%A1%E1%86%AF%E1%84%80%E1%85%A9%E1%84%85%E1%85%B5%E1%84%8C%E1%85%B3%E1%86%B7%20(Prim%E2%80%99s%20algorithm)%2010ec5417ff2c497f848310e8b1edff04/Frame_54.png)

![Frame 55.png](%E1%84%91%E1%85%B3%E1%84%85%E1%85%B5%E1%86%B7%20%E1%84%8B%E1%85%A1%E1%86%AF%E1%84%80%E1%85%A9%E1%84%85%E1%85%B5%E1%84%8C%E1%85%B3%E1%86%B7%20(Prim%E2%80%99s%20algorithm)%2010ec5417ff2c497f848310e8b1edff04/Frame_55.png)

![Frame 56.png](%E1%84%91%E1%85%B3%E1%84%85%E1%85%B5%E1%86%B7%20%E1%84%8B%E1%85%A1%E1%86%AF%E1%84%80%E1%85%A9%E1%84%85%E1%85%B5%E1%84%8C%E1%85%B3%E1%86%B7%20(Prim%E2%80%99s%20algorithm)%2010ec5417ff2c497f848310e8b1edff04/Frame_56.png)

![Frame 57.png](%E1%84%91%E1%85%B3%E1%84%85%E1%85%B5%E1%86%B7%20%E1%84%8B%E1%85%A1%E1%86%AF%E1%84%80%E1%85%A9%E1%84%85%E1%85%B5%E1%84%8C%E1%85%B3%E1%86%B7%20(Prim%E2%80%99s%20algorithm)%2010ec5417ff2c497f848310e8b1edff04/Frame_57.png)

![Frame 58.png](%E1%84%91%E1%85%B3%E1%84%85%E1%85%B5%E1%86%B7%20%E1%84%8B%E1%85%A1%E1%86%AF%E1%84%80%E1%85%A9%E1%84%85%E1%85%B5%E1%84%8C%E1%85%B3%E1%86%B7%20(Prim%E2%80%99s%20algorithm)%2010ec5417ff2c497f848310e8b1edff04/Frame_58.png)

![Frame 59.png](%E1%84%91%E1%85%B3%E1%84%85%E1%85%B5%E1%86%B7%20%E1%84%8B%E1%85%A1%E1%86%AF%E1%84%80%E1%85%A9%E1%84%85%E1%85%B5%E1%84%8C%E1%85%B3%E1%86%B7%20(Prim%E2%80%99s%20algorithm)%2010ec5417ff2c497f848310e8b1edff04/Frame_59.png)

![Frame 60.png](%E1%84%91%E1%85%B3%E1%84%85%E1%85%B5%E1%86%B7%20%E1%84%8B%E1%85%A1%E1%86%AF%E1%84%80%E1%85%A9%E1%84%85%E1%85%B5%E1%84%8C%E1%85%B3%E1%86%B7%20(Prim%E2%80%99s%20algorithm)%2010ec5417ff2c497f848310e8b1edff04/Frame_60.png)

## 의사코드

---

```java
// G: 그래프, r: 시작 정점, minEdge[]: 각 정점 기준으로 타 정점과의 최소 간선 비용
// result: MST 비용, cnt: 처리한 정점 수, visited[]: MST에 포함된 정점 여부
MST-PRIM(G, r)
		result <- 0, cnt <- 0
		FOR u in G.V
				minEdge[u] <- ∞

		// 시작 정점 r의 최소 비용 0 처리
		minEdge[r] <- 0

		WHILE true
				// 방문하지 않은(MST에 포함되지 않은 정점) 최소 비용 정점 탐색
				// 이 부분은 우선순위 큐로도 구현할 수 있음
				u <- Extract-MIN()

				visited[u] <- true  // 방문 처리
				result <- result + minEdge[u]
				if (++cnt == N) break  // 모든 정점이 다 연결되어 있으면 MST 완성

				FOR v in G.Adj[u]  // u의 인접 정점들
						// u -> v 비용이 v의 최소 비용보다 작다면 업데이트
						IF visited[v] == false AND w(u, v) < minEdge[v]
								minEdge[v] <- w(u, v)
		return result
end MST-PRIM
```

## 자바 코드 예시

---

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MST_PrimTest {
		public static void main(String[] args) throws IOException {
				BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
				StringTokenizer st;
				
				// 정점 개수
				int N = Integer.parseInt(in.readLine());
				
				int[][] adjMatrix = new int[N][N];	// 인접 행렬
				int[] minEdge = new int[N]; 		// 다른 정점에서 자신으로의 간선 비용 중 최소 비용
				boolean[] visited = new boolean[N]; // MST에 포함된 정점 여부
				
				// 인접 행렬 초기화
				for (int i = 0; i < N; i++) {
						st = new StringTokenizer(in.readLine(), " ");
						for (int j = 0; j < N; j++) {
								adjMatrix[i][j] = Integer.parseInt(st.nextToken());
						}
						minEdge[i] = Integer.MAX_VALUE; // 충분히 큰 값으로 최솟값 초기화
				}
		
				int result = 0;		// MST 비용
				minEdge[0] = 0;		// 시작 정점은 최소 비용을 0으로 처리
				
				// N개의 정점을 모두 연결
				for (int c = 0; c < N; c++) {
						// MST에 연결되지 않은 정점 중 가장 유리한(최소) 비용의 정점 선택
						int min = Integer.MAX_VALUE;
						int minVertex = 0;
						
						for (int i = 0; i < N; i++) {
								if(!visited[i] && min> minEdge[i]) {
										min = minEdge[i];
										minVertex = i;
								}
						}
						
						// 선택된 정점을 MST에 포함
						visited[minVertex] = true;
						result += min;
						
						// 선택된 정점 기준으로 MSTS에 포함되지 않은 다른 정점으로의 비용을 비교 후 최솟값 업데이트
						for (int i = 0; i < N; i++) {
								if(!visited[i] &&  adjMatrix[minVertex][i] != 0 &&  minEdge[i] > adjMatrix[minVertex][i]) {
										minEdge[i] = adjMatrix[minVertex][i];
								}
						}
				}
				System.out.println(result);
		}
}
```

## 시간 복잡도

---

### 배열로 구현 시

- 각 정점에 최소 간선을 갖는 정점 탐색을 매 정점마다 수행하므로 $O(|V|^2)$
- 탐색 결과를 기반으로 각 정점의 최소 비용 연결 정점 탐색 시 $O(1)$
- 따라서 배열 구현 시 시간 복잡도는 $O(V^2)$

### 우선순위 큐로 구현 시

- 탐색과정은 $O(VlogV)$
    - 모든 노드에 대해 탐색을 진행하기 때문에 $O(V)$
    - 우선순위 큐를 사용하여 매 노드마다 최소 간선을 찾는 시간은 $O(logV)$
- 우선순위 큐 구성은 $O(ElogV)$
    - 각 노드의 인접 간선을 찾는 시간 = 모든 노드의 차수 = $O(\sum\limits_{v=1}^Vdegree(v))$=$O(2E)$=$O(E)$
    - 각 간선에 대해 힙에 넣는 과정은 $O(logV)$
- 따라서 $O(VlogV+ElogV)=O(ElogV)$
    - `E`가 일반적으로 `V`보다 크기 때문