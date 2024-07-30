class Solution {
    public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];
        
        for(int i = 0; i < numbers.length; i++) {
            if (numbers[i] % 2 == 0) {
                answer[i] = numbers[i] + 1;
            }
            
            // 홀수
            // 1. 오른쪽부터 0의 위치를 찾는다
            for (long j = 1;; j <<= 1) {
                if ((numbers[i] & j) == 0) {
                    // 2. 0의 위치에 1을 넣어주고
                    long target = numbers[i] | j;
                    // 3. 해당 위치 오른쪽에 0을 넣어준다.
                    target = target ^ (j >> 1);
                    
                    answer[i] = target;
                    break;
                }
            }
        }
        
        return answer;
    }
}