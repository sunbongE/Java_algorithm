 

import java.io.*;
import java.util.*;

/**
 * 스택에는 오름차순의 높이가 올 수 있도록 한다.
 * 이유 :
 *
 * 높이가 낮아지면 이전에 있던 사각형의 넓이를 파악해서 최대값을 갱신한다.
 * 갱신된 값은 버리고, 현재 값을 넣어 다음에 활용할 수 있게함.
 * 이 과정을 배열을 모든 높이를 순회할때까지 반복
 * 마지막으로 스택에 남아있는 높이들의 사각형 넓이를 구하고 최대값을 갱신한다.
 */
public class Main {
    static int N; // 도시의 개수
    static int[][] W; // 비용 행렬
    static int[][] dp; // DP 테이블: dp[visited][i]는 방문 상태가 visited일 때 i번 도시에서 시작하는 최소 비용을 저장

    static final int INF = 1_000_000_000; // 큰 수로 초기화하기 위한 값 (무한대 역할)

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력 받기
        N = sc.nextInt();
        W = new int[N][N];

        // 비용 행렬 W[i][j] 입력
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                W[i][j] = sc.nextInt();
            }
        }

        // DP 테이블을 -1로 초기화 (아직 계산되지 않은 상태)
        dp = new int[1 << N][N];
        for (int i = 0; i < (1 << N); i++) {
            for (int j = 0; j < N; j++) {
                dp[i][j] = -1;
            }
        }

        // 시작 도시가 0번일 때 모든 도시를 방문하고 돌아오는 최소 비용 출력
        int result = tsp(1, 0);
        System.out.println(result);
    }

    // 외판원 순회 알고리즘 (DP + 비트마스크)
    // visited: 현재까지 방문한 도시 상태를 비트마스크로 표현
    // current: 현재 위치한 도시
    static int tsp(int visited, int current) {
        // 모든 도시를 방문했다면 (visited가 모든 비트가 1인 상태가 되면) 시작점으로 돌아가기
        if (visited == (1 << N) - 1) {
            // 현재 위치에서 시작점(0)으로 가는 비용을 반환, 길이 없으면 INF 반환
            return W[current][0] == 0 ? INF : W[current][0];
        }

        // 이미 계산한 적이 있는 경우 (메모이제이션)
        if (dp[visited][current] != -1) {
            return dp[visited][current];
        }

        dp[visited][current] = INF; // 최소 비용을 찾기 위해 초기화

        // 모든 도시를 하나씩 방문
        for (int next = 0; next < N; next++) {
            // next 도시로 갈 수 있는지 확인 (visited에 포함되어 있지 않고, 경로가 존재해야 함)
            if ((visited & (1 << next)) == 0 && W[current][next] != 0) {
                // 다음 도시를 방문한 상태에서 최소 비용을 계산하여 갱신
                int cost = W[current][next] + tsp(visited | (1 << next), next);
                dp[visited][current] = Math.min(dp[visited][current], cost);
            }
        }

        return dp[visited][current];
    }
}