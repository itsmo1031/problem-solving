def set_seat(std):
    best = 0
    cand = []
    for i in range(N):
        for j in range(N):
            if not desk[i][j]:
                cnt, empty = get_adj(i, j, std)
                if cnt > best:
                    best = cnt
                    cand = [(i, j, empty)]
                elif cnt == best:
                    cand.append((i, j, empty))
    cand.sort(key=lambda x: (-x[2], x[0], x[1]))
    desk[cand[0][0]][cand[0][1]] = std


def get_adj(x, y, std):
    cnt = 0
    empty = 0
    for dx, dy in D:
        nx = x + dx
        ny = y + dy
        if 0 <= nx < N and 0 <= ny < N:
            if desk[nx][ny] in likes[std]:
                cnt += 1
            if desk[nx][ny] == 0:
                empty += 1
    return cnt, empty


def get_happiness():
    def calc(x, y):
        cnt = 0
        for dx, dy in D:
            nx = x + dx
            ny = y + dy
            if 0 <= nx < N and 0 <= ny < N:
                if desk[nx][ny] in likes[desk[x][y]]:
                    cnt += 1
        return 0 if cnt == 0 else 10 ** (cnt - 1)

    res = 0

    for i in range(N):
        for j in range(N):
            res += calc(i, j)

    return res


N = int(input())
desk = [[0] * N for _ in range(N)]
likes = [[] for _ in range(N ** 2 + 1)]
D = [(0, 1), (1, 0), (-1, 0), (0, -1)]
for k in range(N ** 2):
    student, *like, = map(int, input().split())
    likes[student].extend(like)
    if k == 0:
        desk[1][1] = student
    else:
        set_seat(student)

print(get_happiness())