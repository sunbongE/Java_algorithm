import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
/**
 * dp
 * 1. arr[i]보다 이전 값중에 arr[i]보다 작은 수
 * 2. 1에서 사용한 인덱스 기준으로 dp[i]가 최대인 값에 +1 한 값
 * 3. dp[i]의 최대값을 ans에 갱신.
 *
 * **/
public class Main {
    public static void main(String[] args) throws IOException {
        int ans =1 ;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int[] dp = new int[n];

        Arrays.fill(dp,1);

        for (int i = 1; i < n; i++) {
            int cur = arr[i];
            for (int j = i-1; j >= 0; j--) {
                if(arr[j] < cur && dp[i] < dp[j]+1){
                    dp[i] = dp[j]+1;
                    ans = Math.max(ans, dp[i]);
                }
            }
        }
//        System.out.println(Arrays.toString(dp));
        System.out.println(ans );
    }
}
