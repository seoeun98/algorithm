# Hash (해시)

# 해시란?

- key값을 해시 함수를 통해 hash Code 생성, 저장공간의 size로 나눠 index를 정하여 value를 저장
- 키에 대한 해시 값을 사용하여 값을 저장하고, 키&값 쌍의 개수에 따라 동적으로 크기가 증가
- Hashing : 키에 대한 해시값을 구하는 과정
- 해시 함수 : Hashing할 때 사용하는 함수(알고리즘)
- **해시 값 자체를 index로 사용**하기에 평균 시간 복잡도는 O(1)

---

# 해시 함수란?

- 원래의 값이나 키를 색인할 때 사용되며, 값이 관련된 데이터가 검색될 때마다 다시 사용된다.
- 데이터의 효율적 관리를 목적으로 임의의 길이의 데이터를 고정된 길이의 데이터로 매핑
- 키값에 대해 **중복없이 해시값을 고르게 만들어낼수록 좋은 해시 함수**
- 대표적으로는 <나눗셈법>과 <곱셈법>이 있다
    - 나눗셈법 : 해시 함수 중에서도 가장 간단한 알고리즘. 입력 값을 테이블의 크기로 나누고 그 나머지를 테이블의 주소로 사용
    - 곱셈법 : 보통 2의 제곱수로 정하며, 나눗셈법보다는 느리지만 2진수 연산에 최적화된 컴퓨터 구조를 고려한 해시함수

---

# 해싱(hashing)이란?

- 매핑하는 과정
- 키 값에 직접 산술 연산을 적용하여 **해당 항목의 테이블의 주소를 계산하여 항목에 접근**
- 해시 테이블을 이용하여 탐색
- hashing 과정
    
    <aside>
    💡 키(key) → 해시 함수(hash fumction) → 해시값(hash value)
    
    키 : 매핑 전 원래 데이터의 값
    해시값 : 매핑 후 데이터의 값
    
    </aside>
    

---

# 해시 테이블이란?

- 해시 값을 index 혹은 주소로 하여 **데이터의 값과 키를 함께 저장**하는 자료구조

<aside>
💡 키들(keys) → 해시 함수(hash function) → buckets

버킷(bucket) or 슬롯(slot) : 데이터가 저장되는 곳

</aside>

---

# 해시의 장점

- 해시 충돌 발생 가능성은 있지만, **적은 리소스로 많은 데이터를 효율적으로 관리**할 수 있다
- index에 해시값을 사용하므로 모든 데이터를 살피지 않아도 **검색, 삽입, 삭제가 빠르게 수행**된다
- 색인은 간단한 함수로 작동하기에 효율적이다

---

```java
import java.util.LinkedList;

public class hashTable {
	class Node{
		String key;
		String value;
		public Node(String key, String value) {
			this.key = key;
			this.value = value;
		}
		String value() {
			return value;
		}
		void value(String value) {
			this.value = value;
		}
	}
	
	// Node형 연결리스트로 선언
	LinkedList<Node>[] data;
	
	// 자신을 호출하면서 크기를 지정하여 선언
	public hashTable(int size) {
		this.data = new LinkedList[size];
	}
	
	// Key값을 통해 HashCode 생성 - 아스키코드이용
	int gethashCode(String key) {
		int hashcode=0;
		for(char c : key.toCharArray())
			hashcode+=c;
		return hashcode;
	}
	
	// HashCode를 이용해서 Index를 지정
	int convertToIndex(int hashcode) {
		return hashcode%data.length;
	}
	
	Node searchKey(LinkedList<Node> list, String key) {
		if(list == null ) return null;
		for(Node node : list) {
			if(node.key.equals(key)) {
				return node;
			}
		}
		return null;
	}
	
	// Key를 통한 값 저장
	void set(String key,String value) {
		int index = convertToIndex(gethashCode(key));
		LinkedList<Node> list = data[index];
		// 없으면 저장
		if(list == null) {
			list = new LinkedList<Node>();
			data[index] = list;
		}
		
		Node node = searchKey(list, key);

		if(node==null)
			list.addLast(new Node(key,value));
		else
			node.value(value);
		
		System.out.println("hashcode : "+gethashCode(key)+", index : " +index + ", ");
	}
	
	// key를 통한 값 호출
	String get(String key) {
		int index = convertToIndex(gethashCode(key));
		LinkedList<Node> list = data[index];
		if(list == null)
			return "Not Found";
		else {
			for(Node n : list) {
				if(n.key.equals(key))
					return n.value;
			}
		return null;
		}
	}	
}
```