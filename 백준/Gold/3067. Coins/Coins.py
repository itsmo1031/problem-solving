for _ in range(int(input())):
    N = int(input())
    coins = [*map(int, input().split())]
    M = int(input())
    dp = [0] * (M + 1)
    dp[0] = 1

    for c in coins:
        for j in range(c, M + 1):
            dp[j] += dp[j - c]

    print(dp[-1])