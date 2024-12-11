import java.util.*;

public class Solution {
    public int solution(int n) {
        int ans = 0;

        // top down
        while(n!=1){
            // 홀짝 구분
            if(n%2==0){
                n = n/2;
            }else{
                n = n-1;
                ans++;
            }
        }
        
        ans++;

        return ans;
    }
}