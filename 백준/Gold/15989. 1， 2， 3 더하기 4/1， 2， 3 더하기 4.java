import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int LIMIT = 10003;
//    static int LIMIT = 13;

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[][] dp = new int[LIMIT][4];
        dp[1][1] = 1;
        dp[2][1] = 1;
        dp[2][2] = 1;
        dp[3][1] = 1;
        dp[3][2] = 1;
        dp[3][3] = 1;

        for (int i = 4; i < LIMIT; i++) {
            dp[i][1] = dp[i-1][1];
            dp[i][2] = dp[i-2][1] + dp[i-2][2];
            dp[i][3] = dp[i-3][1] + dp[i-3][2] + dp[i-3][3];
        }

        for (int i = 0; i < n; i++) {
            int cur = sc.nextInt();
           sb.append(dp[cur][1]+dp[cur][2]+dp[cur][3]+"\n");
        }

        System.out.println(sb);
//        for (int i = 0; i < dp.length; i++) {
//            System.out.println(i+" = "+Arrays.toString(dp[i]));
//        }
    }
}
