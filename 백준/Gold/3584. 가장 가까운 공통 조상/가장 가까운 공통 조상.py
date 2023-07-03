import sys

input = sys.stdin.readline
sys.setrecursionlimit(int(1e5))
LOG = 21


def dfs(x, d):
    visited[x] = True
    depth[x] = d
    for n in graph[x]:
        if visited[n]:
            continue
        parent[n][0] = x
        dfs(n, d + 1)


def set_parent(r):
    dfs(r, 0)
    for k in range(1, LOG):
        for n in range(1, N + 1):
            parent[n][k] = parent[parent[n][k - 1]][k - 1]


def lca(n, m):
    if depth[n] > depth[m]:
        n, m = m, n
    for i in range(LOG - 1, -1, -1):
        if depth[m] - depth[n] >= (1 << i):
            m = parent[m][i]
    if n == m:
        return n
    for i in range(LOG - 1, -1, -1):
        if parent[n][i] != parent[m][i]:
            n = parent[n][i]
            m = parent[m][i]
    return parent[n][0]


for _ in range(int(input())):
    N = int(input())
    graph = [[] for _ in range(N + 1)]
    depth = [0] * (N + 1)
    visited = [False] * (N + 1)
    parent = [[0] * LOG for _ in range(N + 1)]

    root = 0

    for _ in range(N - 1):
        a, b = map(int, input().split())
        if root == 0 or root == b:
            root = a
        graph[a].append(b)
        graph[b].append(a)

    set_parent(root)

    target = map(int, input().split())

    print(lca(*target))