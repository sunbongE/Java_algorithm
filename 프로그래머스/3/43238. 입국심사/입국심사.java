import java.util.Arrays;

class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        Arrays.sort(times);       
        long min = 0;
        long max = (long)times[times.length-1] * n;
        
        while(min <= max){
            long mid = (min + max) / 2;
            long cnt = 0;
            for(Integer t : times){
                cnt += mid/(long)t;
            }
     
            if(cnt < n){ // 목표값보다 크면 최대값을 중간값으로
                min = mid+1;
            }else{// 목표값보다 크면 최소값을 중간값으로
                max = mid-1;
                answer = mid;
            }
        }
        return answer;
    }
}