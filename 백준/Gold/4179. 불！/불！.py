from collections import deque
import sys

input = sys.stdin.readline


def fire(pos):
    visited = [[False] * C for __ in range(R)]
    q = deque([(pos[0], pos[1], 0)])
    visited[pos[0]][pos[1]] = True
    while q:
        x, y, t = q.popleft()
        for dx, dy in D:
            nx = x + dx
            ny = y + dy
            if 0 <= nx < R and 0 <= ny < C and maze[nx][ny] != '#':
                if t + 1 < fire_time[nx][ny] and not visited[nx][ny]:
                    fire_time[nx][ny] = t + 1
                    visited[nx][ny] = True
                    q.append((nx, ny, t + 1))


def get_out(pos):
    visited = [[False] * C for __ in range(R)]
    q = deque([(pos[0], pos[1], 0)])
    visited[pos[0]][pos[1]] = True
    while q:
        x, y, t = q.popleft()
        if x == 0 or y == 0 or x == R - 1 or y == C - 1:
            return t + 1
        for dx, dy in D:
            nx = x + dx
            ny = y + dy
            if 0 <= nx < R and 0 <= ny < C and maze[nx][ny] != '#':
                if t + 1 < fire_time[nx][ny] and not visited[nx][ny]:
                    visited[nx][ny] = True
                    q.append((nx, ny, t + 1))
    return -1


D = [(0, 1), (1, 0), (0, -1), (-1, 0)]
R, C = map(int, input().split())
maze = []
fire_time = [[int(1e9)] * C for __ in range(R)]
J = None

for i in range(R):
    line = [*input()]
    for j in range(C):
        if line[j] == 'J':
            line[j] = '.'
            J = (i, j)
    maze.append(line)

for i in range(R):
    for j in range(C):
        if maze[i][j] == 'F':
            fire_time[i][j] = 0
            fire((i, j))

answer = get_out(J)
print(answer if answer != -1 else "IMPOSSIBLE")
