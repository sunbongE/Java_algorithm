
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 배열 길이 입력
        int[] arr = new int[n]; // 배열 초기화

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[n]; // DP 배열
        Arrays.fill(dp, 1); // 모든 값을 1로 초기화
        int ans = 1; // 최소 길이는 항상 1 이상

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i] && dp[i] < dp[j] + 1) { // 증가하는 부분수열 조건 확인
                    dp[i] = dp[j] + 1; // dp 갱신
                }
            }
            ans = Math.max(ans, dp[i]); // 최댓값 갱신
        }

        System.out.println(ans); // 최장 길이 출력
    }
}
