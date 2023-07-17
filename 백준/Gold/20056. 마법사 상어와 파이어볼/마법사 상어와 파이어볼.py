# 마법사 상어와 파이어볼 - 골드 4
import math


# 파이어볼 이동 메소드 정의
def move():
    cand = []  # 한꺼번에 이동하기 위해 cand 배열 선언
    for i in range(N):
        for j in range(N):
            # 현재 위치에 파이어볼이 있다면 이동
            while fireball[i][j]:
                cm, cs, cd = fireball[i][j].pop()  # pop으로 기존 파이어볼 제거
                nx = (i + D[cd][0] * cs) % N
                ny = (j + D[cd][1] * cs) % N
                cand.append((nx, ny, cm, cs, cd))
    # 모든 후보들을 fireball에 추가
    for nx, ny, cm, cs, cd in cand:
        fireball[nx][ny].append((cm, cs, cd))


def is_even(num):
    return num % 2 == 0


# 파이어볼 분열 메소드
def remake():
    for i in range(N):
        for j in range(N):
            # 파이어볼이 2개 이상일 때 동작
            if len(fireball[i][j]) > 1:
                fl = len(fireball[i][j])
                nm, ns = 0, 0
                nd = [0, 2, 4, 6]
                di = -1  # 분열된 파이어볼의 방향을 설정하기 위한 트리거 변수
                while fireball[i][j]:
                    cm, cs, cd = fireball[i][j].pop()  # pop을 이용하여 현재 파이어볼 제거
                    if di == -1:
                        # 첫 변수라면 is_even 메소드 실행(방향이 짝수인지 홀수인지)
                        di = is_even(cd)
                    elif di != is_even(cd):
                        # 방향이 기존과 일치하지 않으면 방향 변경
                        nd = [1, 3, 5, 7]
                    nm += cm  # 모든 질량 더하기
                    ns += cs  # 모든 속력 더하기
                nm = math.floor(nm / 5)  # 더해진 질량을 5로 나누어 floor
                ns = math.floor(ns / fl)  # 더해진 속력을 현재 파이어볼 개수만큼 나누기
                # 질량이 0이면 사라지고, 0이 아니라면 각 방향에 대해서 새로운 파이어볼 생성
                if nm != 0:
                    for new_d in nd:
                        fireball[i][j].append((nm, ns, new_d))


# 파이어볼 총 질량 계산
def calc():
    result = 0
    for i in range(N):
        for j in range(N):
            # 현재 위치에 파이어볼이 있다면 질량 더하기
            if fireball[i][j]:
                for fire in fireball[i][j]:
                    result += fire[0]
    return result


D = [(-1, 0), (-1, 1), (0, 1), (1, 1), (1, 0), (1, -1), (0, -1), (-1, -1)]
N, M, K = map(int, input().split())
fireball = [[[] for _ in range(N)] for _ in range(N)]

for _ in range(M):
    r, c, m, s, d = map(int, input().split())
    fireball[r - 1][c - 1].append((m, s, d))

for _ in range(K):
    move()
    remake()

print(calc())
