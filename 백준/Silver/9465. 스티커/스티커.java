import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        int tc = Integer.parseInt(br.readLine());
        int[][] in;
        int n;
        
        for (int t = 0; t <tc ; t++) {

            n = Integer.parseInt(br.readLine());
            in = new int[2][n];
            for (int i = 0; i < 2; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    in[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int[][] dp = new int[3][n+1];

            dp[1][1] = in[0][0];
            dp[2][1] = in[1][0];

            for (int i = 2; i <= n; i++) {

                dp[0][i] = Math.max(dp[1][i-1], dp[2][i-1]);
                dp[1][i] = Math.max(dp[0][i-1], dp[2][i-1])+in[0][i-1];
                dp[2][i] = Math.max(dp[0][i-1], dp[1][i-1])+in[1][i-1];
            }

            sb.append(Math.max(dp[1][n], dp[2][n])).append("\n");
        }
        System.out.println(sb);
        br.close();
    }
}
