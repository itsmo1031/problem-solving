import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static class Purifier {
        int upperIdx;
        int lowerIdx;

        public Purifier(int upperIdx, int lowerIdx) {
            this.upperIdx = upperIdx;
            this.lowerIdx = lowerIdx;
        }

        void purify() {
            purify(upperIdx, 0);
            purify(lowerIdx, 2);
        }

        void purify(int startIdx, int startDir) {
            int dir = startDir;
            int r = startIdx;
            int c = 0;

            if (startDir == 0) {
                home[startIdx - 1][0] = EMPTY;
                r -= 1;
            } else {
                home[startIdx + 1][0] = EMPTY;
                r += 1;
            }

            while (true) {
                int nr = r + DIR[dir][0];
                int nc = c + DIR[dir][1];

                if (isPossible(startIdx, nr, nc)) {
                    dir = (dir + (startIdx == upperIdx ? 1 : -1) + 4) % 4;
                    continue;
                }

                if (nr == startIdx && nc == 0) {
                    break;
                }

                home[r][c] = home[nr][nc];
                home[nr][nc] = 0;

                r = nr;
                c = nc;
            }
        }

        boolean isPossible(int startIdx, int nr, int nc) {
            if (startIdx == upperIdx)
                return nr < 0 || nr > startIdx || nc < 0 || nc >= homeCol;
            else
                return nr < startIdx || nr >= homeRow || nc < 0 || nc >= homeCol;
        }
    }

    static int[][] home;
    static int homeRow, homeCol;
    static final int EMPTY = 0;
    static Purifier purifier;
    static final int[][] DIR = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        homeRow = Integer.parseInt(st.nextToken());
        homeCol = Integer.parseInt(st.nextToken());

        int time = Integer.parseInt(st.nextToken());

        home = new int[homeRow][homeCol];

        for (int row = 0; row < homeRow; row++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int col = 0; col < homeCol; col++) {
                home[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        purifier = initPurifier();
        int currentTime = 0;

        while (currentTime++ < time) {
            spreadAll();

            purifier.purify();
        }

        System.out.println(countDust());
    }

    static int countDust() {
        int count = 2;

        for (int row = 0; row < homeRow; row++) {
            for (int col = 0; col < homeCol; col++) {
                count += home[row][col];
            }
        }

        return count;
    }

    static Purifier initPurifier() {
        int upperIdx = 0;
        int lowerIdx = 0;

        for (int row = 2; row < homeRow - 2; row++) {
            if (home[row][0] == -1) {
                upperIdx = row;
                lowerIdx = row + 1;
                break;
            }
        }

        return new Purifier(upperIdx, lowerIdx);
    }

    static void spreadAll() {
        int[][] newHome = new int[homeRow][homeCol];

        newHome[purifier.upperIdx][0] = -1;
        newHome[purifier.lowerIdx][0] = -1;

        for (int row = 0; row < homeRow; row++) {
            for (int col = 0; col < homeCol; col++) {
                if (home[row][col] > EMPTY) {
                    spread(newHome, row, col);
                }
            }
        }

        home = newHome;
    }

    static void spread(int[][] newHome, int row, int col) {
        int amount = home[row][col] / 5;
        int cnt = 0;

        for (int[] dir : DIR) {
            int nr = row + dir[0];
            int nc = col + dir[1];

            if (isPossible(nr, nc)) {
                newHome[nr][nc] += amount;
                cnt += 1;
            }
        }

        newHome[row][col] += home[row][col] - (amount * cnt);
    }

    static boolean isPossible(int row, int col) {
        if (row < 0 || row >= homeRow || col < 0 || col >= homeCol)
            return false;
        if (col == 0 && (row == purifier.upperIdx || row == purifier.lowerIdx))
            return false;
        return true;
    }
}
