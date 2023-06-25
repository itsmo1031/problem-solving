N, M = map(int, input().split())
tree = [*map(int, input().split())]

sp = 0
ep = max(tree)

answer = 0
while sp <= ep:
    total = 0
    mp = (sp + ep) // 2
    for t in tree:
        if t > mp:
            total += t - mp
    if total < M:
        ep = mp - 1
    else:
        answer = mp
        sp = mp + 1

print(answer)