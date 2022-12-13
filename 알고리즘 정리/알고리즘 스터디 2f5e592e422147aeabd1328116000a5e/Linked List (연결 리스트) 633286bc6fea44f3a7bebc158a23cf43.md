# Linked List (연결 리스트)

# 연결 리스트의 정의

- 개별적으로 위치하고 있는 **각 원소를 연결한** 하나의 전체적인 자료구조
- 자료구조의 **크기를 동적으로 조정**할 수 있어, 메모리의 효율적인 사용 가능
- **링크를 통해 원소에 접근**

---

# 연결 리스트의 기본 구조

- **노드**
    - 연결 리스트의 한 원소
    - **<데이터 필드>** 원소의 값을 저장
    - **<링크 필드>** 다음 노드의 참조값을 저장
- **헤드**
    - 연결 리스트의 **첫 노드에 대한 참조값**을 가지고 있다.
    
    ![1.jpg](Linked%20List%20(%E1%84%8B%E1%85%A7%E1%86%AB%E1%84%80%E1%85%A7%E1%86%AF%20%E1%84%85%E1%85%B5%E1%84%89%E1%85%B3%E1%84%90%E1%85%B3)%20633286bc6fea44f3a7bebc158a23cf43/1.jpg)
    
    ---
    

# 연결 리스트의 종류

