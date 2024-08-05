import java.util.*;

class Solution {
    static class Info {
        int level;
        int cnt;
        int step;
        
        public Info (int level, int cnt, int step) {
            this.level = level;
            this.cnt = cnt;
            this.step = step;
        }
    }
    
    public int solution(int storey) {
        return bfs(storey);
    }
    
    static int bfs(int storey) {
        Queue<Info> q = new PriorityQueue<>((i1, i2) -> Integer.compare(i1.cnt, i2.cnt));
        q.offer(new Info(storey, 0, 0));
        
        while(!q.isEmpty()) {
            Info now = q.poll();
            
            if (now.level == 0) {
                return now.cnt;
            }
            
            int div = (int) Math.pow(10, (now.step + 1));
            int mod = now.level % div;
            
            int nl = now.level - mod;
            int nc = now.cnt + (mod / (div / 10));
            int ns = now.step + 1;
            q.offer(new Info(nl, nc, ns));
            
            nl = now.level + (div - mod);
            nc = now.cnt + ((div - mod) / (div / 10));
            q.offer(new Info(nl, nc, ns));
        }
        
        return 0;
    }
}