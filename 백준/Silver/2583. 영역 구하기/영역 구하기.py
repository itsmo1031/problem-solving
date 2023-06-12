from collections import deque

d = [(1, 0), (0, 1), (-1, 0), (0, -1)]


def bfs(pos):
    q = deque()
    q.append(pos)
    cnt = 1
    while q:
        x, y = q.popleft()
        for dx, dy in d:
            nx = dx + x
            ny = dy + y
            if 0 <= nx < N and 0 <= ny < M:
                if graph[nx][ny] == 0 and not visited[nx][ny]:
                    visited[nx][ny] = True
                    cnt += 1
                    q.append((nx, ny))
    return cnt


N, M, K = map(int, input().split())
graph = [[0] * M for __ in range(N)]
visited = [[False] * M for __ in range(N)]

for __ in range(K):
    ul_y, ul_x, dr_y, dr_x = map(int, input().split())
    for i in range(ul_x, dr_x):
        for j in range(ul_y, dr_y):
            if graph[i][j] != 1:
                graph[i][j] = 1

result = []

for i in range(N):
    for j in range(M):
        if graph[i][j] == 0 and not visited[i][j]:
            visited[i][j] = True
            result.append(bfs((i, j)))

print(len(result))
print(*sorted(result))
