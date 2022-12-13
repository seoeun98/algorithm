# Boyear-Moore

## ✔️보이어-무어 알고리즘

## 개념

---

- 패턴 문자열의 오른쪽 끝 부분에서부터 왼쪽 앞부분 방향으로 문자를 비교하는 방식
- 패턴 문자열에 대한 이동거리 skip 테이블을 만들어놓고 적절한 이동 거리만큼 점프해가며 비교

## 특징

---

- 보통 상황에서 문자열 앞부분보다 뒷부분이 불일치가 날 확률이 높다는 성질
- 원본 문자열을 모두 보지 않아도 검색 가능
- 구현이 복잡

## 비교 과정

---

- skip[ch] 배열 생성
    - 패턴 문자들의 skip 배열 값 = (패턴 문자열의 길이 - 1) - 각 패턴 문자의 인덱스

![Untitled](Boyear-Moore%20f46c5a33a1c44f2abd47af33b258e109/Untitled.png)

- 원본 문자열과 비교하며 skip[ch] 배열 값에 따라 이동

![Untitled](Boyear-Moore%20f46c5a33a1c44f2abd47af33b258e109/Untitled%201.png)

## JAVA 구현

---

```java
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoyerMoore {

    public static List<Integer> search(String text, String keyword) {
        List<Integer> indices = new ArrayList<>();

        Map<Character, Integer> skipTable = createSkipTable(keyword);

        int textPointer = keyword.length() - 1;
        while (textPointer > 0 && textPointer <= text.length() - keyword.length()) {
            int keywordPointer = keyword.length() - 1;
            int matchCount = 0;

            while (keywordPointer >= 0 && text.charAt(textPointer) == keyword.charAt(keywordPointer)) {
                textPointer--;
                keywordPointer--;
                matchCount++;
            }

            if (keywordPointer < 0) {
                indices.add(++textPointer);
                textPointer += keyword.length() + (keyword.length() - 1);
            } else {
                // skip 테이블의 값에서 앞서 일치한 수 만큼 뺀 값 만큼 skip
                // 이후 포인터를 다시 비교할 부분의 끝으로 돌려야 하므로 다시 matchCount 더함
                // 의미를 명확히 하기 위해 -matchCount + matchCount = 0으로 소거하지 않았음
                textPointer +=
                        skipTable.getOrDefault(text.charAt(textPointer), keyword.length()) - matchCount + matchCount;
            }
        }

        return indices;
    }

    private static Map<Character, Integer> createSkipTable(String keyword) {
        Map<Character, Integer> skipTable = new HashMap<>();
        int count = keyword.length() - 1;
        for (char ch : keyword.toCharArray()) {
            skipTable.put(ch, count--);
        }
        return skipTable;
    }
}
```

## 시간복잡도

---

- 찾으려는 문자열 패턴의 길이 M, 총 문자열의 길이 N
- 일반적으로 O(N) 보다 적다.
- 최악의 경우 O(MN)