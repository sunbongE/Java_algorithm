import java.util.*;

class Solution {
    public int solution(int[] elements) {
        int answer = 0;
        int n = elements.length;
        int len =0;
        HashSet<Integer> set = new HashSet<>();
        
            // 요소 순회
        for(int i = 0; i<n; i++){
            int sum = 0;
            for(int k=i; k<n+i; k++){
                sum += elements[k%n];
                set.add(sum);
            }
        }       
        
        
        answer = set.size();
        // System.out.println(set);
        return answer;
    }
}