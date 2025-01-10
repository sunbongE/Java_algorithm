import java.util.*;
/**
현재 자리에 올 수 있는 경우
1. 한 번만 이동해서 온다.
2. 2번만 이동해서 온다.
3. 섞어서 온다.

풀이
n개의 배열을 생성하고 짝수인경우 초기값을 2 나머지는 1로 설정하고
1~n까지 순회하면서 앞에 값을 자신의 값에다가 더한다.
마지막 n의 값을 리턴한다.
**/
class Solution {
    final int MOD = 1234567;
    
    public long solution(int n) {
        long answer = 0;
        long[] arr = new long[n+1];
        
        arr[0] = 1;
        arr[1] = 1;
        
        for(int i=2;i<=n;i++){
            arr[i] = (arr[i-2]+arr[i-1])%MOD;
        }
        
        
        
        answer = arr[n];
        return answer;
    }
}