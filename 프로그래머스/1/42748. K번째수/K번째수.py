def solution(array, commands):
    answer = []
    
    #commands[0][0]~[0][1]대로 정렬 -> [0][2]위치의 수 answer에 넣기
    for com in commands:
        lis = array[com[0]-1:com[1]]
        lis.sort()
        res = lis[com[2]-1]
        answer.append(res)
    return answer