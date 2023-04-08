package work;

import java.util.Stack;
import java.util.StringTokenizer;

/*
 * StringBuilder 사용의 중요성..
 * 입력에는 StringTokenizer 출력에는 StringBuilder 쓰기
 */

public class PGM_표편집 {
    class Node {
        Node prev;
        Node next;
        int index;
    }
    
    public String solution(int n, int k, String[] cmds) {      
        Node[] list = new Node[n];
        for(int i = 0; i < n; i++) {
            list[i] = new Node();
            list[i].index = i;
            
            if(i != 0) {
                list[i].prev = list[i - 1];
                list[i - 1].next = list[i];  
            }
        }
        
        Stack<Node> que = new Stack<>();
        Node now = list[k];
        for(String cmd : cmds) {
            StringTokenizer st = new StringTokenizer(cmd);
            String order = st.nextToken();
                        
            switch(order) {
                case "U" :
                    int upto = Integer.parseInt(st.nextToken());
                    
                    for(int i = 0; i < upto; i++) {
                       if(now.prev != null) {
                            now = now.prev;
                        }
                    }
                    break;
                case "D" :
                    int downto = Integer.parseInt(st.nextToken());
                    
                    for(int i = 0; i < downto; i++) {
                       if(now.next != null) {
                            now = now.next;
                        }
                    }
                    break;
                case "C" :
                    Node prevN = now.prev;
                    Node nextN = now.next;
                    
                    if(prevN != null)
                        prevN.next = nextN;
                    if(nextN != null)
                        nextN.prev = prevN;
                    
                    que.add(now);
                    
                    if(now.next != null) {
                        now = now.next;
                    } else {
                        now = now.prev;
                    }
                    break;
                case "Z" :
                    if(que.size() != 0) {
                        Node recentC = que.pop();

                        Node prev = recentC.prev;
                        Node next = recentC.next;

                        if(prev != null) {
                            prev.next = recentC;
                        }
                        if(next != null) {
                            next.prev = recentC;   
                        }
                    }
                    break;
            }
        }
        
        while(now.prev != null) {
            now = now.prev;
        }
        
        StringBuilder sb = new StringBuilder();
        
        for(int i = 0; i < n; i++) {
            if(now != null && now.index == i) {
                sb.append("O");
                now = now.next;
            } else {
                sb.append("X");
            }
        }
        
        
        return sb.toString();
    }
}
