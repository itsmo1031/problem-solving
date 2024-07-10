

class Solution {
    // D, L, R, U 순
    static final int[][] DIR = {{1, 0}, {0, -1}, {0, 1}, {-1, 0}};
    static final char[] dict = {'d', 'l', 'r', 'u'};
    static char[] answer;
    static boolean isPossible;
    static int mapRow, mapCol;
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        answer = new char[k];
        
        mapRow = n;
        mapCol = m;
        
        if ((k - calcDist(x, y, r, c)) % 2 != 0) {
            return "impossible";
        }
        
        escape(0, x - 1, y - 1, r - 1 , c - 1);
        
        return isPossible ? new String(answer) : "impossible";
    }
    
    static int calcDist(int x, int y, int r, int c) {
        return Math.abs(r - x) + Math.abs(c - y);
    }
    
    static void escape(int step, int curR, int curC, int goalR, int goalC) {
        if (step == answer.length) {
            if (curR == goalR && curC == goalC) {
                isPossible = true;
            }
            return;
        }
        
        if (answer.length - step < calcDist(goalR, goalC, curR, curC)) {
            return;
        }
        
        if (isPossible) {
            return;
        }
        
        for (int i = 0; i < 4 ; i++) {
            int nr = curR + DIR[i][0];
            int nc = curC + DIR[i][1];
            
            if (nr < 0 || nr >= mapRow || nc < 0 || nc >= mapCol) {
                continue;
            }
            
            answer[step] = dict[i];
            escape(step + 1, nr, nc, goalR, goalC);
            
            if (isPossible) {
                return;
            }
        }
    }
}
