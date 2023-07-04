import sys

sys.setrecursionlimit(int(1e5))
LOG = 21


def read():
    return sys.stdin.readline().rstrip()


def bfs(x, d):
    visited[x] = True
    depth[x] = d
    for n in tree[x]:
        if visited[n]:
            continue
        parent[n][0] = x
        bfs(n, d + 1)


def set_parent():
    bfs(1, 0)
    for k in range(1, LOG):
        for n in range(1, N + 1):
            parent[n][k] = parent[parent[n][k - 1]][k - 1]


def lca(a, b):
    if depth[a] > depth[b]:
        a, b = b, a
    for i in range(LOG - 1, -1, -1):
        if depth[b] - depth[a] >= (1 << i):
            b = parent[b][i]
    if a == b:
        return print(a)
    for i in range(LOG - 1, -1, -1):
        if parent[a][i] != parent[b][i]:
            a = parent[a][i]
            b = parent[b][i]
    return print(parent[a][0])


N = int(read())
tree = [[] for _ in range(N + 1)]
depth = [0] * (N + 1)
visited = [False] * (N + 1)
parent = [[0] * LOG for _ in range(N + 1)]

for _ in range(N - 1):
    v, w = map(int, read().split())
    tree[v].append(w)
    tree[w].append(v)

set_parent()

for _ in range(int(input())):
    lca(*map(int, read().split()))