- **단순 연결 리스트**
    - **헤드는 가장 앞의 노드**를, 링크 필드가 **연속적으로 다음 노드**를 가리킨다.
    - **마지막 노드는 Null**을 가리킨다.
    
    - **첫번째 노드로 삽입**
        1. 새로운 노드 **new 생성**
            
            ![1.jpg](Linked%20List%20(%E1%84%8B%E1%85%A7%E1%86%AB%E1%84%80%E1%85%A7%E1%86%AF%20%E1%84%85%E1%85%B5%E1%84%89%E1%85%B3%E1%84%90%E1%85%B3)%20633286bc6fea44f3a7bebc158a23cf43/1%201.jpg)
            
        
        1. **Head가 가리키고 있던 노드의 참조값을 new의 링크 필드에 저장**
            
            ![2.jpg](Linked%20List%20(%E1%84%8B%E1%85%A7%E1%86%AB%E1%84%80%E1%85%A7%E1%86%AF%20%E1%84%85%E1%85%B5%E1%84%89%E1%85%B3%E1%84%90%E1%85%B3)%20633286bc6fea44f3a7bebc158a23cf43/2.jpg)
            
        
        1. **Head에 new의 참조값을 저장**
            
            ![3.jpg](Linked%20List%20(%E1%84%8B%E1%85%A7%E1%86%AB%E1%84%80%E1%85%A7%E1%86%AF%20%E1%84%85%E1%85%B5%E1%84%89%E1%85%B3%E1%84%90%E1%85%B3)%20633286bc6fea44f3a7bebc158a23cf43/3.jpg)
            
        
        addtoFirst( L , i )  // 리스트 헤드 L,  원소 i
        
        new ← createNode(); // 새로운 노드 생성
        
        new.data = i; // 데이터 필드 작성
        
        new.link = L; // 링크 필드 작성
        
        L = new; //리스트의 처음으로 지정
        
        end addtoFirst()
        
    - **마지막 노드로 삽입**
        1. 새로운 노드 **new 생성**
            
            ![1.jpg](Linked%20List%20(%E1%84%8B%E1%85%A7%E1%86%AB%E1%84%80%E1%85%A7%E1%86%AF%20%E1%84%85%E1%85%B5%E1%84%89%E1%85%B3%E1%84%90%E1%85%B3)%20633286bc6fea44f3a7bebc158a23cf43/1%202.jpg)
            
        
        1. 연결 리스트의 **마지막 노드의 링크 필드에 new의 참조값 저장**
            
            ![2.jpg](Linked%20List%20(%E1%84%8B%E1%85%A7%E1%86%AB%E1%84%80%E1%85%A7%E1%86%AF%20%E1%84%85%E1%85%B5%E1%84%89%E1%85%B3%E1%84%90%E1%85%B3)%20633286bc6fea44f3a7bebc158a23cf43/2%201.jpg)
            
        
         addtoLast( L, i ) //리스트 헤드 L, 원소 i
        
        new ← createNode() //새로운 노드 생성
        
        new.data = i;
        
        new.link = Null;
        
        if ( L == Null ) { //빈 리스트일 때는 최초 노드 추가
        
        L = new;
        
        return;
        
        }
        
        temp = L; //노드 링크 이용해서 리스트 순회
        
        while (temp.link ! = Null ) { //마지막 노드를 찾을 때까지 이동한다
        
        temp = temp.link;
        
        }
        
        temp.link = new; //마지막에 노드를 추가한다
        
    - **가운데 노드로 삽입**
        1. 새로운 노드 **new 생성**
        
        ![1.jpg](Linked%20List%20(%E1%84%8B%E1%85%A7%E1%86%AB%E1%84%80%E1%85%A7%E1%86%AF%20%E1%84%85%E1%85%B5%E1%84%89%E1%85%B3%E1%84%90%E1%85%B3)%20633286bc6fea44f3a7bebc158a23cf43/1%203.jpg)
        
        1. 삽입될 위치의 바로 앞에 위치한 노드의 링크를 new 링크에 복사
            
            ![2.jpg](Linked%20List%20(%E1%84%8B%E1%85%A7%E1%86%AB%E1%84%80%E1%85%A7%E1%86%AF%20%E1%84%85%E1%85%B5%E1%84%89%E1%85%B3%E1%84%90%E1%85%B3)%20633286bc6fea44f3a7bebc158a23cf43/2%202.jpg)
            
        2. 바로 앞 노드의 링크 필드에 새로운 노드 new의 참조값을 저장
            
            ![3.jpg](Linked%20List%20(%E1%84%8B%E1%85%A7%E1%86%AB%E1%84%80%E1%85%A7%E1%86%AF%20%E1%84%85%E1%85%B5%E1%84%89%E1%85%B3%E1%84%90%E1%85%B3)%20633286bc6fea44f3a7bebc158a23cf43/3%201.jpg)
            
        
        add ( L, pre, i) // 리스트 헤드 L, 노드 pre, 원소 i
        
        new ← createNode() //새로운 노드 생성
        
        new.data = i; //데이터 필드 작성
        
        if ( L == Null ) {
        
        L = new;
        
        new.link = Null;
        
        } else {
        
        new.link = pre.link;
        
        pre.link = new;
        
        } 
        
    - **삭제**
        1. 삭제할 노드의 선행 노드 탐색
            
            ![1.jpg](Linked%20List%20(%E1%84%8B%E1%85%A7%E1%86%AB%E1%84%80%E1%85%A7%E1%86%AF%20%E1%84%85%E1%85%B5%E1%84%89%E1%85%B3%E1%84%90%E1%85%B3)%20633286bc6fea44f3a7bebc158a23cf43/1%204.jpg)
            
        2. 선행 노드의 링크에 삭제할 노드의 링크를 복사
            
            ![2.jpg](Linked%20List%20(%E1%84%8B%E1%85%A7%E1%86%AB%E1%84%80%E1%85%A7%E1%86%AF%20%E1%84%85%E1%85%B5%E1%84%89%E1%85%B3%E1%84%90%E1%85%B3)%20633286bc6fea44f3a7bebc158a23cf43/2%203.jpg)
            
        3. 삭제할 노드의 링크에 null 저장
            
            ![3.jpg](Linked%20List%20(%E1%84%8B%E1%85%A7%E1%86%AB%E1%84%80%E1%85%A7%E1%86%AF%20%E1%84%85%E1%85%B5%E1%84%89%E1%85%B3%E1%84%90%E1%85%B3)%20633286bc6fea44f3a7bebc158a23cf43/3%202.jpg)
            
            delete ( L, target )   // 리스트 헤드 L, 삭제노드 target 
                    if ( L == Null || target == Null ) return;
                    pre  = getPreNode( target ); // 선행노드 탐색
                    
                    if ( pre == Null ) { //선행 노드가 없다면, 첫 노드임
                            L = target.link;
                    } else {
                            pre.link = target.link;
                    }
            
                    target.link = null;
            
    - **구현 코드**
        
        ```java
        public class SinglyLinkedList<T> implements ILinkedList<T> {
        	SingleNode<T> head;
        	
        	public boolean isEmpty() {
        		if(head == null) System.out.println("empty list");
        		return head == null;
        	}
        	
        	// 처음에 추가
        	public void addFirst(T d) {
        		System.out.println(d+" added to the front");
        		SingleNode<T> neww = new SingleNode<>(d);
        		neww.link = head;
        		head = neww;
        	}
        	
        	// 마지막에 추가
        	public void addLast(T d) {
        		// 방법 1. 그냥 구현
        //		SingleNode<T> neww = new SingleNode<>(d);
        //		
        //		if(isEmpty()) {
        //			head = neww;
        //			return;
        //		}
        //		System.out.println(d+" added to the end");
        //		// 마지막 노드 찾기
        //		SingleNode<T> prev = head;
        //		while(prev.link != null) {
        //			prev = prev.link;
        //		}
        //		// 연결
        //		prev.link = neww;
        		
        		// 방법 2. 중간에 삽입 이용
        		// 마지막 노드 찾기
        		SingleNode<T> prev = head;
        		while(prev.link != null) {
        			prev = prev.link;
        		}
        		System.out.print(d+" added to the end: ");
        		addMid(d, prev);
        		
        	}
        	
        	// target의 prev 구하기
        	SingleNode<T> findPrev(T target) {
        		for(SingleNode<T> i = head;i.link!=null;i=i.link) {
        			if(i.link.data.equals(target)) return i;
        		}
        		return null;
        	}
        	
        	// 중간에 추가
        	void addMid(T d, SingleNode<T> prev) {
        		SingleNode<T> neww = new SingleNode<>(d);
        		if(isEmpty()) {
        			head = neww;
        			return;
        		}else if(prev == null) return; // 값 못 찾음
        		System.out.println(d+" is added after "+prev.data);
        		neww.link = prev.link;
        		prev.link = neww;
        	}
        	
        	T deleteFirst() {
        //		if(isEmpty()) return null;
        //		T d = head.data;
        //		head = head.link;
        //		System.out.println(d+" is deleted from the front");
        //		return d;
        		return delete(null, head);
        	}
        	
        	T deleteLast() {
        		if(isEmpty()) return null;
        		
        		// 마지막 이전 노드 찾기
        		SingleNode<T> prev = head;
        		while(prev.link.link != null) {
        			prev = prev.link;
        		}
        		
        		System.out.print(prev.link.data+" is deleted from the end: ");
        		
        		return delete(prev, prev.link);
        		
        //		prev.link = null;
        //		return prev.data;
        	}
        	
        	// 삭제
        	T delete(SingleNode<T> prev, SingleNode<T> target) {
        		if(isEmpty() || target==null) return null;
        		if(prev == null) {
        			head = target.link;
        			target.link = null;
        			System.out.println("head node "+target.data+" is deleted");
        			return target.data;
        		}else {
        			prev.link = target.link;
        			target.link = null;
        			System.out.println("node "+target.data+" is deleted");
        			return target.data;
        		}
        	}
        	
        	public T delete(T target) {
        		SingleNode<T> prev = findPrev(target);
        		if(prev==null) return delete(null, null);
        		return delete(prev, prev.link);
        	}
        	
        	// prev 값 다음에 추가
        	@Override
        	public void add(T prev, T d) {
        		addMid(d, findPrev(prev).link);		
        	}
        
        	@Override
        	public SingleNode<T> getHead() {
        		return head;
        	}
        }
        ```
        

