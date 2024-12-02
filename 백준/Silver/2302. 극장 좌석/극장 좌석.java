import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int ans=1;
        int n = Integer.parseInt(br.readLine());

        int[] dp = new int[41];

        dp[0]=1;
        dp[1]=1;
        dp[2]=2;
        dp[3]=3;
        dp[4]=5;

        for (int i = 5; i <= n; i++) {
            dp[i] = dp[i-1]+dp[i-2];
        }

        int m = Integer.parseInt(br.readLine());
        int[] arr = new int[m];
        
        for (int i = 0; i < m; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int pre=0;
        for(Integer vip: arr){
            ans *= dp[vip-1-pre];
            pre = vip;
        }
        ans *= dp[n-pre];
        System.out.println(ans);

    }
}
