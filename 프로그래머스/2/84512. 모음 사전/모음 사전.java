/**
대충 5^5 -> 3000개정도 나옴. list에 넣고, indexOf으로 몇번째인지 가져오면된다.
*/
import java.util.*;
class Solution {
    static char[] cArr = {'A','E','I','O','U'};
    static ArrayList<String> li = new ArrayList<>();
    public int solution(String word) {
        int answer = 0;

        getMPer(0,"");
        
        answer = li.indexOf(word)+1;
        return answer;
    }
    
    public void getMPer(int cnt, String per){
        if(cnt == 5){
            return;
        }
        
        for(int i=0; i<5;i++){
            String nextPer = per+cArr[i];
            li.add(nextPer);
            getMPer(cnt+1,nextPer);
        }
        
    }
}