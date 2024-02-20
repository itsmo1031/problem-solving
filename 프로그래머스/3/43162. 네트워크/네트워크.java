import java.util.*;

class Solution {
    boolean[] visited;
    int computerCnt;
    final int CONNECTED = 1;
    
    public int solution(int n, int[][] computers) {
        visited = new boolean[n];
        int answer = 0;
        computerCnt = n;
        
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                bfs(i, computers);
                answer += 1;
            }
        }
        
        return answer;
    }
    
    void bfs(int start, int[][] computers) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(start);
        
        while (!q.isEmpty()) {
            int now = q.poll();
            int[] info = computers[now];
            
            for (int i = 0; i < computerCnt; i++) {
                if (info[i] != CONNECTED || visited[i]) {
                    continue;
                }
                
                visited[i] = true;
                q.offer(i);
            }
        }
    }
}