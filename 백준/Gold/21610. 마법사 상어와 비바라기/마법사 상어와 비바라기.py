cmd = [(0, -1), (-1, -1), (-1, 0), (-1, 1), (0, 1), (1, 1), (1, 0), (1, -1)]

N, M = map(int, input().split())
cloud = [(N - 1, 0), (N - 1, 1), (N - 2, 0), (N - 2, 1)]
basket = [[*map(int, input().split())] for _ in range(N)]


def move(d, s):
    for i in range(len(cloud)):
        nx = (cloud[i][0] + cmd[d - 1][0] * s) % N
        ny = (cloud[i][1] + cmd[d - 1][1] * s) % N
        cloud[i] = (nx, ny)


def rain():
    for x, y in cloud:
        basket[x][y] += 1


def duplicate():
    for x, y in cloud:
        for dx, dy in cmd[1::2]:
            nx = x + dx
            ny = y + dy
            if 0 <= nx < N and 0 <= ny < N:
                if basket[nx][ny] > 0:
                    basket[x][y] += 1


def make_cloud():
    global cloud
    new_cloud = []
    for i in range(N):
        for j in range(N):
            if basket[i][j] > 1 and (i, j) not in cloud:
                new_cloud.append((i, j))
                basket[i][j] -= 2
    cloud = new_cloud


for _ in range(M):
    move(*map(int, input().split()))
    rain()
    duplicate()
    make_cloud()

answer = sum(map(sum, basket))

print(answer)