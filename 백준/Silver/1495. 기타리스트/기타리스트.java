
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n, s, m;
        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int[] arr = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        boolean dp[][] = new boolean[n+1][m+1];
        dp[0][s] = true;

        for (int i = 1; i <=n; i++) {
            for (int j = 0; j <= m; j++) {
                if(dp[i-1][j]){
                    int plus = j + arr[i];
                    int mi = j - arr[i];
                    if(plus <= m) dp[i][plus] = true;
                    if(mi >= 0) dp[i][mi] = true;
                }
            }
        }

        int ans =-1;
        for (int i = m; i >=0; i--) {
            if(dp[n][i]) {
                ans = i;
                break;
            }

        }
        System.out.println(ans);
    }
}