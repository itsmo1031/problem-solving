N, M = map(int, input().split())
snack = [*map(int, input().split())]

start = 1
end = max(snack)
answer = 0

while start <= end:
    cnt = 0
    mid = (start + end) // 2
    for s in snack:
        if mid <= s:
            cnt += (s // mid)
    if cnt < N:
        end = mid - 1
    else:
        answer = mid
        start = mid + 1

print(answer)
