def rotate(g, d):
    if d == 1:
        gear[g] = [gear[g][-1]] + gear[g][:-1]
    if d == -1:
        gear[g] = gear[g][1:] + [gear[g][0]]


def get_cand(g, d):
    res = [(g, d)]
    tg, td = g, d
    while tg < 4:
        tg += 1
        td = -td
        if gear[tg - 1][2] != gear[tg][6]:
            res.append((tg, td))
        else:
            break
    tg, td = g, d
    while 1 < tg:
        tg -= 1
        td = -td
        if gear[tg + 1][6] != gear[tg][2]:
            res.append((tg, td))
        else:
            break
    return res


def get_score():
    s = 0
    for i in range(1, 5):
        if gear[i][0] == 1:
            s += 2 ** (i - 1)
    return s


gear = [[]]

for _ in range(4):
    gear.append([*map(int, input())])

for _ in range(int(input())):
    rot = [False] * 5
    num, direction = map(int, input().split())
    cand = get_cand(num, direction)
    for cg, cd in cand:
        rotate(cg, cd)

print(get_score())
