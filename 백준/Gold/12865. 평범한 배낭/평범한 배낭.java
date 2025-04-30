import java.io.*;
import java.util.*;
 
public class Main {
    static int n,k,w,v;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      //  BufferedReader br = new BufferedReader(new FileReader("test.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        int[] W = new int[n+1], V = new int[n+1];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            W[i] = w;
            V[i] = v;
        }

        int[][] dp = new int[n+1][k+1];

        for (int i = 1; i <= n; i++) {
            int curW = W[i];
            int curV = V[i];
            for (int j = 1; j <= k; j++) {
                if(j >= curW){
                    dp[i][j] = Math.max(dp[i-1][j-curW]+curV, dp[i-1][j]);
                }else{
                    dp[i][j] = dp[i-1][j];
                }
            }
//            System.out.println(Arrays.toString(dp[i]));
        }
        System.out.println(dp[n][k]);

    }
}
