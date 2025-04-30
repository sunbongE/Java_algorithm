import java.io.*;
import java.util.*;
/**
 * 문제
 * n가지 종류의 동전이 있다. 이 동전들을 적당히 사용해서, 그 가치의 합이 k원이 되도록 하고 싶다.
 * 그러면서 동전의 개수가 최소가 되도록 하려고 한다. 각각의 동전은 몇 개라도 사용할 수 있다.
 *
 * 입력
 * 첫째 줄에 n, k가 주어진다. (1 ≤ n ≤ 100, 1 ≤ k ≤ 10,000) 다음 n개의 줄에는 각각의 동전의 가치가 주어진다.
 * 동전의 가치는 100,000보다 작거나 같은 자연수이다. 가치가 같은 동전이 여러 번 주어질 수도 있다.
 *
 * 출력
 * 첫째 줄에 사용한 동전의 최소 개수를 출력한다. 불가능한 경우에는 -1을 출력한다.
 *
 * 예제 입력 1
 * 3 15
 * 1
 * 5
 * 12
 * 예제 출력 1
 * 3
 *
 * */
public class Main {
    static int n,k,coin[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  //      BufferedReader br = new BufferedReader(new FileReader("test.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        // Set을 사용하여 중복 제거
        Set<Integer> coinSet = new HashSet<>();
        for (int i = 0; i < n; i++) {
            coinSet.add(Integer.parseInt(br.readLine()));
        }

        // Set을 배열로 변환
//        coin = coinSet.stream().mapToInt(Integer::intValue).toArray();

        // dp 배열 초기화
        int[] dp = new int[k + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0; // 0원을 만들기 위해서는 동전이 필요 없음

        // 동전 가치에 따라 dp 배열 업데이트
//        for (int curCoin : coin) {
        for(int curCoin : coinSet){
            for (int j = curCoin; j <= k; j++) {
                if (dp[j - curCoin] != Integer.MAX_VALUE) {
                    dp[j] = Math.min(dp[j], dp[j - curCoin] + 1);
                }
            }
//            System.out.println(Arrays.toString(dp));
        }

        // 결과 출력
        System.out.println(dp[k] == Integer.MAX_VALUE ? -1 : dp[k]);
    }
}
