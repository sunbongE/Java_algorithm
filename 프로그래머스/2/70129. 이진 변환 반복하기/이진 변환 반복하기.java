import java.util.*;
// 한 사이클 -> 0없애기, 문자열길이를 2진법으로 표현한 문자열로 만들기

class Solution {
    public int[] solution(String s) {
        
        int len =0;
        
        int rmCnt=0;
        int procCnt=0;
        
        String rm0;
        String cur=s;
        StringBuilder sb;
        // 1이 될때 까지 반복.
        while(!cur.equals("1")){
            sb = new StringBuilder();
            // 0을 없앤다.
            for(int i=0; i<cur.length(); i++){
            // 없애는 과정에서 0의 개수를 카운트한다.
                if('0'==cur.charAt(i)){
                    rmCnt++;   
                }else{
                    sb.append(cur.charAt(i));
                }
            }
            
            cur = sb.toString();
            // 길이를 2진법으로 변환한 문자열로 만든다.
            len = cur.length();
            cur = getBit(len);
            // 연산 횟수를 늘린다.
            procCnt++;
        }
        int[] answer = new int[] {procCnt, rmCnt};
        
        return answer;
    }
    
    public String getBit(int len){
        StringBuilder result = new StringBuilder();
        while(len > 1){
            result.append(len%2);
            len = len/2;
        }
        result.append(len);
        
        return result.reverse().toString();
    }
}