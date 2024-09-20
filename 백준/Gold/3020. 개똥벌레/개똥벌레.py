from bisect import bisect_left

N, H = map(int, input().split())
floor = []
ceil = []
for __ in range(N // 2):
    floor.append(int(input()))
    ceil.append(int(input()))

floor.sort()
ceil.sort()
result = N + 1
res_cnt = 0

for i in range(H):
    floor_cnt = N // 2 - bisect_left(floor, i + 1)
    ceil_cnt = N // 2 - bisect_left(ceil, H - i)
    cnt = floor_cnt + ceil_cnt
    if cnt == result:
        res_cnt += 1
    if cnt < result:
        result = cnt
        res_cnt = 1

print(result, res_cnt)