import java.io.*;
import java.util.*;
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] dp = new int[n][3];
        int[] arr = new int[n];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        dp[0][1] = arr[0];

        for (int i = 1; i < dp.length; i++) {
            // 안밟은
            dp[i][0] = Math.max(dp[i-1][1],dp[i-1][2]);
            // 1번 밟
            dp[i][1] = dp[i-1][0]+arr[i];
            // 2번 밟
            dp[i][2] = dp[i-1][1]+arr[i];
        }

        System.out.println(Math.max(dp[n-1][1],dp[n-1][2]));



    }
}