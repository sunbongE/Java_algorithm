import java.util.*;
/*
1,2,1,3,1,4,1,2
오른쪽 종류 : set.add == true then leftCnt++;
1 2 2 3 3 4

왼쪽 종류 : 전체 종류 - 오른쪽 종류(해쉬맵에서 조회시 0인경우 1을 뺀다. 아닌경우 넘어감.)
4 4 4 3 3 2

해쉬맵
1 : 4 -> 3 -> 2 -> 1
2 : 2 -> 1
3 : 1 -> 0
4 : 1 -> 0
*/
class Solution {
    public int solution(int[] topping) {
        int len = topping.length;
        int answer = 0;
        // 토핑 종류 : 개수
        HashMap<Integer, Integer> topping_cnt = new HashMap<>();
        
        // 종류별 개수 기록.
        for(int i=0; i<len; i++){
            topping_cnt.put(topping[i], topping_cnt.getOrDefault(topping[i],0)+1);
        }
        // 왼쪽 종류 기록.
        HashSet<Integer> set = new HashSet<>();
        int leftCnt = 0 ;
        
        // 전체 종류 개수
        int rightCnt = topping_cnt.keySet().size();
        
        for(int i=0; i<len; i++){
            int cur = topping[i];
            // 왼쪽 개수올림.
            if(set.add(cur)) leftCnt++;
            // 오른쪽 개수 변경
            topping_cnt.put(cur, topping_cnt.get(cur)-1);
            if(topping_cnt.get(cur) <= 0) rightCnt--;
            
            // 양쪽이 같으면 잘 분배한거
            if(leftCnt == rightCnt) answer++;
        }
        return answer;
    }
}