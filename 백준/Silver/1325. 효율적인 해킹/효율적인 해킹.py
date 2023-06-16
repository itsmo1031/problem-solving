from collections import deque

N, M = map(int, input().split())
C = [[] for _ in range(N + 1)]
res = [(x, 0) for x in range(N + 1)]


def bfs(idx):
    visited = [False] * (N + 1)
    q = deque([idx])
    visited[idx] = True
    cnt = 1
    while q:
        n = q.popleft()
        for nxt in C[n]:
            if not visited[nxt]:
                visited[nxt] = True
                cnt += 1
                q.append(nxt)
    return cnt


for __ in range(M):
    a, b = map(int, input().split())
    C[b].append(a)

for i in range(1, N + 1):
    res[i] = (i, bfs(i))

res = sorted(res[1:], key=lambda x: (-x[1], x[0]))

print(*map(lambda x: x[0], filter(lambda x: x[1] == res[0][1], res)))
