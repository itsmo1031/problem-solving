import java.util.*;
import java.io.*;

class Solution {
    final int INF = (int) 1e9;
    
    public int solution(int x, int y, int n) {
        int[] dp = new int[1_000_001];
        
        Arrays.fill(dp, INF);
        dp[x] = 0;
        
        for(int idx = x; idx <= y; idx++) {
            if (idx + n <= y) {
                dp[idx + n] = Math.min(dp[idx]+1, dp[idx + n]);
            }
            for(int mul : new int[]{2, 3}) {
                if (idx * mul <= y) {
                    dp[idx * mul] = Math.min(dp[idx] + 1, dp[idx * mul]);
                }
            }
        }
        
        int answer = dp[y];
        return answer == INF ? -1 : answer;
    }
}