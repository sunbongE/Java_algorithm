import java.util.*;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        
        // 내가 원하는 품목과 수량을 map으로 변환
        Map<String, Integer> wantMap = new HashMap<>();
        for (int i = 0; i < want.length; i++) {
            wantMap.put(want[i], number[i]);
        }
        
        // 슬라이딩 윈도우: discount 배열에서 10개씩 검사
        for (int i = 0; i <= discount.length - 10; i++) {
            Map<String, Integer> windowMap = new HashMap<>();
            
            // 현재 윈도우에 등장한 품목 빈도 세기
            for (int j = i; j < i + 10; j++) {
                windowMap.put(discount[j], windowMap.getOrDefault(discount[j], 0) + 1);
            }
            
            // 내가 원하는 품목과 수량이 모두 만족하는지 비교
            boolean match = true;
            for (String item : wantMap.keySet()) {
                if (windowMap.getOrDefault(item, 0) < wantMap.get(item)) {
                    match = false;
                    break;
                }
            }
            
            if (match) answer++;
        }
        
        return answer;
    }
}
