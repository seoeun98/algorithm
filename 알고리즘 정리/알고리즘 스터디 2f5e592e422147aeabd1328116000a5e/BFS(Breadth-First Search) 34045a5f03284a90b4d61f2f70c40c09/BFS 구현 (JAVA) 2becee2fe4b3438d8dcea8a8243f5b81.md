# BFS 구현 (JAVA)

## 인접 행렬 + 큐 + 방문 처리 배열

---

```java
public static void bfs(int[][]adjMatrix,int start) {
		
		Queue<Integer> queue = new LinkedList<Integer>();
		boolean[] visited = new boolean[N];
		
		queue.offer(start);
		visited[start]=true;
		
		while(!queue.isEmpty()) {
			int current = queue.poll();
			System.out.print(current+" ");
			//current의 인접 정점 처리
			for(int j=0;j<N;j++) {
				if(!visited[j] && adjMatrix[current][j]!=0) {
					queue.offer(j);
					visited[j]=true;
				}
			}
			
		}
		
	}
```

## 인접 리스트 + 큐 + 방문 처리 배열

---

```java
static class Node {
		int vertex;
		Node link;
		
		public Node(int vertex, Node link) {
			this.vertex=vertex;
			this.link = link;
		}
}

public static void bfs(Node[] adjList,int start) {
		
		Queue<Integer> queue = new LinkedList<Integer>();
		boolean[] visited = new boolean[N];
		
		queue.offer(start);
		visited[start]=true;
		
		while(!queue.isEmpty()) {
			int current = queue.poll();
			System.out.print(current+" ");
			
			for(Node temp=adjList[current];temp!=null;temp=temp.link) {
				if(!visited[temp.vertex]) {
					queue.offer(temp.vertex);
					visited[temp.vertex]=true;
				}
			}
			
		}
	}

```

## 인접 행렬(2차원 배열 탐색)+ 큐 +방문 처리 배열

---

```java
static void bfs(int i,int j) { 
		Queue<int[]> q = new LinkedList<>();
		v[i][j] =true;
		q.offer(new int[] {i,j});

		while(!q.isEmpty()) {
			int[] cur = q.poll();
			i=cur[0];
			j=cur[1];
			a[i][j]=C++;

			for(int d=0;d<4;d++) {
				int ni=i+di[d];
				int nj=j+dj[d];
				if(0<=ni&&ni<N&&0<=nj&&nj<N&&!v[ni][nj]) {
					v[ni][nj]=true;
					q.offer(new int[] {ni,nj}); 
				}
			}
		}
		
	}
```

## 단계별 BFS 탐색

---

```java
public static void bfs(int[][]adjMatrix,int start) {
		
		Queue<Integer> queue = new LinkedList<Integer>();
		boolean[] visited = new boolean[N];
		
		queue.offer(start);
		visited[start]=true;
		

		int size = q.size();
	
		for(int i=0;i<size;i++){
			int current = queue.poll();
      //현재 depth 표시
			System.out.print("현재 Depth:"+size);
			System.out.print(current+" ");
			//current의 인접 정점 처리
			for(int j=0;j<N;j++) {
				if(!visited[j] && adjMatrix[current][j]!=0) {
					queue.offer(j);
					visited[j]=true;
				}
			}
		}
		
	}
```