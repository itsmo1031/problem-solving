A = input()
N = len(A)
B = input()
M = len(B)
lcs = [[0] * (M + 1) for _ in range(N + 1)]

for i in range(1, N + 1):
    for j in range(1, M + 1):
        if A[i - 1] == B[j - 1]:
            lcs[i][j] = lcs[i - 1][j - 1] + 1
        else:
            lcs[i][j] = max(lcs[i - 1][j], lcs[i][j - 1])

x = N
y = M
answer = []

while x > 0 and y > 0:
    if lcs[x][y] == lcs[x - 1][y]:
        x -= 1
    elif lcs[x][y] == lcs[x][y - 1]:
        y -= 1
    else:
        answer.append(A[x - 1])
        x -= 1
        y -= 1

answer.reverse()

print(lcs[-1][-1])
print(''.join(answer))