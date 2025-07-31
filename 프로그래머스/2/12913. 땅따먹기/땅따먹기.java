
import java.util.*;
class Solution {
    static int[][] dp;
    int solution(int[][] land) {
        int answer = 0;
        int len = land.length;
        this.dp = land;
        
        for(int i=1; i<len;i++){
            for(int j=0; j<4;j++){
                int targetScore=0;
                for(int k=0; k<4;k++){
                    if(k==j) continue;
                    targetScore = Math.max(targetScore,dp[i-1][k]); // 이전위치 중 최대 값에서 현재 위치를 밟도록.
                }   
                dp[i][j] += targetScore;
            }
        }
        
        // for(int i=0;i<len;i++){
        //     System.out.println(Arrays.toString(dp[i]));
        // }
        for(int i=0;i<len;i++){
            for(int j=0; j<4;j++){
                answer = Math.max(dp[i][j],answer);
            }
        }
        return answer;
    }
}