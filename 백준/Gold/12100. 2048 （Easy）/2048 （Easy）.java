import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int boardSize;
    static int[][] board;
    static int maxNum;
    static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;
    static final int MAX_CNT = 5;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        boardSize = Integer.parseInt(br.readLine().trim());

        board = new int[boardSize][boardSize];

        for (int row = 0; row < boardSize; row++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int col = 0; col < boardSize; col++) {
                board[row][col] = Integer.parseInt(st.nextToken());
            }
        }
        doGame(board, 0);

        System.out.println(maxNum);
    }

    static void doGame(int[][] newBoard, int cnt) {
        if (cnt == MAX_CNT) {
            maxNum = Math.max(getMax(newBoard), maxNum);
            return;
        }

        for (int dir : new int[] { UP, DOWN, LEFT, RIGHT }) {
            int[][] current = move(newBoard, dir);
            if (getMaxLimit(getMax(current), MAX_CNT - (cnt + 1)) >= maxNum) {
                doGame(current, cnt + 1);
            }
        }
    }

    static int getMaxLimit(int currentMax, int left) {
        for (int idx = 0; idx < left; idx++) {
            currentMax += currentMax;
        }

        return currentMax;
    }

    static void print(int[][] arr) {
        for (int[] data : arr) {
            System.out.println(Arrays.toString(data));
        }
    }

    static int[][] deepcopy(int[][] original) {
        int[][] copy = new int[boardSize][boardSize];

        for (int row = 0; row < boardSize; row++) {
            copy[row] = Arrays.copyOf(original[row], boardSize);
        }

        return copy;
    }

    static int getMax(int[][] arr) {
        int max = 0;
        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                max = Math.max(arr[row][col], max);
            }
        }
        return max;
    }

    static int[][] move(int[][] original, int dir) {
        int[][] moved = deepcopy(original);

        switch (dir) {
            case UP:
                moveUp(moved);
                break;
            case DOWN:
                moveDown(moved);
                break;
            case LEFT:
                moveLeft(moved);
                break;
            case RIGHT:
                moveRight(moved);
                break;
        }

        return moved;
    }

    private static void moveUp(int[][] temp) {
        boolean[][] isIntegrated = new boolean[boardSize][boardSize];

        for (int row = 1; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                if (temp[row][col] == 0)
                    continue;
                for (int curRow = row; curRow > 0; curRow--) {
                    if (temp[curRow - 1][col] == 0) {
                        temp[curRow - 1][col] = temp[curRow][col];
                        temp[curRow][col] = 0;
                        continue;
                    } else if (temp[curRow - 1][col] == temp[curRow][col] && !isIntegrated[curRow - 1][col]) {
                        temp[curRow - 1][col] *= 2;
                        isIntegrated[curRow - 1][col] = true;
                        temp[curRow][col] = 0;
                    }
                    break;
                }
            }
        }
    }

    private static void moveDown(int[][] temp) {
        boolean[][] isIntegrated = new boolean[boardSize][boardSize];

        for (int row = boardSize - 2; row > -1; row--) {
            for (int col = 0; col < boardSize; col++) {
                if (temp[row][col] == 0)
                    continue;
                for (int curRow = row; curRow < boardSize - 1; curRow++) {
                    if (temp[curRow + 1][col] == 0) {
                        temp[curRow + 1][col] = temp[curRow][col];
                        temp[curRow][col] = 0;
                        continue;
                    } else if (temp[curRow + 1][col] == temp[curRow][col] && !isIntegrated[curRow + 1][col]) {
                        temp[curRow + 1][col] *= 2;
                        isIntegrated[curRow + 1][col] = true;
                        temp[curRow][col] = 0;
                    }
                    break;
                }
            }
        }
    }

    private static void moveLeft(int[][] temp) {
        boolean[][] isIntegrated = new boolean[boardSize][boardSize];

        for (int col = 1; col < boardSize; col++) {
            for (int row = 0; row < boardSize; row++) {
                if (temp[row][col] == 0)
                    continue;
                for (int curCol = col; curCol > 0; curCol--) {
                    if (temp[row][curCol - 1] == 0) {
                        temp[row][curCol - 1] = temp[row][curCol];
                        temp[row][curCol] = 0;
                        continue;
                    } else if (temp[row][curCol - 1] == temp[row][curCol] && !isIntegrated[row][curCol - 1]) {
                        temp[row][curCol - 1] *= 2;
                        isIntegrated[row][curCol - 1] = true;
                        temp[row][curCol] = 0;
                    }
                    break;
                }
            }
        }
    }

    private static void moveRight(int[][] temp) {
        boolean[][] isIntegrated = new boolean[boardSize][boardSize];

        for (int col = boardSize - 2; col > -1; col--) {
            for (int row = 0; row < boardSize; row++) {
                if (temp[row][col] == 0)
                    continue;
                for (int curCol = col; curCol < boardSize - 1; curCol++) {
                    if (temp[row][curCol + 1] == 0) {
                        temp[row][curCol + 1] = temp[row][curCol];
                        temp[row][curCol] = 0;
                        continue;
                    } else if (temp[row][curCol + 1] == temp[row][curCol] && !isIntegrated[row][curCol + 1]) {
                        temp[row][curCol + 1] *= 2;
                        isIntegrated[row][curCol + 1] = true;
                        temp[row][curCol] = 0;
                    }
                    break;
                }
            }
        }
    }

}