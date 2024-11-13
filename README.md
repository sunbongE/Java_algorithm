# 자바 알고리즘 연습
## 오답 문제 큐

| 문제 링크                            | 날짜     | 풀이 횟수 |
| ------------------------------------ | -------- |--- |
| https://www.acmicpc.net/problem/3151 | 24.11.05 |1 |
| https://www.acmicpc.net/problem/1300 | 24.11.10 |0 |
| https://www.acmicpc.net/problem/2098 | 24.11.10 |0 |
| https://www.acmicpc.net/problem/2263 | 24.11.12 |0 |


## 알고리즘 문법 정리



### 맵 순회하기

```java
or (String k: map.keySet() ) { // key값이 나온다.
			for (char name : nameY) {
				int temp = map.get(k);
				if (Character.toString(name).equals(k)) {
					map.put(k, ++temp);
				}
			}
		}
```



### 해쉬맵 복사

```java
Map<String, Integer> map = new HashMap<String, Integer>() {{
			put("L",0);
			put("O",0);
			put("V",0);
			put("E",0);
		}};
		HashMap<String, Integer> copiedMap = new HashMap<String, Integer>();

map.forEach((key, value) -> copiedMap.put(key, value));

```

### char -> String으로

`Character.toString(문자)`

```java
if(Character.toString(c).equals(k)) 
```



### char -> int

`Character.getNumericValue( 문자타입입력 );`

```java
char as = '9';
System.out.println(Character.getNumericValue(as)); // 9
```



### 문자열에서 문자 배열로 만들기

```java
String name = "taeho";
nameToChar = name.toCharArray();//['t','a','e','h','o']
```

