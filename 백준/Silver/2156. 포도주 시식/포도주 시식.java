
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 3번 이상은 못마심.
 * dp에서 매번 최대값을 갱신했다는 가정하에
 * 현재 최대가 되려면 [i-1]+wine[i], [i-2]+wine, [i-3]+wine
 * <p>
 * dp[0] = wine[0]
 * dp[1] = wine[0]+wine[1]
 * dp[2] = max(dp[1], wine[0]+wine[2], wine[1]+wine[2])
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
        int[] wine = new int[N];
        int[] dp = new int[N];
        for (int i = 0; i < N; i++) {
            wine[i] = Integer.parseInt(bf.readLine());
        }

        dp[0] = wine[0];
        if (N > 1) {
            dp[1] = dp[0] + wine[1];
        }
        if (N > 2) {
            dp[2] = Math.max(dp[1], Math.max(wine[1] + wine[2], wine[0] + wine[2]));
        }

        // 현재값 = max(전값, 전전값+현재와인, 전전전+전와인+현재와인)
        // oox, oxo, xoo 이렇게 3번 마시는 경우를 배제한다.
        for (int i = 3; i < N; i++) {
            dp[i] = Math.max(dp[i - 1], Math.max(dp[i - 2] + wine[i], dp[i - 3] + wine[i - 1] + wine[i]));
        }

        System.out.println(dp[N - 1]);
    }
}
