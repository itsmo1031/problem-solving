def ccw(p1, p2, p3):
    s = p1[0] * p2[1] + p2[0] * p3[1] + p3[0] * p1[1] - (p1[0] * p3[1] + p3[0] * p2[1] + p2[0] * p1[1])
    return 0 if s == 0 else 1 if s > 0 else -1


P = []
for __ in range(3):
    P.append(tuple(map(int, input().split())))

print(ccw(*P))
