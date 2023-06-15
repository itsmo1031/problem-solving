N = int(input())
max_r = [*map(int, input().split())]
min_r = max_r.copy()

for __ in range(N - 1):
    max_n = [*map(int, input().split())]
    min_n = max_n.copy()
    for j in range(3):
        if j == 0:
            max_n[j] += max(max_r[:2])
            min_n[j] += min(min_r[:2])
        elif j == 1:
            max_n[j] += max(max_r)
            min_n[j] += min(min_r)
        else:
            max_n[j] += max(max_r[1:])
            min_n[j] += min(min_r[1:])
    max_r = max_n
    min_r = min_n

print(max(max_r), min(min_r))