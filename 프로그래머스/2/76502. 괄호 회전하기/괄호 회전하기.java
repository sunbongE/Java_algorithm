/*
len = s.length()-1;
1. len만큼 반복할 것.
2. 인덱스+s길이 만큼 반복 조건
3. s[i%s길이 ] 회전할 수 있게함.
4. 회전시 각 요소를 stack에 삽입
5. 삽입시 가장 위에 있는 값과 비교해서 올바른 괄호인지 확인.
*/
import java.util.*;
class Solution {
    public int solution(String s) {
        int answer = 0;
        char[] arr = s.toCharArray();
        
        int len = arr.length-1;
        for (int i=0;i<len;i++){
            Stack<Character> st = new Stack();
            boolean isValid=true;
            for(int idx=i; idx<i+arr.length; idx++){
                if(!checkAndPush(st, arr[idx%arr.length])) {
                    isValid=false;
                    break; // 넣는 순간 잘못된거 확인
                }
            }
            if(st.isEmpty() && isValid) answer++; // 올바르게 괄호가 들어가면 빈값.
        }
        return answer;
    }
    private boolean checkAndPush(Stack<Character> st, char c){
        boolean isValid = true;
        
        if(st.isEmpty()){
            // 처음부터 닫는 괄호면 올바른 것이 아님
            if(c == ']' || c==')' || c=='}') return false;
            st.push(c);
        }else{
            char pre = st.peek();
            if(c == ')' && pre == '(') st.pop();
            else if(c == '}' && pre == '{') st.pop();
            else if(c == ']' && pre == '[') st.pop();
            else{
                st.push(c); // 열린 괄호는 삽입.
            }
        }
        return isValid; // 넣을 수 있는 경우.
    }
}