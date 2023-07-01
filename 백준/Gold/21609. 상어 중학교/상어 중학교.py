from collections import deque

D = [(0, 1), (1, 0), (0, -1), (-1, 0)]


def get_block_group(pos, color):
    q = deque([pos])
    res = [pos]
    rb = []
    while q:
        x, y = q.popleft()
        for dx, dy in D:
            nx = x + dx
            ny = y + dy
            if 0 <= nx < N and 0 <= ny < N:
                if not visited[nx][ny] and game[nx][ny] in [color, 0]:
                    visited[nx][ny] = True
                    res.append((nx, ny))
                    q.append((nx, ny))
                    if game[nx][ny] == 0:
                        rb.append((nx, ny))
    for x, y in rb:
        visited[x][y] = False

    return res if len(res) >= 2 else []


def get_biggest_block_group():
    def count_rainbow(arr):
        cnt = 0
        for x, y in arr:
            if game[x][y] == 0:
                cnt += 1
        return cnt

    res = []
    res_rb = 0
    for i in range(N):
        for j in range(N):
            if game[i][j] > 0 and not visited[i][j]:
                visited[i][j] = True
                tmp = get_block_group((i, j), game[i][j])
                if len(tmp) > len(res):
                    res = tmp
                    res_rb = count_rainbow(res)
                elif len(tmp) == len(res):
                    tmp_rb = count_rainbow(tmp)
                    if tmp_rb >= res_rb:
                        res = tmp
                        res_rb = tmp_rb
    return res


def destroy(block):
    for x, y in block:
        game[x][y] = -2
    return len(block) ** 2


def drop():
    for i in range(N - 2, -1, -1):
        for j in range(N):
            if game[i][j] > -1:
                r = i
                while 0 <= r + 1 < N and game[r + 1][j] == -2:
                    game[r][j], game[r + 1][j] = game[r + 1][j], game[r][j]
                    r += 1


def rotate(board):
    return [*map(list, zip(*board))][::-1]


N, M = map(int, input().split())
game = [[*map(int, input().split())] for _ in range(N)]
score = 0
while True:
    visited = [[False] * N for _ in range(N)]
    target = get_biggest_block_group()
    if len(target) == 0:
        break
    score += destroy(target)
    drop()
    game = rotate(game)
    drop()

print(score)