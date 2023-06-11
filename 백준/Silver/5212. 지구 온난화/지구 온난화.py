D = [(0, 1), (0, -1), (1, 0), (-1, 0)]
R, C = map(int, input().split())
earth = [['.'] * (C + 2) for __ in range(R + 2)]
land = set()


def check(pos):
    x, y = pos
    sea = 0
    for dx, dy in D:
        nx = x + dx
        ny = y + dy
        if earth[nx][ny] == '.':
            sea += 1
    return False if sea > 2 else True


def display(graph):
    sbx = sorted(graph)
    min_x = sbx[0][0]
    max_x = sbx[-1][0]
    sby = sorted(graph, key=lambda x: x[1])
    min_y = sby[0][1]
    max_y = sby[-1][1]
    adjusted = [*map(lambda x: (x[0] - min_x, x[1] - min_y), graph)]
    res = [['.'] * (max_y - min_y + 1) for __ in range(max_x - min_x + 1)]
    for a, b in adjusted:
        res[a][b] = 'X'
    for r in res:
        print(''.join(r))


for i in range(1, R + 1):
    data = [*input()]
    for j in range(1, C + 1):
        if data[j - 1] == 'X':
            land.add((i, j))
        earth[i][j] = data[j - 1]

display([*filter(check, land)])
