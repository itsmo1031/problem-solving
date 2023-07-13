N, T = map(int, input().split())
time = [0] * (T + 1)

for _ in range(N):
    K, S = map(int, input().split())
    for i in range(T, -1, -1):
        if K <= i:
            time[i] = max(time[i], time[i - K] + S)

print(time[-1])