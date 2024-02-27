import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        Deque<Integer> days = process(progresses, speeds);
        
        List<Integer> result = new ArrayList<>();
        
        int max = days.poll();
        int concurrent = 1;
        
        while (!days.isEmpty()) {
            int now = days.poll();
            if (max < now) {
                max = now;
                result.add(concurrent);
                concurrent = 0;
            }
            concurrent += 1;
        }
        
        if (concurrent > 0) {
            result.add(concurrent);
        }
        
        int[] res = result.stream()
                          .mapToInt(Integer::intValue)
                          .toArray();
        
        return res;
    }
    
    Deque<Integer> process(int[] progresses, int[] speeds) {
        Deque<Integer> q = new ArrayDeque<>();
        
        for (int i = 0; i < progresses.length; i++) {
            double left = (100.0 - progresses[i]) / speeds[i];
            q.offer((int) Math.ceil(left));
        }
        
        return q;
    }
}