import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] W = new int[n];
        int[] V = new int[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            W[i] = Integer.parseInt(st.nextToken());
            V[i] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[n + 1][k + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {

                if(j>= W[i-1]){
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-W[i-1]] + V[i-1]);
                }else{
                    dp[i][j] = dp[i-1][j];
                }


            }

        }
        System.out.println(dp[n][k]);

    }
}
