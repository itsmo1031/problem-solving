from collections import defaultdict

d = defaultdict(int)

for __ in range(int(input())):
    _, e = input().split('.')
    d[e] += 1

for e, c in sorted(d.items()):
    print(e, c)
