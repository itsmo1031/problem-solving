from collections import deque

D = [(1, 0), (0, 1), (-1, 0), (0, -1)]
N, M = map(int, input().split())
maze = [[*map(int, input())] for _ in range(N)]
visited = [[[False] * 2 for _ in range(M)] for _ in range(N)]


def bfs():
    visited[0][0][0] = True
    q = deque([(0, 0, 1, 0)])

    while q:
        x, y, c, w = q.popleft()
        if x == N - 1 and y == M - 1:
            return c
        for dx, dy in D:
            nx = x + dx
            ny = y + dy
            if 0 <= nx < N and 0 <= ny < M and not visited[nx][ny][w]:
                if maze[nx][ny] == 1:
                    if not w:
                        visited[nx][ny][1] = True
                        q.append((nx, ny, c + 1, 1))
                else:
                    visited[nx][ny][w] = True
                    q.append((nx, ny, c + 1, w))
    return -1


print(bfs())
