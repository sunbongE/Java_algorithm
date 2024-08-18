import java.util.*;
/**
큐를 우선 만든다.
연산 횟수를 기록할 cnt를 0으로 초기화.

각 큐의 값의 합을 sum1, sum2에 기록한다.

sum1, sum2가 같으면 횟수를 리턴한다.
sum1>sum2 경우
q1에서 poll하고
q2에 offer한다.
poll한 값을 sum1에서 빼고, sum2에 더한다.

반대인 경우
q2에서 poll하고
q1에 offer한다.
poll한 값을 sum2에서 빼고, sum1에 더한다.

위 과정을 n*4번 했는데 결과가 안나오면 -1을 리턴한다.

1.
    1,1,1
    5
2.
    1,1,1,5
    0
3.
    1,1,5
    1
4.
    1,5
    1,1
5.
    5
    1,1,1
6. 
    0
    1,1,1,5
7.
    1
    1,1,5
8. 원상태로 돌아옴
    1,1
    1,5

**/

class Solution {

    Queue<Integer> q1;
    Queue<Integer> q2;
    int answer=0; // 연산 횟수
    long sum1,sum2;
    public int solution(int[] queue1, int[] queue2) {
        // 큐만들기
        q1 = setQ(queue1);
        q2 = setQ(queue2);
        
        this.sum1 = getSum(queue1);
        this.sum2 = getSum(queue2);
        
        int n = queue1.length;
        while(answer < n*4){
            if(sum1 == sum2){
                return answer;
            }else if(sum1 > sum2){
                int pollNum = q1.poll();
                q2.offer(pollNum);
                
                sum1 -= pollNum;
                sum2 += pollNum;
            }else{
                int pollNum = q2.poll();
                q1.offer(pollNum);
                
                sum2 -= pollNum;
                sum1 += pollNum;
            }
            answer++;
        }
        return -1;
        
        
        
        
        
    }
    
    private Queue<Integer> setQ(int[] qArr){
        Queue<Integer> resultQ = new LinkedList<>();
        for(Integer num : qArr){
            resultQ.offer(num);
        }
        return resultQ;
    }
    
    private long getSum(int[] qArr){
        long sum = 0;
        for(Integer num:qArr){
            sum+=num;
        }
        return sum;
    }
}