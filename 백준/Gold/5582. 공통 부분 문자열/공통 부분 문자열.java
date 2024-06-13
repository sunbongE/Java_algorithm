import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String s1 = bf.readLine();
        String s2 = bf.readLine();

        System.out.println(lcs(s1,s2));
    }

    private static int lcs(String s1, String s2){

        int m = s1.length();
        int n = s2.length();
        int maxValue = 0;
        int[][] dp = new int[m+1][n+1];

        for(int i = 1; i<=m;i++){
            for(int j=1; j<=n;j++ ){
                if(s1.charAt(i-1)== s2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1]+1;
                    int tmp = dp[i][j];
                    maxValue = (maxValue < tmp ) ? tmp:maxValue;
                }else{
                    dp[i][j]=0;
                }
            }
        }
        return maxValue;
    }
}
