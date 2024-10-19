
import java.io.*;
import java.util.*;

public class Main {
    static int[][] m;
    static int ans = 0;
    static boolean[] rv,cv,drv,dlv;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        cv = new boolean[n];
        rv = new boolean[n];
        drv = new boolean[n*2];
        dlv = new boolean[n*2];

        m = new int[n][n];

        dfs(0, n);
        System.out.println(ans);
    }

    private static void dfs(int n, int N) {
        if (n == N) {
            ans++;
            return;
        }

        // 조건에 맞으면 놓으셈
        for (int i = 0; i < N; i++) {
            if(!cv[i] && !rv[i] && !dlv[n+i] && !drv[n-i+N]){
                rv[i] = true;
                cv[i] = true;
                dlv[n+i] = true;
                drv[n-i+N]  = true;
                dfs(n+1,N);
                cv[i] = false;
                rv[i] = false;
                dlv[n+i] = false;
                drv[n-i+N]  = false;
            }
        }


    }
}