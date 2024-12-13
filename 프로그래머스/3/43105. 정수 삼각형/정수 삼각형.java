import java.util.*;
class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        int lastL = triangle[triangle.length-1].length;
        int n = lastL + (lastL-1);
        int h = triangle.length;
        
        int[][] dp = new int[h][n];
        
        for(int i=0; i<h; i++){
            Arrays.fill(dp[i],-1);
        }
        
        int mid = n/2;
        for(int i = 0; i<triangle.length; i++){
            int diff = triangle[i].length-1;
            for(int j=0; j< triangle[i].length; j++){ // 배열 길이 만큼.
                dp[i][mid-diff] = triangle[i][j];
                diff-=2;
            }
        }
        
        
        for(int i = 1; i<h; i++){
            for(int j=0; j< n; j++){ // 배열 길이 만큼.
                if(dp[i][j] == -1 ) continue;
                // 양쪽 끝인 경우 따로 처리.
                if(j==0){
                    dp[i][j] += dp[i-1][j+1];
                }else if(j==n-1){
                    dp[i][j] += dp[i-1][j-1];
                }else{
                    dp[i][j] += Math.max(dp[i-1][j+1],dp[i-1][j-1]);
                }
            }
        }

        for(int i=0; i<n; i++){
            answer = Math.max(answer, dp[h-1][i]);
        }
        
        return answer;
    }
}