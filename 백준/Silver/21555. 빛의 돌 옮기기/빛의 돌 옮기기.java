
import java.io.*;
import java.util.*;

public class Main {
    static long n, k, arr[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Long.parseLong(st.nextToken());
        k = Long.parseLong(st.nextToken());

        arr = new long[2][(int) n];
        long[][] dp = new long[2][(int) n];


        for (int i = 0; i < 2; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Long.parseLong(st.nextToken());
            }
        }

        dp[0][0] = arr[0][0];
        dp[1][0] = arr[1][0];

        for (int j = 1; j < n; j++) {
            dp[0][j] = Math.min(dp[0][j-1], dp[1][j-1]+k)+arr[0][j];
            dp[1][j] = Math.min(dp[1][j-1], dp[0][j-1]+k)+arr[1][j];
        }

        System.out.println(Math.min(dp[0][(int)n-1],dp[1][(int)n-1]));


    }
}