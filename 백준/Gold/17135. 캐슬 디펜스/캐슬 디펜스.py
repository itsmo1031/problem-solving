from collections import deque
from copy import deepcopy
from itertools import combinations

N, M, D = map(int, input().split())

DIR = [(0, -1), (-1, 0), (0, 1)]
game = [[*map(int, input().split())] for _ in range(N)]


def attack(n):
    visited = [[False] * M for _ in range(new_N)]
    q = deque([(new_N - 1, n, 1)])
    visited[new_N - 1][n] = True
    while q:
        x, y, d = q.popleft()
        if d > D:
            return
        if new_game[x][y] == 1:
            kill.add((x, y))
            return
        for dx, dy in DIR:
            nx = x + dx
            ny = y + dy
            if 0 <= nx < new_N and 0 <= ny < M and not visited[nx][ny]:
                visited[nx][ny] = True
                q.append((nx, ny, d + 1))
    return


def is_empty():
    for g in new_game:
        if 1 in g:
            return False
    return True


answer = 0

for archers in combinations(range(M), 3):
    new_game = deepcopy(game)
    new_N = N
    tmp = 0
    while not is_empty():
        kill = set()
        for a in archers:
            attack(a)
        for kx, ky in kill:
            new_game[kx][ky] = 0
        tmp += len(kill)
        new_game.pop()
        new_N -= 1
    answer = max(answer, tmp)

print(answer)