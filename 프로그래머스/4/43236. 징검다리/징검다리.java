import java.util.*;

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;
        Arrays.sort(rocks);
        
        // 최솟값을 mid로 놓고 mid에서 제거 가능한 돌의 개수가 n개가 되는 지점을 구한다
        // upper bound
        int lo = 1;
        int hi = distance;
        
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            
            if (check(distance, mid, rocks) <= n) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        
        return lo - 1;
    }
    
    // 바위 간 최소 거리가 dist가 되게 하려면 몇 개의 바위를 제거해야 하는가?
    public int check(int goal, int dist, int[] rocks) {
        int count = 0;
        
        int current = 0;
        
        for (int i = 0; i < rocks.length; i++) {
            if (rocks[i] - current < dist) {
                count += 1;
                continue;
            }
            current = rocks[i];
        }
        
        if (goal - current < dist) {
            count += 1;
        }
        
        return count;
    }
}