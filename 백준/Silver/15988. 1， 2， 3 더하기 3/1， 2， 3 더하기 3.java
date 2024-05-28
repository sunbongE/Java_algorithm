import java.util.Scanner;

public class Main {

    static int LIMIT = 1000010;
    static int MOD = 1000000009;
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        long[] dp = new long[LIMIT];

        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;


        for (int i = 4; i < LIMIT; i++) {
            dp[i] = (dp[i-3]+dp[i-2]+dp[i-1])%MOD;
        }

        for (int i = 0; i < N; i++) {
            int n = sc.nextInt();

            if(0<=n && n<=3){
                System.out.println(dp[n]%MOD);
            }else{

            System.out.println(dp[n]%MOD);
            }
        }

    }
}
