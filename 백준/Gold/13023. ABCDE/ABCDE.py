N, M = map(int, input().split())
people = [[] for __ in range(N)]
result = False
visited = [False] * N

for __ in range(M):
    a, b = map(int, input().split())
    people[a].append(b)
    people[b].append(a)


def dfs(idx, cnt):
    global result
    if cnt == 5 or result:
        result = True
        return
    visited[idx] = True
    for n in people[idx]:
        if not visited[n]:
            dfs(n, cnt + 1)
            visited[n] = False


for i in range(N):
    dfs(i, 1)
    visited[i] = False
    if result:
        break

print(1 if result else 0)
