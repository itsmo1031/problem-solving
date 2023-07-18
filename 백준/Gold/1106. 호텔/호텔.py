C, N = map(int, input().split())
city = [0] + [int(1e9)] * (C + 100)

for _ in range(N):
    cost, visitor = map(int, input().split())
    for i in range(C + 101):
        if visitor <= i:
            city[i] = min(city[i], city[i - visitor] + cost)

print(min(city[C:]))