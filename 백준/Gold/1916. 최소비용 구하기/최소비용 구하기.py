import heapq


INF = int(1e9)

N = int(input())
M = int(input())


def dijkstra(sp, ep):
    dist = [INF] * (N + 1)
    dist[sp] = 0

    q = []
    heapq.heappush(q, (0, sp))

    while q:
        cost, now = heapq.heappop(q)
        if cost > dist[now]:
            continue
        for np, nd in bus[now]:
            nc = cost + nd
            if nc < dist[np]:
                dist[np] = nc
                heapq.heappush(q, (nc, np))

    return dist[ep]


bus = [[] for _ in range(N + 1)]

for _ in range(M):
    S, E, C = map(int, input().split())
    bus[S].append((E, C))

start, end = map(int, input().split())

print(dijkstra(start, end))