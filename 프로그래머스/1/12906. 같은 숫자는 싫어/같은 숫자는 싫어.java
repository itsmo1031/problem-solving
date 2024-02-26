import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        Deque<Integer> q = new ArrayDeque<>();
        
        q.offerLast(arr[0]);
        
        for (int i = 1; i < arr.length; i++) {
            int now = q.peekLast();
            if (now != arr[i]) {
                q.offerLast(arr[i]);
            }
        }
        
        int[] result = new int[q.size()];
        
        for (int i = 0; i < result.length; i++) {
            result[i] = q.pollFirst();
        }
        
        return result;
    }
}