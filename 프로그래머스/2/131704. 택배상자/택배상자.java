
import java.util.*;

class Solution {
    public int solution(int[] order) {
        int answer = 0;
        
        
        Stack<Integer> sub =  new Stack<>();
        
        for(int num=1; num<=order.length; num++){
            
            // 지금 들어온 수가 오더의 자리수에 맞으면 넣고, 아니면 안넣음.
            if(num == order[answer]) {
                answer++;
                answer = checkInSub(sub,answer, order);
                
            }else{
                if(sub.isEmpty()) sub.push(num);
                else{
                    // peek가 현재 들어가야할 수
                    if(sub.peek() == order[answer]){
                        
                        answer = checkInSub(sub,answer, order);
                    }else{
                    // 아닌경우 스택에 넣어둠.
                        sub.push(num);
                    }
                }
            }
        }
        
        
        
        
        return checkInSub(sub,answer, order);
    }
    
    public int checkInSub(Stack<Integer> sub, int answer, int[] order){
        while(!sub.isEmpty() && sub.peek() == order[answer]){
            sub.pop();
            answer++;
        }
        return answer;
    }
}

// if(!sub.isEmpty()){
//     System.out.println(orderIdx);
//     System.out.println(sub);
// }