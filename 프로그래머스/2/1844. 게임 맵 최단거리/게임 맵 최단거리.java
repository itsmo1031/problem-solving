import java.util.*;

class Solution {
    
    static final int[][] DIR = {{0,1}, {1,0}, {0,-1}, {-1,0}};
    static final int WALL = 0;
    
    class Pos {
        int r, c, cost;
        
        public Pos (int r, int c, int cost) {
            this.r = r;
            this.c = c;
            this.cost = cost;
        }
    }
    
    public int solution(int[][] maps) {
        int answer = bfs(maps);
        return answer;
    }
    
    public int bfs(int[][] maps) {
        Pos startPos = new Pos(0, 0, 1);
        int mapRow = maps.length;
        int mapCol = maps[0].length;
        
        boolean[][] visited = new boolean[mapRow][mapCol];
        visited[0][0] = true;
        
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(startPos);
        
        while(!q.isEmpty()) {
            Pos now = q.poll();
            
            if(now.r == maps.length-1 && now.c == maps[0].length-1) {
                return now.cost;
            }
            
            for(int[] dir: DIR) {
                int nr = now.r + dir[0];
                int nc = now.c + dir[1];
                
                if(nr<0 || nr>=mapRow || nc<0 || nc>=mapCol || visited[nr][nc]) {
                    continue;
                }
                
                if (maps[nr][nc] != WALL) {
                    visited[nr][nc] = true;
                    q.offer(new Pos(nr, nc, now.cost+1));
                }
            }
        }
        
        return -1;
    }
}