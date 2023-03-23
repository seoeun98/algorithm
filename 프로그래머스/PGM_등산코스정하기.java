package work;

import java.util.*;


public class PGM_등산코스정하기 {
	
	public static void main(String[] args) {
		int[] answer = solution( 
			4,
			new int[][] {{1, 3, 1}, {1, 4, 1}, {4, 2, 1}},
			new int[] {1},
			new int[] {2, 3, 4}
		);
		
		System.out.println(answer[0] + " " + answer[1]);
	}
	
	static class Node {
		int destination;
		int distance;
		
		public Node(int destination, int distance) {
			this.destination = destination;
			this.distance = distance;
		}
	}
	
	static boolean[] isgate;
	static boolean[] issummit;
    public static int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        int[] answer = new int[2];
        answer[0] = Integer.MAX_VALUE;
        answer[1] = Integer.MAX_VALUE;
        
        ArrayList<Node>[] list = new ArrayList[n];
        isgate = new boolean[n];
        issummit = new boolean[n];
        for(int i = 0; i < gates.length; i++) {
        	isgate[gates[i] - 1] = true;
        }
        
        for(int i = 0; i < summits.length; i++) {
        	issummit[summits[i] - 1] = true;
        }
        
        for(int i = 0; i < n; i++) {
        	list[i] = new ArrayList<>();
        }
        
        for(int i = 0; i < paths.length; i++) {
        	int start = paths[i][0] - 1;
        	int end = paths[i][1] - 1;
        	int distance = paths[i][2];
        	
        	if(issummit[start]) {
        		list[end].add(new Node(start, distance));
        		continue;
        	}
        	if(issummit[end]) {
            	list[start].add(new Node(end, distance));
            	continue;
        	}
        	if(isgate[start]) {
        		list[start].add(new Node(end, distance));
        		continue;
        	}
        	if(isgate[end]) {
        		list[end].add(new Node(start, distance));
        		continue;
        	}
        	list[start].add(new Node(end, distance));
        	list[end].add(new Node(start, distance));
        }
        
        answer = djikstra(n, gates, summits, list);
        
        return answer;
    }
    
    public static int[] djikstra(int n, int[] gates, int[] summits, ArrayList<Node>[] paths) {
    	Queue<int[]> que = new LinkedList<>();
    	
    	int[] dist = new int[n];
    	Arrays.fill(dist, Integer.MAX_VALUE);
    	    	
    	for(int i = 0; i < gates.length; i++) {
    		dist[gates[i] - 1] = 0;
    		que.add(new int[] {gates[i] - 1, 0});
    	}
    	
    	while(!que.isEmpty()) {
    		int[] now = que.poll();
    		int nowStart = now[0];
    		int nowDistance = now[1];
    	
    		if(nowDistance > dist[nowStart]) continue;
    		
    		for(Node node : paths[nowStart]) {
    			if(dist[node.destination] > Math.max(dist[nowStart], node.distance)) {
    				dist[node.destination] = Math.max(dist[nowStart], node.distance);
    				que.add(new int[] {node.destination, dist[node.destination]});
    			}
    		}
    		
    	}
    	
    	int index = 0;
    	int distance = Integer.MAX_VALUE;
    	
    	Arrays.sort(summits);
    	
    	for(int i = 0; i < summits.length; i++) {
    		if(dist[summits[i] - 1] < distance) {
    			distance = dist[summits[i] - 1];
    			index = summits[i];
    		}
    	}
    	
    	return new int[] {index, distance};
    }
	
}
	

/*
 * 1, 3, 6 실패
 * 13~21, 25 시간초과
 * 
 * 48.4점..
 */
//	public static void main(String[] args) {
//		solution( 
//			5,
//			new int[][] {{1, 2, 1}, {1, 3, 1}, {1, 4, 1}, {1, 5, 1}},
//			new int[] {1},
//			new int[] {2, 3, 4, 5}
//		);
//		
//		System.out.println(answer[0] + " " + answer[1]);
//	}
	
//	public static class Node {
//		int length;
//		int destination;
//		
//		public Node(int destination, int length) {
//			this.length = length;
//			this.destination = destination;
//		}
//	}
//
//	static ArrayList<Node>[] path;
//	static boolean[] isgate;
//	static boolean[] issummit;
//	static int[] answer;
//    public static int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
//        answer = new int[2];
//        Arrays.fill(answer, Integer.MAX_VALUE);
//        
//        path = new ArrayList[n];
//        isgate = new boolean[n];
//        issummit = new boolean[n];
//        
//        for(int i = 0; i < gates.length; i++) {
//        	isgate[gates[i] - 1] = true;
//        }
//        
//        for(int i = 0; i < summits.length; i++) {
//        	issummit[summits[i] - 1] = true;
//        }
//        
//        for(int i = 0; i < n; i++) {
//        	path[i] = new ArrayList<>();
//        }
//        
//        for(int i = 0; i < paths.length; i++) {
//        	int start = paths[i][0] - 1;
//        	int end = paths[i][1] - 1;
//        	int length = paths[i][2];
//        	
//        	path[start].add(new Node(end, length));
//        	path[end].add(new Node(start, length));
//        }
//        
//        for(int i = 0; i < gates.length; i++) {
//    		boolean[] check = new boolean[n];
//    		check[gates[i] - 1] = true;
//    		
//    		dfs(gates[i] - 1, 0, check, 0);
//        }
//        
//        return answer;
//    }
//    
//    public static void dfs(int now, int maxLength, boolean[] check, int depth) {
//    	ArrayList<Node> options = path[now];
//    	
//    	for(int i = 0; i < options.size(); i++) {
//    		//이미 방문 했던 곳이면 or 답보다 커서 갈 필요가 없으면
//    		if(check[options.get(i).destination] || (answer[1] != 0 && options.get(i).length > answer[1])) {
//    			continue;
//    		}
//    		
//    		//출입구 중 하나면
//    		if(isgate[options.get(i).destination]) { 
//    			continue;
//    		}
//    		
//    		//시작점이면 maxLength 무조건 갱신
//    		if(depth == 0) {
//    			maxLength = options.get(i).length;
//    		} 
//    		//시작점 아니면 더 클 때만 갱신
//    		else if(options.get(i).length > maxLength) {
//    			maxLength = options.get(i).length;
//    		}
//    		
//    		//산 정상이면
//    		if(issummit[options.get(i).destination]) {
//    			if(answer[1] == maxLength) {
//    				if(answer[0] > options.get(i).destination + 1) {
//    					answer[0] = options.get(i).destination + 1;
//    				}
//    			} else if(answer[1] > maxLength) {
//    				answer[0] = options.get(i).destination + 1;
//    				answer[1] = maxLength;
//    			}
//    		} else {
//    			check[options.get(i).destination] = true;
//    			dfs(options.get(i).destination, maxLength, check, depth + 1);
//    			check[options.get(i).destination] = false;
//    		}
//    	}
//    	
//    }
