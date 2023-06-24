from collections import deque


def bfs(x, y):
    visited = [[False] * M for __ in range(N)]
    safe[x][y] = 0
    visited[x][y] = True
    q = deque([(x, y, 0)])
    while q:
        qx, qy, c = q.popleft()
        for dx, dy in D:
            nx = qx + dx
            ny = qy + dy
            if 0 <= nx < N and 0 <= ny < M:
                if not visited[nx][ny]:
                    safe[nx][ny] = min(safe[nx][ny], c + 1)
                    visited[nx][ny] = True
                    q.append((nx, ny, c + 1))


D = [(1, 0), (0, 1), (1, 1), (-1, 0), (0, -1), (-1, -1), (1, -1), (-1, 1)]
N, M = map(int, input().split())
shark = [[*map(int, input().split())] for __ in range(N)]
safe = [[int(1e9)] * M for __ in range(N)]

for i in range(N):
    for j in range(M):
        if shark[i][j] == 1:
            bfs(i, j)

print(max(map(max, safe)))
