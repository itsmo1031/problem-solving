def solution(arr):
    answer = []
    
    # 배열을 순회하면서 각 요소와 비교
    for i in arr: # 원소
        # 현재 answer가 비어있거나, answer의 마지막 값과 현재 arr의 원소가 같을때
        if len(answer) == 0 or answer[-1] != i:
            # answer에 집어넣기
            answer.append(i)
    
    return answer