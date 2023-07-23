N = int(input())

A = [*map(int, input().split())]
lis = [1] * N

for i in range(1, N):
    for j in range(i):
        if A[i] > A[j]:
            lis[i] = max(lis[i], lis[j] + 1)

index = max(lis)
result = []

print(index)

for i in range(N - 1, -1, -1):
    if lis[i] == index:
        result.append(A[i])
        index -= 1
    if index == 0:
        break


print(*sorted(result))