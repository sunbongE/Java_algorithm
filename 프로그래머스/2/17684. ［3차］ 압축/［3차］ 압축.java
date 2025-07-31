import java.util.*;
class Solution {
    public int[] solution(String msg) {
        // LZW 압축 결과를 저장할 리스트
        List<Integer> result = new ArrayList<>();

        // A~Z 사전을 초기화
        Map<String, Integer> dictionary = new HashMap<>();
        for (int i = 1; i <= 26; i++) {
            dictionary.put(String.valueOf((char)('A' + i - 1)), i);
        }

        int dictIndex = 27; // 새로운 사전 인덱스
        String word = ""; // 현재 탐색 중인 문자열

        for (int i = 0; i < msg.length(); i++) {
            word += msg.charAt(i); // 다음 글자 추가

            // 사전에 없다면, 이전 문자열까지만 출력하고 새로운 문자열 등록
            if (!dictionary.containsKey(word)) {
                dictionary.put(word, dictIndex++); // 새로운 문자열 사전에 추가
                result.add(dictionary.get(word.substring(0, word.length() - 1))); // 이전 문자열 출력
                word = String.valueOf(msg.charAt(i)); // 현재 문자부터 다시 시작
            }
        }

        // 마지막 단어도 결과에 추가
        if (!word.isEmpty()) {
            result.add(dictionary.get(word));
        }

        // List<Integer> → int[] 변환
        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}