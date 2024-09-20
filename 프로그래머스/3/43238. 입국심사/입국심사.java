import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        long lo = 0;
        long hi = (long) 1e13;
        
        while(lo <= hi) {
            long mid = lo + (hi - lo) / 2;
            
            if (isPossible(n, mid, times)) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        
        return lo;
    }
    
    public boolean isPossible(int n, long mid, int[] times) {
        long count = 0;
        
        for (int time : times) {
            count += mid / time;
        }
        
        return count >= n;
    }
}