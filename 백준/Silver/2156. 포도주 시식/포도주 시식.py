N = int(input())
wine = [int(input()) for __ in range(N)]
dp = wine.copy()
for i in range(1, N):
    if i < 4:
        if i == 1:
            dp[i] += wine[0]
        if i == 2:
            dp[i] += max(wine[0], wine[1])
        if i == 3:
            dp[i] += max(dp[i - 2], dp[i - 3] + wine[i - 1])
        continue
    else:
        dp[i] += max(dp[i - 2], dp[i - 3] + wine[i - 1], dp[i - 4] + wine[i - 1])

print(max(dp))
