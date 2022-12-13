# 이진 트리 순회 (Binary tree traversal)

## 전위 순회 (Preorder traversal)

### 방문 순서

- 부모 노드 → 왼쪽 자식 노드 → 오른쪽 자식 노드

### 순회 과정 애니메이션

![preorder.gif](%E1%84%8B%E1%85%B5%E1%84%8C%E1%85%B5%E1%86%AB%20%E1%84%90%E1%85%B3%E1%84%85%E1%85%B5%20%E1%84%89%E1%85%AE%E1%86%AB%E1%84%92%E1%85%AC%20(Binary%20tree%20traversal)%20c950fa346f7a4a88855884e7ec1ffe20/preorder.gif)

### 의사 코드

```java
preorderTraverse (T)
		if (T is not null)
				visit(T);  // 현재(부모) 노드 방문
				preorderTraverse(T.left);  // 왼쪽 자식 노드 방문
				preorderTraverse(T.right);  // 오른쪽 자식 노드 방문
end preorderTraverse
```

### Java 코드 예시

```java
class Node {
	public int value;
	public Node left;
	public Node right;

	public Node(int value) {
			this.value = value;
			this.left = null;
			this.right = null;
	}
}

public class PreorderTest {
	public static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) {
		// 트리 초기화
		Node root = new Node(1);
		root.left = new Node(2);
		root.right = new Node(3);
		root.left.left = new Node(4);
		root.left.right = new Node(5);
		root.right.left = new Node(6);
		root.right.right = new Node(7);
		
		// 전위 순회
		preorder(root);
		
		sb.setLength(sb.length() - 4);
		System.out.println(sb.toString());
	}
	
	public static void preorder(Node root) {
		if (root == null) return;
		
		sb.append(root.value).append(" -> ");	// 현재 노드 방문
		traverse(root.left);	// 왼쪽 자식 노드 방문
		traverse(root.right);	// 오른쪽 자식 노드 방문
	}
}
```

## 중위 순회 (Inorder traversal)

### 방문 순서

- 왼쪽 자식 노드 → 부모 노드 → 오른쪽 자식 노드

### 순회 과정 애니메이션

![inorder.gif](%E1%84%8B%E1%85%B5%E1%84%8C%E1%85%B5%E1%86%AB%20%E1%84%90%E1%85%B3%E1%84%85%E1%85%B5%20%E1%84%89%E1%85%AE%E1%86%AB%E1%84%92%E1%85%AC%20(Binary%20tree%20traversal)%20c950fa346f7a4a88855884e7ec1ffe20/inorder.gif)

### 의사 코드

```java
inorderTraverse (T)
		if (T is not null)
				inorderTraverse(T.left);  // 왼쪽 자식 노드 방문
				visit(T);  // 현재(부모) 노드 방문
				inorderTraverse(T.right);  // 오른쪽 자식 노드 방문
end inorderTraverse 
```

### Java 코드 예시

```java
class Node {
	public int value;
	public Node left;
	public Node right;

	public Node(int value) {
			this.value = value;
			this.left = null;
			this.right = null;
	}
}

public class InorderTest {
	public static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) {
		// 트리 초기화
		Node root = new Node(1);
		root.left = new Node(2);
		root.right = new Node(3);
		root.left.left = new Node(4);
		root.left.right = new Node(5);
		root.right.left = new Node(6);
		root.right.right = new Node(7);
		
		// 중위 순회
		inorder(root);
		
		sb.setLength(sb.length() - 4);
		System.out.println(sb.toString());
	}
	
	public static void inorder(Node root) {
		if (root == null) return;
		
		traverse(root.left);	// 왼쪽 자식 노드 방문
		sb.append(root.value).append(" -> ");	// 현재 노드 방문
		traverse(root.right);	// 오른쪽 자식 노드 방문
	}
}
```

## 후위 순회 (Postorder traversal)

### 방문 순서

- 왼쪽 자식 노드 → 오른쪽 자식 노드 → 부모 노드

### 순회 과정 애니메이션

![postorder.gif](%E1%84%8B%E1%85%B5%E1%84%8C%E1%85%B5%E1%86%AB%20%E1%84%90%E1%85%B3%E1%84%85%E1%85%B5%20%E1%84%89%E1%85%AE%E1%86%AB%E1%84%92%E1%85%AC%20(Binary%20tree%20traversal)%20c950fa346f7a4a88855884e7ec1ffe20/postorder.gif)

### 의사 코드

```java
postorderTraverse (T)
		if (T is not null)
				postorderTraverse(T.left);  // 왼쪽 자식 노드 방문
				postorderTraverse(T.right);  // 오른쪽 자식 노드 방문
				visit(T);  // 현재(부모) 노드 방문
end postorderTraverse
```

### Java 코드 예시

```java
class Node {
	public int value;
	public Node left;
	public Node right;

	public Node(int value) {
			this.value = value;
			this.left = null;
			this.right = null;
	}
}

public class PostorderTest {
	public static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) {
		// 트리 초기화
		Node root = new Node(1);
		root.left = new Node(2);
		root.right = new Node(3);
		root.left.left = new Node(4);
		root.left.right = new Node(5);
		root.right.left = new Node(6);
		root.right.right = new Node(7);
		
		// 후위 순회
		postorder(root);
		
		sb.setLength(sb.length() - 4);
		System.out.println(sb.toString());
	}
	
	public static void postorder(Node root) {
		if (root == null) return;
		
		traverse(root.left);	// 왼쪽 자식 노드 방문
		traverse(root.right);	// 오른쪽 자식 노드 방문
		sb.append(root.value).append(" -> ");	// 현재 노드 방문
	}
}
```

## 수식 트리 (수식 이진 트리)

### 개요

- 수식을 표현하는 이진 트리
- 연산자는 루트 노드이거나 가지 노드
- 피연산자는 모두 리프 노드

### 순회 예시

![Frame 52.png](%E1%84%8B%E1%85%B5%E1%84%8C%E1%85%B5%E1%86%AB%20%E1%84%90%E1%85%B3%E1%84%85%E1%85%B5%20%E1%84%89%E1%85%AE%E1%86%AB%E1%84%92%E1%85%AC%20(Binary%20tree%20traversal)%20c950fa346f7a4a88855884e7ec1ffe20/Frame_52.png)

- 전위 순회: `+ * 1 2 - 3 4`
- 중위 순회: `1 * 2 + 3 - 4`
- 후위 순회: `1 2 * 3 4 - +`

![Frame 53.png](%E1%84%8B%E1%85%B5%E1%84%8C%E1%85%B5%E1%86%AB%20%E1%84%90%E1%85%B3%E1%84%85%E1%85%B5%20%E1%84%89%E1%85%AE%E1%86%AB%E1%84%92%E1%85%AC%20(Binary%20tree%20traversal)%20c950fa346f7a4a88855884e7ec1ffe20/Frame_53.png)

- 전위 순회: `+ * * / A B C D E`
- 중위 순회: `A / B * C * D + E`
- 후위 순회: `A B / C * D * E +`