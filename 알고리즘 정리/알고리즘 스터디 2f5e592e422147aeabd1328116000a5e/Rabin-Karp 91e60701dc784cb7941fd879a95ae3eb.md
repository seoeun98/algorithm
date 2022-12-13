# Rabin-Karp

## ✔️라빈-카프 알고리즘

## 개념

---

- 문자열에 해싱 기법을 사용하여 해시 값으로 비교하는 알고리즘
- 해시 값을 만들 때, 문자열의 각 문자(ASCII)에 특정 수의 제곱 수를 차례대로 곱하여 모두 더하기
- 두 문자열이 서로 다를 때 두 문자열의 해시 값이 다르게 나오게 됨

## 특징

---

- 새로 추가되는 문자와 그 전에 읽었던 값을 이용하여 해시 값을 구하는 것
- 패턴이 정수가 아닌 문자열이며, 길이가 4자리보다 커지면 일정 자리수로 맞추기 위해 mod 연산 필요
- 따라서 해시 값이 일치하더라도 실제 패턴이 일치하지 않을 수 있으므로 해시 값 일치 시 문자열 일치를 검사(= 해시충돌)

## 비교 과정

---

![Untitled](Rabin-Karp%2091e60701dc784cb7941fd879a95ae3eb/Untitled.png)

1. 우선 패턴의 해시 값을 계산
    - 각 자리의 숫자에 자리 값을 곱하여 더하는 방식으로 계산
    - 즉, “4321” 문자열이 아니라 4321 정수 값으로 취급
        
        ![Untitled](Rabin-Karp%2091e60701dc784cb7941fd879a95ae3eb/Untitled%201.png)
        
2. 찾고자 하는 문자열에서 4자리씩 해시 값 계산

![Untitled](Rabin-Karp%2091e60701dc784cb7941fd879a95ae3eb/Untitled%202.png)

![Untitled](Rabin-Karp%2091e60701dc784cb7941fd879a95ae3eb/Untitled%203.png)

![Untitled](Rabin-Karp%2091e60701dc784cb7941fd879a95ae3eb/Untitled%204.png)

## JAVA 구현

---

```java
import java.util.*;
import java.io.*;

public class RabinKarp {

    public static void main(String[] args) throws IOException {
        String parent = "ababacabacaabacaaba";
        String pattern = "abacaaba";

        findStringPattern(parent, pattern);
    }

    public static void findStringPattern(String parent, String pattern) {
        final int MOD = 100000007;
        int parentSize = parent.length();
        int patternSize = pattern.length();

        long parentHash = 0, patternHash = 0, power = 1;

        for(int i=0; i<=parentSize-patternSize; i++) {
            if(i == 0) {
                for(int j=0; j<patternSize; j++) {
                    parentHash = (parentHash + (parent.charAt(patternSize-1-j)) * power) % MOD;
                    patternHash = (patternHash + (pattern.charAt(patternSize-1-j)) * power) % MOD;

                    if(j < patternSize-1) {
                        power = (power%MOD * 31) % MOD;
                    }
                }

            } else {
                parentHash = 31*parentHash%MOD - 31*parent.charAt(i-1)*power%MOD + parent.charAt(i+patternSize-1);
                parentHash %= MOD;
            }
            
            System.out.println(i+" >> 해시값 비교: "+parentHash+", "+patternHash);
            
            if(parentHash == patternHash) {
                System.out.println(i+"번째에서 같은 문자열");
            }
        }
    }
}
```

## 시간복잡도

---

- 찾으려는 문자열 패턴의 길이 M, 총 문자열의 길이 N
- O(N) 문자열 길이(N) 만큼의 복잡도
- 최악의 경우 시간 복잡도는 브루트 포스와 동일하게 O(MN)