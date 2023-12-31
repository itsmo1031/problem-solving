import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int boardRow, boardCol;
    static int[][] board;
    static boolean[] check;
    static int result = 0;
    static final int[][] DIR = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        boardRow = Integer.parseInt(st.nextToken());
        boardCol = Integer.parseInt(st.nextToken());

        board = new int[boardRow][boardCol];
        check = new boolean[26];

        for (int row = 0; row < boardRow; row++) {
            String line = br.readLine().trim();
            for (int col = 0; col < boardCol; col++) {
                board[row][col] = line.charAt(col) - 'A';
            }
        }
        place(0, 0, 1);
        System.out.println(result);

    }

    static void place(int x, int y, int cnt) {
        check[board[x][y]] = true;

        if (cnt > result)
            result = cnt;

        for (int[] dir : DIR) {
            int nx = x + dir[0];
            int ny = y + dir[1];

            if (nx < 0 || nx >= boardRow || ny < 0 || ny >= boardCol)
                continue;

            if (!check[board[nx][ny]]) {
                place(nx, ny, cnt + 1);
                check[board[nx][ny]] = false;
            }
        }
    }
}