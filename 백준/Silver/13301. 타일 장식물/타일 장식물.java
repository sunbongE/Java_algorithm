
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        long[] dp = new long[81];
        dp[0] = 1L;
        dp[1] = 1L;
        dp[2] = 2L;

        if(n < 3){
            System.out.println((dp[n]+dp[n-1])*2);
            return;
        }

        for (int i = 3; i < dp.length; i++) {
            dp[i] = dp[i-1]+dp[i-2];
        }

        System.out.println((dp[n]+dp[n-1])*2);

    }
}