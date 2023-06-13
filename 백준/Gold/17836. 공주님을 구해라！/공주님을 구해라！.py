from collections import deque

D = [(0, 1), (1, 0), (-1, 0), (0, -1)]
N, M, T = map(int, input().split())
board = [[*map(int, input().split())] for __ in range(N)]


def bfs():
    visited = [[False] * M for _ in range(N)]
    visited[0][0] = True
    q = deque([(0, 0, 0)])  # x, y, t
    res = int(1e9)
    while q:
        x, y, t = q.popleft()
        if t > T:
            continue
        if x == N - 1 and y == M - 1:
            res = min(res, t)
        for dx, dy in D:
            nx = x + dx
            ny = y + dy
            if 0 <= nx < N and 0 <= ny < M and not visited[nx][ny]:
                if board[nx][ny] == 1:
                    continue
                if board[nx][ny] == 2:
                    left_x = N - 1 - nx
                    left_y = M - 1 - ny
                    res = min(res, left_x + left_y + t + 1)
                visited[nx][ny] = True
                q.append((nx, ny, t + 1))
    return print("Fail" if res > T else res)


bfs()