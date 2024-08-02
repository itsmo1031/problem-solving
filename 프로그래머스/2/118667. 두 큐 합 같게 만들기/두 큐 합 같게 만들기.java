import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        Queue<Integer> q1 = new ArrayDeque<>();
        Queue<Integer> q2 = new ArrayDeque<>();
        long q1Sum = 0;
        long q2Sum = 0;
        
        for (int num : queue1) {
            q1.offer(num);
            q1Sum += num;
        }
        
        for (int num : queue2) {
            q2.offer(num);
            q2Sum += num;
        }
        
        int size = q1.size() + q2.size();
        
        int cnt = 0;
        while (cnt <= size * 2) {
            if (q1Sum < q2Sum) {
                int num = q2.poll();
                q1Sum += num;
                q2Sum -= num;
                q1.offer(num);
                cnt += 1;
            } else if (q1Sum > q2Sum) {
                int num = q1.poll();
                q1Sum -= num;
                q2Sum += num;
                q2.offer(num);
                cnt += 1;
            } else {
                break;
            }
        }
        
        
        return cnt > size * 2 ? -1 : cnt;
    }
}