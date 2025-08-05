T = int(input())

for tc in range(1, T + 1):
    N, M = map(int, input().split())
    S = []
    P = []
    
    for _ in range(M):
        s, p = map(int, input().split())
        S.append(s)
        P.append(p)

    # dp[i][j]: i번째 아이템까지 고려해서 크기 j일 때 최대 가치
    dp = [[0] * (N + 1) for _ in range(M + 1)]

    for i in range(1, M + 1):
        for j in range(N + 1):
            if S[i - 1] > j:
                dp[i][j] = dp[i - 1][j]
            else:
                dp[i][j] = max(
                    dp[i - 1][j],
                    dp[i - 1][j - S[i - 1]] + P[i - 1]
                )

    print(f"#{tc} {dp[M][N]}")
