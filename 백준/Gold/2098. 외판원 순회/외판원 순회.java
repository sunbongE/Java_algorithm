 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N; // 도시의 개수
    static int[][] W; // 비용 행렬
    static int[][] dp; // DP 테이블: dp[visited][i]는 방문 상태가 visited일 때 i번 도시에서 시작하는 최소 비용을 저장
    static final int INF = 1_000_000_000; // 큰 수로 초기화하기 위한 값 (무한대 역할)

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        W = new int[N][N];
        StringTokenizer st;

        for (int i = 0; i < N; i++) {
            st =new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                W[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // dp 테이블 초기화.
        dp = new int[1<<N][N];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i],-1);
        }

        int result = TSP(1,0);
        System.out.println(result);

    }

    private static int TSP(int visited, int cur) {
        if(visited == (1<<N)-1){ // 모두 방문한 경우.
            return W[cur][0] == 0 ? INF : W[cur][0];
        }

        if(dp[visited][cur] != -1){ // 이미 값이 갱신 된경우.
            return dp[visited][cur];
        }

        dp[visited][cur] = INF; // 최소비용 찾기위해
        // 모든 도시 방문
        for (int next = 0; next < N; next++) {
            // 미방문, next로 갈 수 있는지
            // 현재에서 다음으로 가는 비용 + 최소비용
          if ((visited & (1 << next)) == 0 && W[cur][next] != 0) {
                int cost = W[cur][next] + TSP(visited | (1 << next), next);
                dp[visited][cur] = Math.min(cost, dp[visited][cur]);
            }
        }

        return dp[visited][cur];
    }
}
