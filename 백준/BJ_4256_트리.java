package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 전위 기준 제일 앞에 있는 게 루트
 * 중위에서 루트를 기준으로 왼쪽과 오른쪽으로 나눌 수 있음
 * 
 * 그러면 또 전위로 가서 제일 앞에 있는게 또 루트
 * 또 중위로 가서 왼쪽과 오른쪽으로 나눠..
 * 
 * 이거 계속 반복하면 트리 완성, 후위 돌면 됨
 * -------------------------------------⬆️시간 초과 남
 * rootIndex : inorder배열에서 찾고자 하는 preorder값의 인덱스
 * start : inorder배열의 탐색 시작 인덱스
 * end : inorder배열의 탐색 종료 인덱스
 * idx : inorder배열에서 preorder[rootIndex]의 인덱스
 * 
 * inorder 배열의 start~end까지 탐색하면서 preorder[rootIndex]가 존재하는지 확인
 * 존재한다? 해당 값이 부모 노드가 됨. 부모 노드를 기준으로 왼쪽과 오른쪽 구역으로 나눌 수 있음
 * 
 * preorder 배열은 부모, 왼쪽, 오른쪽이기에 왼쪽 구역에서 찾아야하는 rootIndex는 그냥 바로 뒤에 거. rootIndex + 1 번째를 찾으면 됨.
 * 
 * 그러면 오른쪽 구역은? 
 * 일단 rootIndex 이후라는 거는 동일하기에 +1
 * preorder 배열이나 inorder 배열이나 동일한 게 하나 있음
 * pre 3 6 5 4 8 | 7 1 2
 * in  5 6 8 4 3 | 1 2 7
 * 이렇게 갈림. 따라서 idx를 더해줘야 함. preorder에서도 idx이후로가 오른쪽에 위치하는 요소들이기 때문
 * 
 * 그러면 rootIndex + 1 + idx가 되는데 rootIndex와 idx를 더하면서 start가 중복됨. start 빼주기
 * ∴ inorder의 idx 오른쪽 구역에서 찾아야 할 preorder의 요소 인덱스는 rootIndex + 1 + idx - start
 */


public class BJ_4256_트리 {
	
//	static class Node {
//		int value;
//		Node left;
//		Node right;
//		
//		public Node(int value) {
//			this.value = value;
//		}
//	}

	static int[] preorder;
	static int[] inorder;
//	static Node root;
	static int rootIndex;
	static int nodeN;
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		
		int testCase = Integer.parseInt(st.nextToken());
		
		for(int test = 0; test < testCase; test++) {
			st = new StringTokenizer(reader.readLine());
			nodeN = Integer.parseInt(st.nextToken());
			
			preorder = new int[nodeN];
			inorder = new int[nodeN];
			
			st = new StringTokenizer(reader.readLine());
			for(int i = 0; i < nodeN; i++) {
				preorder[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(reader.readLine());
			for(int i = 0; i < nodeN; i++) {
				inorder[i] = Integer.parseInt(st.nextToken());
			}
			
//			root = new Node(preorder[0]);
			
			setTree(0, 0, nodeN);
//			setTree(root, midorder.indexOf(preorder.get(0)), 0, nodeN - 1);
			
//			lastOrder(root);
			System.out.println();
		}
	}
	
	public static void setTree(int rootIndex, int start, int end) {
		if(rootIndex >= nodeN) {
			return;
		}
		
		for(int i = start; i < end; i++) {
			if(inorder[i] == preorder[rootIndex]) {
				setTree(rootIndex + 1, start, i);
				setTree(rootIndex + i + 1 - start, i + 1, end);
				System.out.print(inorder[i] + " ");
			}
		}
		
	}
	
//	public static void lastOrder(Node root) {
//		if(root.left != null) {
//			lastOrder(root.left);
//		}
//		
//		if(root.right != null) {
//			lastOrder(root.right);
//		}
//		
//		System.out.print(root.value + " ");
//	}

}
