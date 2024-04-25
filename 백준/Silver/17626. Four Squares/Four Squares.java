 

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

//        int x = (int) Math.sqrt(y);

        int[] dp = new int[n + 1];

        for (int i = 0; i < n + 1; i++) dp[i] = i;


        for (int i = 1; i <= (int) Math.sqrt(n); i++) {
            int tmp = i*i;
            for (int j = 0; j <= n; j++) {
                if(tmp>j) continue;
                dp[j] = Math.min(dp[j],dp[j-tmp]+1);
            }
        }
//        System.out.println(Arrays.toString(dp));
        System.out.println(dp[n]);


    }
}
