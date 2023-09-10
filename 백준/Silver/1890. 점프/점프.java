import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int boardSize;
    static int[][] board;
    static long[][] dp;
    static final int[][] DIR = { { 1, 0 }, { 0, 1 } };

    static long doGame() {
        dp[0][0] = 1;

        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                if (board[row][col] == 0)
                    continue;
                if (dp[row][col] != 0) {
                    jump(row, col);
                }
            }
        }

        return dp[boardSize - 1][boardSize - 1];
    }

    static void jump(int row, int col) {
        int mul = board[row][col];
        for (int[] dir : DIR) {
            int nx = row + dir[0] * mul;
            int ny = col + dir[1] * mul;

            if (nx < 0 || nx >= boardSize || ny < 0 || ny >= boardSize)
                continue;

            dp[nx][ny] += dp[row][col];
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        boardSize = Integer.parseInt(br.readLine().trim());

        board = new int[boardSize][boardSize];
        dp = new long[boardSize][boardSize];

        for (int row = 0; row < boardSize; row++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int col = 0; col < boardSize; col++) {
                board[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(doGame());
    }
}