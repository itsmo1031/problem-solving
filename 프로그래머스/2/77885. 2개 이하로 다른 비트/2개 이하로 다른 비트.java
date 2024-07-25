class Solution {
    public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];
        
        for(int i = 0; i < numbers.length; i++) {
            if (numbers[i] % 2 == 0) {
                answer[i] = numbers[i] + 1;
                continue;
            }
            
            for (long flag = 1;;flag <<= 1) {
                if ((numbers[i] & flag) == 0) {
                    long target = numbers[i] | flag;
                    target ^= flag >> 1;
                    answer[i] = target;
                    break;
                }
            }
        }
        
        return answer;
    }
}