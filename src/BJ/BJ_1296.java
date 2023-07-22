package BJ;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 팀이름정하기
 * @author qkrxo
 * <pre>
 * 
 * </pre>
 */
public class BJ_1296 {
	//이름을 저장 할 변수
	public static String teamName;
	public static int result;
	public static void main(String[] args) {
		// map 선언
		Map<String, Integer> map = new HashMap<String, Integer>() {{
			put("L",0);
			put("O",0);
			put("V",0);
			put("E",0);
		}};
		HashMap<String, Integer> copiedMap = new HashMap<String, Integer>();
		// 연두 이름 입력받아 배열로 변환한다. 순회 가능하게
		Scanner sc = new Scanner(System.in);
		char[] nameY = sc.next().toCharArray();
		
		// 연두 이름에서 [L,O,V,E]의 각각의 개수를 갱신한다.
		for (String k: map.keySet() ) {
			for (char name : nameY) {
				int temp = map.get(k);
				if (Character.toString(name).equals(k)) {
					map.put(k, ++temp);
				}
			}
		}
		// 반복할 수 입력
		int N = sc.nextInt();
		// 반복
		String[] names = new String[N];
		// 입력 문자열을 하나의 배열에 담는다.
		for(int i=0;i<N;i++) {
			names[i] = sc.next();
		}
		// 반복문 이후 내림차순 정렬한다.
		Arrays.sort(names, Collections.reverseOrder());
		// LOVE 각 개수를 순회하면 찾아야하니 배열을 반복한다.
		char[] nameToChar;
		// 반복문 안
		for(int i=0; i<N;i++) {
			int L=0;
			int O=0;
			int V=0;
			int E=0;
			int nameScore = 0; // 현재 이름 점수
			map.forEach((key, value) -> copiedMap.put(key, value));
			// charAt으로 문자를 하나씩 받아 LOVE 개수를 갱신하며 현재 값보다 수가 높으면 이름을
			nameToChar = names[i].toCharArray();
			for (String k: copiedMap.keySet()) {
				for (char c : nameToChar) {
					if(Character.toString(c).equals(k)) {
						//value 증가
						int tempValue = copiedMap.get(k)+1;
						copiedMap.put(k, tempValue);
					}
				}
			}
			// 이름하나 끝나면 식 적용해서 이름의 점수를 파악하고 점수가 result보다 놓으면 
			// teamName으로 설정한다.
			L = copiedMap.get("L");
			O = copiedMap.get("O");
			V = copiedMap.get("V");
			E = copiedMap.get("E");
			nameScore=((L+O) * (L+V) *(L+E) * (O+V)* (O+E) * (V+E))%100;
			if (result<=nameScore) {
				result = nameScore;
				teamName = names[i];
			}
		}
		System.out.println(teamName);
	
	}

}
