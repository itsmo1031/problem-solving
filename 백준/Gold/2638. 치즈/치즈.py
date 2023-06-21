from collections import deque


def hole_check():
    res = [[0] * M for __ in range(N)]
    visited = [[False] * M for __ in range(N)]
    q = deque([(0, 0)])
    while q:
        x, y = q.popleft()
        for dx, dy in D:
            nx = x + dx
            ny = y + dy
            if 0 <= nx < N and 0 <= ny < M:
                if cheese[nx][ny] == 0 and not visited[nx][ny]:
                    res[nx][ny] = 1
                    visited[nx][ny] = True
                    q.append((nx, ny))
    return res


def melt_check(pos):
    x, y = pos
    chk = 0
    for dx, dy in D:
        nx = x + dx
        ny = y + dy
        if 0 <= nx < N and 0 <= ny < M:
            if air[nx][ny]:
                chk += 1

    return True if chk > 1 else False


D = [(0, 1), (0, -1), (1, 0), (-1, 0)]
N, M = map(int, input().split())
cheese = [[*map(int, input().split())] for __ in range(N)]
hour = 0

while True:
    eliminate = []
    air = hole_check()
    for i in range(N):
        for j in range(M):
            if cheese[i][j] == 1 and melt_check((i, j)):
                eliminate.append((i, j))
    if not eliminate:
        break
    for ex, ey in eliminate:
        cheese[ex][ey] = 0
    hour += 1

print(hour)
