N, K = map(int, input().split())
item = []
dp = [0] * (K + 1)

for _ in range(N):
    W, V = map(int, input().split())
    item.append((W, V))

for i in range(N):
    for j in range(K, -1, -1):
        if item[i][0] <= j:
            dp[j] = max(dp[j], dp[j - item[i][0]] + item[i][1])

print(dp[-1])