def p_sum(l, r):
    return prefix[r] - prefix[l - 1]


N = int(input())
honey = [0] + [*map(int, input().split())]
prefix = honey.copy()
answer = 0

for i in range(N):
    prefix[i + 1] += prefix[i]

for i in range(2, N):
    answer = max(answer, p_sum(2, N) + p_sum(i + 1, N) - honey[i])
    answer = max(answer, p_sum(1, N - 1) + p_sum(1, i - 1) - honey[i])
    answer = max(answer, p_sum(2, i) + p_sum(i, N - 1))

print(answer)
