from collections import deque

N, K = map(int, input().split())


def bfs():
    q = deque([(K, 0)])
    while q:
        now, cost = q.popleft()
        if now == N:
            return cost
        if now % 2 == 0 and now // 2 not in visited:
            visited.add(now // 2)
            q.append((now // 2, cost))
        if now + 1 not in visited:
            visited.add(now + 1)
            q.append((now + 1, cost + 1))
        if now - 1 not in visited:
            visited.add(now - 1)
            q.append((now - 1, cost + 1))


visited = {K}

print(bfs())