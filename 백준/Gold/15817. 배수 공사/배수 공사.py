N, X = map(int, input().split())

pipe = [1] + [0]*(X)

for _ in range(N):
    L, C = map(int, input().split())
    for i in range(X, -1, -1):
        for j in range(1, C+1):
            if L*j > i:
                break
            pipe[i] += pipe[i-L*j]

print(pipe[-1])