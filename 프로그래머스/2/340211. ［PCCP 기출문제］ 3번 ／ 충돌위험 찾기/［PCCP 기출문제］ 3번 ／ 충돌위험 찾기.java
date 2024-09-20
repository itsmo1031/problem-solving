import java.util.*;

class Solution {
    int[][] ap;
    int[][][] memo;
    
    class Pos {
        int r, c;
        
        public Pos (int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    
    public int solution(int[][] points, int[][] routes) {
        int answer = 0;
        memo = new int[20001][101][101];
        ap = points;
        
        for (int[] route : routes) {
            answer += move(route);
        }
        
        return answer;
    }
    
    public int move(int[] route) {
        int result = 0;
        int time = 0;
        Pos cur = null;
        
        for (int i = 0; i < route.length - 1; i++) {
            int[] from = ap[route[i] - 1];
            int[] to = ap[route[i + 1] - 1];
            cur = new Pos(from[0], from[1]);
            
            while (cur.r != to[0] || cur.c != to[1]) {
                if (++memo[time++][cur.r][cur.c] == 2) {
                    result += 1;
                }

                int rDiff = to[0] - cur.r;
                int cDiff = to[1] - cur.c;

                if (rDiff != 0) {
                    if (rDiff < 0) {
                        cur.r -= 1;
                    } else {
                        cur.r += 1;
                    }
                } else {
                    if (cDiff < 0) {
                        cur.c -= 1;
                    } else {
                        cur.c += 1;
                    }
                }
            }
        }
        
        if (++memo[time][cur.r][cur.c] == 2) {
            result += 1;
        }
        
        return result;
    }
}