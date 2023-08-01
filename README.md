# 자바 알고리즘 연습

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

