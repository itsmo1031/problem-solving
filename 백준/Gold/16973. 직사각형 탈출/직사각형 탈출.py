from collections import deque

D = [(0, 1), (0, -1), (1, 0), (-1, 0)]

N, M = map(int, input().split())
board = []
wall = []
for a in range(N):
    line = [*map(int, input().split())]
    for b in range(M):
        if line[b] == 1:
            wall.append((a, b))
    board.append(line)

visited = [[False] * M for __ in range(N)]
H, W, Sr, Sc, Fr, Fc = map(int, input().split())


def bfs(pos):
    x, y = pos
    visited[x][y] = True
    q = deque([(x, y, 0)])
    while q:
        x, y, c = q.popleft()
        if x == Fr - 1 and y == Fc - 1:
            return c
        for dx, dy in D:
            nx = x + dx
            ny = y + dy
            if 0 <= nx < N - H + 1 and 0 <= ny < M - W + 1 and not visited[nx][ny]:
                visited[nx][ny] = True
                if check((nx, ny)):
                    q.append((nx, ny, c + 1))
    return -1


def check(pos):
    x, y = pos
    for wx, wy in wall:
        if x <= wx < x + H and y <= wy < y + W:
            return False
    return True


print(bfs((Sr - 1, Sc - 1)))
