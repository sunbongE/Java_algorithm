import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * dp배열 생성 n개 만큼
 * 반복문 인덱스 1부터 시작하면 이전 자리 중에 현재 자리에 올 수 있는 자리를 파악
 * 올 수 있는 자리 => arr[j]+j >= i
 * 이중에 dp[j] 최소값 + 1 -> dp[i]
 * print(dp[n-1])
 **/
public class Main {
    static final int MAX = 10000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        int[] dp = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i < n; i++) {
            int min = MAX;
            for (int j = i - 1; j >= 0; j--) {
                if(arr[j]+j >= i){ // 현재 위치에 점프해서 올 수 있는 것들 중 최소 점프횟수
                    min = Math.min(min, dp[j]);
                }
            }
            // 현재 점프 = 이제까지 최소 점프 + 1
            dp[i] = min+1;
        }
        System.out.println(dp[n-1] < MAX ? dp[n-1] : -1);
    }
}
