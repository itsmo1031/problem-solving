class Solution {
    static int[] answer;
    static int[][] data;
    
    public int[] solution(int[][] arr) {
        answer = new int[2];
        data = arr;
        
        compress(0, 0, arr.length, arr.length);
        
        return answer;
    }
    
    static void compress(int sr, int sc, int er, int ec) {
        int flag = data[sr][sc];

        for (int r = sr; r < er; r++) {
            for (int c = sc; c < ec; c++) {
                if (flag != data[r][c]) {
                    int hr = (sr + er) / 2;
                    int hc = (sc + ec) / 2;
                    compress(sr, sc, hr, hc);
                    compress(sr, hc, hr, ec);
                    compress(hr, sc, er, hc);
                    compress(hr, hc, er, ec);
                    return;
                }
            }
        }

        answer[flag] += 1;
    }
}