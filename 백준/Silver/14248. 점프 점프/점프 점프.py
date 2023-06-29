def dfs(p):
    visited[p] = True
    if 0 <= p + rock[p] < n and not visited[p + rock[p]]:
        dfs(p + rock[p])
    if 0 <= p - rock[p] < n and not visited[p - rock[p]]:
        dfs(p - rock[p])


n = int(input())

rock = [*map(int, input().split())]
cur = int(input()) - 1
visited = [False] * n

dfs(cur)
print(visited.count(True))
