from collections import deque
from itertools import permutations

N = int(input())
inning = []
did = set()


def play_game(o):
    def hit(n, b):
        if n == 4:
            s = b.count('1') + 1
            b = '000'
        elif n == 3:
            s = b.count('1')
            b = '001'
        elif n == 2:
            s = b[1:].count('1')
            b = '01' + b[0]
        elif n == 1:
            s = int(b[2])
            b = '1' + b[:2]
        else:
            s = -1
        return s, b

    player = deque(o)
    player.insert(3, 0)
    score = 0

    for i in inning:
        base = '000'
        out = 0
        while out < 3:
            now = player.popleft()
            r, base = hit(i[now], base)
            if r == -1:
                out += 1
            else:
                score += r
            player.append(now)

    return score


for _ in range(N):
    inning.append([*map(int, input().split())])

result = 0

for p in permutations(range(1, 9)):
    result = max(result, play_game(p))

print(result)