- **이중 연결 리스트**
    - **양쪽 방향으로 순회**할 수 있도록 노드를 연결한 리스트이다.
    - **두 개의 링크 필드**와 한 개의 데이터 필드로 구성되어 있다.
    
    ![1.jpg](Linked%20List%20(%E1%84%8B%E1%85%A7%E1%86%AB%E1%84%80%E1%85%A7%E1%86%AF%20%E1%84%85%E1%85%B5%E1%84%89%E1%85%B3%E1%84%90%E1%85%B3)%20633286bc6fea44f3a7bebc158a23cf43/1%205.jpg)
    
    - **삽입**
        1. 새로운 노드 **new 생성**
            
            ![1.jpg](Linked%20List%20(%E1%84%8B%E1%85%A7%E1%86%AB%E1%84%80%E1%85%A7%E1%86%AF%20%E1%84%85%E1%85%B5%E1%84%89%E1%85%B3%E1%84%90%E1%85%B3)%20633286bc6fea44f3a7bebc158a23cf43/1%206.jpg)
            
        
        1. **cur 노드의 다음 노드를 new의 다음 노드**로 저장한다
            
            ![2.jpg](Linked%20List%20(%E1%84%8B%E1%85%A7%E1%86%AB%E1%84%80%E1%85%A7%E1%86%AF%20%E1%84%85%E1%85%B5%E1%84%89%E1%85%B3%E1%84%90%E1%85%B3)%20633286bc6fea44f3a7bebc158a23cf43/2%204.jpg)
            
        
        1. **cur이 가리키고 있는 노드를 new의 이전 노드**로 저장한다
            
            ![3.jpg](Linked%20List%20(%E1%84%8B%E1%85%A7%E1%86%AB%E1%84%80%E1%85%A7%E1%86%AF%20%E1%84%85%E1%85%B5%E1%84%89%E1%85%B3%E1%84%90%E1%85%B3)%20633286bc6fea44f3a7bebc158a23cf43/3%203.jpg)
            
        
        1. **new 이전 노드가 다음 노드로 new**를 가리키도록 한다
            
            ![1.jpg](Linked%20List%20(%E1%84%8B%E1%85%A7%E1%86%AB%E1%84%80%E1%85%A7%E1%86%AF%20%E1%84%85%E1%85%B5%E1%84%89%E1%85%B3%E1%84%90%E1%85%B3)%20633286bc6fea44f3a7bebc158a23cf43/1%207.jpg)
            
        
        1. **new 다음 노드가 이전 노드로 new**를 가리키도록 한다
            
            
            ![2.jpg](Linked%20List%20(%E1%84%8B%E1%85%A7%E1%86%AB%E1%84%80%E1%85%A7%E1%86%AF%20%E1%84%85%E1%85%B5%E1%84%89%E1%85%B3%E1%84%90%E1%85%B3)%20633286bc6fea44f3a7bebc158a23cf43/2%205.jpg)
            
    - **삭제**
        1. 삭제할 노드 **cur의 다음 노드를 cur의 이전 노드의 next**에 저장
            
            ![1.jpg](Linked%20List%20(%E1%84%8B%E1%85%A7%E1%86%AB%E1%84%80%E1%85%A7%E1%86%AF%20%E1%84%85%E1%85%B5%E1%84%89%E1%85%B3%E1%84%90%E1%85%B3)%20633286bc6fea44f3a7bebc158a23cf43/1%208.jpg)
            
        
        1. 삭제할 노드 **cur의 이전 노드를 다음 노드의 prev**에 저장
            
            ![2.jpg](Linked%20List%20(%E1%84%8B%E1%85%A7%E1%86%AB%E1%84%80%E1%85%A7%E1%86%AF%20%E1%84%85%E1%85%B5%E1%84%89%E1%85%B3%E1%84%90%E1%85%B3)%20633286bc6fea44f3a7bebc158a23cf43/2%206.jpg)
            
        
        1. 삭제할 노드 **cur의 prev, next에 null** 저장
            
            ![3.jpg](Linked%20List%20(%E1%84%8B%E1%85%A7%E1%86%AB%E1%84%80%E1%85%A7%E1%86%AF%20%E1%84%85%E1%85%B5%E1%84%89%E1%85%B3%E1%84%90%E1%85%B3)%20633286bc6fea44f3a7bebc158a23cf43/3%204.jpg)
            
        - 이중연결 리스트 코드
            - 클래스
            
            ```java
            public class DLinkedList<E> implements List<E> {
             
            	private Node<E> head;	// 노드의 첫 부분 
            	private Node<E> tail;	// 노드의 마지막 부분 
            	private int size;	// 요소 개수
             
            	public DLinkedList() {
            		this.head = null;
            		this.tail = null;
            		this.size = 0;
            	}
            }
            ```
            
            - 추가
            
            ```java
            public void addFirst(E value) {
            	Node<E> newNode = new Node<E>(value);
            	newNode.next = head; 
             
            	if (head != null) {
            		head.prev = newNode;
            	}
            	head = newNode;
            	size++;
             
            	if (head.next == null) {
            		tail = head;
            	}
            }
            	
            @Override
            public boolean add(E value) {
            	addLast(value);
            	return true;
            }
             
             
            public void addLast(E value) {
            	Node<E> newNode = new Node<E>(value);
             
            	if (size == 0) {
            		addFirst(value);
            		return;
            	}
             
            	tail.next = newNode;
            	newNode.prev = tail;
            	tail = newNode;
            	size++;
            }
             
            @Override
            public void add(int index, E value) {
             
            	if (index > size || index < 0) {
            		throw new IndexOutOfBoundsException();
            	}
            	if (index == 0) {
            		addFirst(value);
            		return;
            	}
            	if (index == size) {
            		addLast(value);
            		return;
            	}
            	Node<E> prev_Node = search(index - 1);
            	Node<E> next_Node = prev_Node.next;
            	Node<E> newNode = new Node<E>(value);
            		
            	prev_Node.next = null;
            	next_Node.prev = null;
            	
            	prev_Node.next = newNode;
            	
            	newNode.prev = prev_Node;
            	newNode.next = next_Node;
            		
            	next_Node.prev = newNode;
            	size++;
            }
            ```
            
            - 삭제
            
            ```java
            public boolean remove(Object value) {
             
            	Node<E> prevNode = head;
            	Node<E> x = head;		// removedNode 
            		
            	// value 와 일치하는 노드를 찾는다.
            	for (; x != null; x = x.next) {
            		if (value.equals(x.data)) {
            			break;
            		}
            		prevNode = x;
            	}
             
            	// 일치하는 요소가 없을 경우 false 반환 
            	if(x == null) {
            		return false;
            	}
             
            	// 삭제하려는 노드가 head일 경우 remove()로 삭제
            	if (x.equals(head)) {
            		remove();
            		return true;
            	}
                
            	// remove(int index)와 같은 메커니즘으로 삭제
            	else {
            		Node<E> nextNode = x.next;
            			
            		prevNode.next = null;
            		x.data = null;
            		x.next = null;
            		x.prev = null;
            			
            		if(nextNode != null) {
            			nextNode.prev = null;
            			
            			nextNode.prev = prevNode;
            			prevNode.next = nextNode;
            		}
            		else {
            			tail = prevNode;
            		}
             
            		size--;
            		return true;
            	}
            }
            ```
            
            - 탐색
            
            ```java
            private Node<E> search(int index) {
             
            	// 범위 밖(잘못된 위치)일 경우 예외 던지기 
            	if(index < 0 || index >= size) {
            		throw new IndexOutOfBoundsException();
            	}
            		
            	// 뒤에서부터 검색 
            	if (index > size / 2) {
            		Node<E> x = tail;
            		for (int i = size - 1; i > index; i--) {
            			x = x.prev;
            		}
            		return x;
            	}
                
            	// 앞에서부터 검색
            	else {
            		Node<E> x = head;
            		for (int i = 0; i < index; i++) {
            			x = x.next;
            		}
            		return x;
            	}
            }
            ```