class Solution {
    static final int[][] DIR = {{1, 0}, {0, 1}, {-1, -1}};
    
    public int[] solution(int n) {
        return makeSnail(n);
    }
    
    static int[] makeSnail(int n) {
        int[][] board = new int[n][n];
        int num = 1;
        int dirIdx = 0;
        int stack = 0;
        
        int r = 0, c = 0;
        
        while (stack < 2) {
            board[r][c] = num++;
            
            while (stack < 2) {
                int nr = r + DIR[dirIdx][0];
                int nc = c + DIR[dirIdx][1];

                if (nr >= 0 && nr < n && nc >= 0 && nc < n && board[nr][nc] == 0) {
                    r = nr;
                    c = nc;
                    stack = 0;
                    break;
                }
                
                dirIdx = (dirIdx + 1) % 3;
                stack++;
            }
        }
        
        return flatSnail(board);
    }
    
    static int[] flatSnail(int[][] board) {
        int size = 0;
        
        for(int i = 1; i <= board.length; i++) {
            size += i;
        }
        
        int[] flat = new int[size];
        int flatIdx = 0;
        
        for(int r = 0; r < board.length; r++) {
            for(int c = 0; c < board.length; c++) {
                if (board[r][c] == 0) {
                    break;
                }
                flat[flatIdx++] = board[r][c];
            }
        }
        
        return flat;
    }
}