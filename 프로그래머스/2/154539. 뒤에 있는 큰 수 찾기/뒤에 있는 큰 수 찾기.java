import java.util.*;

class Solution {
    static Stack<int[]> stack;
    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        stack = new Stack<>();
        
        for(int i=0; i<numbers.length;i++){
            if(stack.isEmpty()){
                stack.push(new int[] {i,numbers[i]});
            }else{
                int[] tmp = stack.peek();
                
                while(tmp[1]<numbers[i]){ // 뒤에있는 큰수 찾은경우.
                    answer[tmp[0]] = numbers[i]; 
                    stack.pop();
                    if(stack.isEmpty()) break;
                    tmp = stack.peek();
                }
                stack.push(new int[] {i,numbers[i]});
            }
        }
        while(!stack.isEmpty()){
            int[] tmp = stack.pop();
            answer[tmp[0]] = -1;
        }
        return answer;
    }
}