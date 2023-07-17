import math


def move():
    cand = []
    for i in range(N):
        for j in range(N):
            while fireball[i][j]:
                cm, cs, cd = fireball[i][j].pop()
                nx = (i + D[cd][0] * cs) % N
                ny = (j + D[cd][1] * cs) % N
                cand.append((nx, ny, cm, cd, cs))
    for nx, ny, cm, cs, cd in cand:
        fireball[nx][ny].append((cm, cd, cs))


def is_even(num):
    return num % 2 == 0


def remake():
    for i in range(N):
        for j in range(N):
            if len(fireball[i][j]) > 1:
                fl = len(fireball[i][j])
                nm, ns = 0, 0
                nd = [0, 2, 4, 6]
                di = -1
                while fireball[i][j]:
                    cm, cs, cd = fireball[i][j].pop()
                    if di == -1:
                        di = is_even(cd)
                    elif di != is_even(cd):
                        nd = [1, 3, 5, 7]
                    nm += cm
                    ns += cs
                nm = math.floor(nm / 5)
                ns = math.floor(ns / fl)
                if nm != 0:
                    for new_d in nd:
                        fireball[i][j].append((nm, ns, new_d))


def calc():
    result = 0
    for i in range(N):
        for j in range(N):
            if fireball[i][j]:
                for fire in fireball[i][j]:
                    result += fire[0]
    return result


D = [(-1, 0), (-1, 1), (0, 1), (1, 1), (1, 0), (1, -1), (0, -1), (-1, -1)]
N, M, K = map(int, input().split())
fireball = [[[] for _ in range(N)] for _ in range(N)]

for _ in range(M):
    r, c, m, s, d = map(int, input().split())
    fireball[r - 1][c - 1].append((m, s, d))

for _ in range(K):
    move()
    remake()

print(calc())