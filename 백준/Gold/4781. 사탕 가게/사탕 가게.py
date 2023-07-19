while True:
    n, m = map(float, input().split())
    n = int(n)
    m = int(m * 100 + 0.5)
    money = [0] * (m + 1)
    if n == m == 0:
        break
    for _ in range(n):
        c, p = map(float, input().split())
        c = int(c)
        p = int(p * 100 + 0.5)
        for i in range(m + 1):
            if p <= i:
                money[i] = max(money[i], money[i - p] + c)

    print(money[-1])