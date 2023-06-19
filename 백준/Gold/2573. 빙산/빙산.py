from collections import deque


def bfs(pos):
    q = deque([pos])

    while q:
        x, y = q.popleft()
        cnt = 0
        for dx, dy in D:
            nx = x + dx
            ny = y + dy
            if ice[nx][ny] == 0:
                cnt += 1
            else:
                if not visited[nx][ny]:
                    visited[nx][ny] = True
                    q.append((nx, ny))
        melt[x][y] = cnt if ice[x][y] > cnt else ice[x][y]
    return 1


D = [(0, 1), (0, -1), (1, 0), (-1, 0)]
N, M = map(int, input().split())
ice = [[*map(int, input().split())] for __ in range(N)]

year = 0
while True:
    visited = [[False] * M for _ in range(N)]
    melt = [[0] * M for _ in range(N)]
    iceberg = 0

    for i in range(N):
        for j in range(M):
            if ice[i][j] != 0 and not visited[i][j]:
                visited[i][j] = True
                iceberg += bfs((i, j))

    if iceberg >= 2:
        break
    if iceberg == 0:
        year = 0
        break
    
    for i in range(N):
        for j in range(M):
            ice[i][j] -= melt[i][j]
    year += 1

print(year)