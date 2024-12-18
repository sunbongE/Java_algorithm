import java.util.*;

class Solution {
    public String solution(String s) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        
        String answer = "";
        String[] arr = s.split(" ");
        
        for(String a : arr){
            int inta = Integer.parseInt(a);
            min = Math.min(min, inta);
            max = Math.max(max, inta);
        }
        
        answer+=min;
        answer+=" ";
        answer+=max;
        return answer;
    }
}