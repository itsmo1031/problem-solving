import java.util.*;

// 각 석유 정보 memoization?
// 열 인덱스별로 석유 객체 추가해서 BFS 한번으로 최적화
// 열 번호 HashMap으로 추가해주기?
class Solution {
    static class Oil {
        int size;
        
        public Oil(int size) {
            this.size = size;
        }
        
        public String toString() {
            return "size: " + size;
        }
    }
    
    static class Pos {
        int r, c;
        
        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    
    static Map<Integer, Integer> oilMap;
    static int[][] land;
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static int landRow;
    static int landCol;
    
    public int solution(int[][] land) {
        this.land = land;
        landRow = land.length;
        landCol = land[0].length;
        
        oilMap = new HashMap<>();
        
        for (int r = 0; r < landRow; r++) {
            for (int c = 0; c < landCol; c++) {
                if (land[r][c] == 1) {
                    drill(new Pos(r, c));
                }
            }
        }
        
        return oilMap.values().stream().mapToInt(x -> x).max().getAsInt();
    }
    
    static void drill(Pos pos) {
        int oilSize = 1;
        Set<Integer> oilCol = new HashSet<>();
        
        oilCol.add(pos.c);
        
        land[pos.r][pos.c] = -1;
        
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(pos);
        
        while (!q.isEmpty()) {
            Pos now = q.poll();
            
            for (int[] d : dir) {
                int nr = now.r + d[0];
                int nc = now.c + d[1];
                
                if (nr < 0 || nr >= landRow || nc < 0 || nc >= landCol)
                    continue;
                
                if (land[nr][nc] == 1) {
                    land[nr][nc] = -1;
                    oilSize += 1;
                    oilCol.add(nc);
                    
                    q.offer(new Pos(nr, nc));
                }
            }
        }
        
        for (int col : oilCol) {
            oilMap.put(col, oilMap.getOrDefault(col, 0) + oilSize);
        }
    }
}