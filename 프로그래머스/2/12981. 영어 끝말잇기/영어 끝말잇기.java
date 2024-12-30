import java.util.*;
class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer = new int[] {0,0};        
        HashSet<String> set = new HashSet<>();
        
        
        set.add(words[0]);
        
        for(int i=1; i<words.length;i++){
            // 이전 단어의 마지막 알파벳과 현재 시작 알파벳이 동일한가
            // 이전에 없던 단어를 말했는가
            if(set.contains(words[i]) ||
               words[i].charAt(0) != words[i-1].charAt(words[i-1].length()-1)){
                answer[0] = i%n+1;
                answer[1] = i/n+1;
                break;
            }
            set.add(words[i]);
            
        }
        return answer;
    }
}