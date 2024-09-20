class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int lo = 1;
        int hi = 100_000;
        
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            
            if (solve(mid, diffs, times) <= limit) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        
        return lo;
    }
    
    public long solve(int level, int[] diffs, int[] times) {
        long result = 0;
        
        for (int i = 0; i < diffs.length; i++) {
            if (diffs[i] <= level) {
                result += times[i];
            } else {
                result += ((times[i - 1] + times[i]) * (diffs[i] - level)) + times[i];
            }
        }
        
        return result;
    }
}