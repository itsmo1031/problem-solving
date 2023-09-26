import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static int[][] sudoku;
    static List<Pos> cand;
    static StringBuilder sb;
    static boolean found;

    static class Pos {
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        sudoku = new int[9][9];
        cand = new ArrayList<>();

        for (int row = 0; row < 9; row++) {
            char[] data = br.readLine().trim().toCharArray();
            for (int col = 0; col < 9; col++) {
                sudoku[row][col] = data[col] - '0';
                if (sudoku[row][col] == 0)
                    cand.add(new Pos(row, col));
            }
        }
        sb = new StringBuilder();
        doGame(0);

        System.out.print(sb);
    }

    static void doGame(int cnt) {
        if (cnt == cand.size()) {
            for (int[] line : sudoku) {
                for (int data : line) {
                    sb.append(data);
                }
                sb.append("\n");
            }
            found = true;
        }

        if (found)
            return;

        Pos now = cand.get(cnt);

        for (int num = 1; num <= 9; num++) {
            sudoku[now.x][now.y] = num;
            if (isPossible(now))
                doGame(cnt + 1);
            sudoku[now.x][now.y] = 0;
        }
    }

    static boolean isPossible(Pos now) {
        int target = sudoku[now.x][now.y];

        for (int idx = 0; idx < 9; idx++) {
            if (idx != now.x && sudoku[idx][now.y] == target)
                return false;
            if (idx != now.y && sudoku[now.x][idx] == target)
                return false;
        }

        int[][] indexList = getCubeIdx(now);
        for (int row : indexList[0]) {
            for (int col : indexList[1]) {
                if (row == now.x && col == now.y)
                    continue;
                if (sudoku[row][col] == target)
                    return false;
            }
        }

        return true;
    }

    static int[][] getCubeIdx(Pos now) {
        int[][] result = new int[2][3];

        // 행 계산
        if (0 <= now.x && now.x <= 2) {
            result[0] = new int[] { 0, 1, 2 };
        } else if (3 <= now.x && now.x <= 5) {
            result[0] = new int[] { 3, 4, 5 };
        } else {
            result[0] = new int[] { 6, 7, 8 };
        }

        if (0 <= now.y && now.y <= 2) {
            result[1] = new int[] { 0, 1, 2 };
        } else if (3 <= now.y && now.y <= 5) {
            result[1] = new int[] { 3, 4, 5 };
        } else {
            result[1] = new int[] { 6, 7, 8 };
        }

        return result;
    }
}
