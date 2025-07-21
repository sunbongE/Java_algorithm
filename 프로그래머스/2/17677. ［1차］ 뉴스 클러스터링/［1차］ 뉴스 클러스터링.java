import java.util.*;

class Solution {
    public int solution(String str1, String str2) {
        // 1. 두 문자열을 소문자로 변환
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();

        // 2. 다중집합(2글자씩) 생성
        List<String> a = makeList(str1);
        List<String> b = makeList(str2);

        // 3. 둘 다 공집합이면 65536 반환
        if (a.isEmpty() && b.isEmpty()) return 65536;

        // 4. 각 다중집합의 원소별 개수 Map
        Map<String, Integer> mapA = new HashMap<>();
        Map<String, Integer> mapB = new HashMap<>();

        for (String s : a) mapA.put(s, mapA.getOrDefault(s, 0) + 1);
        for (String s : b) mapB.put(s, mapB.getOrDefault(s, 0) + 1);

        // 5. 교집합/합집합 개수 계산
        int intersection = 0;
        int union = 0;

        // 합집합 후보 set
        Set<String> allKeys = new HashSet<>();
        allKeys.addAll(mapA.keySet());
        allKeys.addAll(mapB.keySet());

        for (String key : allKeys) {
            int countA = mapA.getOrDefault(key, 0);
            int countB = mapB.getOrDefault(key, 0);

            // 교집합: 둘 다 존재할 때 min
            intersection += Math.min(countA, countB);
            // 합집합: max
            union += Math.max(countA, countB);
        }

        // 6. 결과 반환 (소수점 버리고 int로)
        return (int) ((double)intersection / union * 65536);
    }

    // 2글자씩 끊어서 알파벳 쌍만 반환
    public List<String> makeList(String str) {
        List<String> list = new ArrayList<>();
        for (int i = 1; i < str.length(); i++) {
            char c1 = str.charAt(i - 1);
            char c2 = str.charAt(i);
            if (Character.isLetter(c1) && Character.isLetter(c2)) {
                list.add("" + c1 + c2);
            }
        }
        return list;
    }
}
