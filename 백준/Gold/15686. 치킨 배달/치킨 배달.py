from itertools import combinations

n, m = map(int, input().split())
home = []
chicken = []
for i in range(n):
    info = [*map(int, input().split())]
    for j in range(n):
        if info[j] == 1:
            home.append((i, j))
        if info[j] == 2:
            chicken.append((i, j))

result = []

for temp in combinations(chicken, m):  # 임시 치킨집 리스트들중에서
    city_len = 0
    for h in home:
        c_len = 101
        for c in temp:
            c_len = min(c_len, abs(h[0] - c[0]) + abs(h[1] - c[1]))
        city_len += c_len
    result.append(city_len)

print(min(result))