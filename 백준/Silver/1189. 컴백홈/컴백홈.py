def dfs(pos, c, v):
    global answer
    x, y = pos
    if (x, y) == (0, C - 1) and K == c:
        answer += 1
    v[x][y] = True
    for dx, dy in D:
        nx = x + dx
        ny = y + dy
        if 0 <= nx < R and 0 <= ny < C:
            if road[nx][ny] == '.' and not v[nx][ny]:
                dfs((nx, ny), c + 1, v)
                v[nx][ny] = False


D = [(0, 1), (1, 0), (-1, 0), (0, -1)]
R, C, K = map(int, input().split())
road = [[*input()] for _ in range(R)]
visited = [[False] * C for _ in range(R)]
answer = 0

dfs((R - 1, 0), 1, visited)
print(answer)