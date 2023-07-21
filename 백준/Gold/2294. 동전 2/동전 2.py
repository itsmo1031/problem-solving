N, K = map(int, input().split())
dp = [0] + [int(1e9)] * K

for _ in range(N):
    coin = int(input())
    for i in range(coin, K+1):
        dp[i] = min(dp[i], dp[i-coin]+1)

print(-1 if dp[-1] == int(1e9) else dp[-1])