N, K = map(int, input().split())

dp = [0] * (N + 1)

for _ in range(K):
    I, K = map(int, input().split())
    for i in range(N, -1, -1):
        if K <= i:
            dp[i] = max(dp[i], dp[i - K] + I)

print(dp[-1])