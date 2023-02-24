package work;

import java.util.Arrays;

public class PGM_길찾기게임 {
	public static void main(String[] args) {
		int[][] nodeinfo = {
				{5, 3},
				{11, 5},
				{13, 3},
				{3, 5},
				{6, 1},
				{1, 3},
				{8, 6},
				{7, 2},
				{2, 2}
		};
		solution(nodeinfo);
	}
	
	static class Node implements Comparable<Node> {
		int index;
		int x, y;
		Node left;
		Node right;
		
		public Node(int index, int x, int y) {
			this.index = index;
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Node o) {
			if(o.y == this.y) {
				return this.x - o.x;
			}
			return o.y - this.y;
		}
	}
	
	
	static int index;
	static int listN;
    public static int[][] solution(int[][] nodeinfo) {
        
        listN = nodeinfo.length;
        Node[] nodes = new Node[listN];
        
        int[][] answer = new int[2][listN];
        
        for(int i = 0; i < listN; i++) {
        	nodes[i] = new Node(i + 1, nodeinfo[i][0], nodeinfo[i][1]);
        }
        
        Arrays.sort(nodes);
        
        for(int i = 1; i < listN; i++) {
        	setTree(nodes[0], nodes[i]);
        }
        
        int[] pre = new int[listN];
        int[] last = new int[listN];
        
        preorder(nodes[0], pre);
        index = 0;
        lastorder(nodes[0], last);
        
        answer[0] = pre;
        answer[1] = last;
        
        return answer;
    }
    
    public static void setTree(Node parent, Node child) {
    	if(parent.x > child.x) { //왼쪽 영역에 위치
    		if(parent.left == null) {
    			parent.left = child;
    		} else {
    			setTree(parent.left, child);
    		}
    	} else { //오른쪽 영역에 위치
    		if(parent.right == null) {
    			parent.right = child;
    		} else {
    			setTree(parent.right, child);
    		}
    	}
    }
    
    public static void preorder(Node node, int[] order) {
    	order[index] = node.index;
    	
    	if(node.left != null) {
    		index++;
    		preorder(node.left, order);
    	}
    	if(node.right != null) {
    		index++;
    		preorder(node.right, order);
    	}
    }
    
    public static void lastorder(Node node, int[] order) {
    	if(node.left != null) {
    		lastorder(node.left, order);
    	}
    	if(node.right != null) {
    		lastorder(node.right, order);
    	}
    	
    	order[index++] = node.index;
    	
    }
}
