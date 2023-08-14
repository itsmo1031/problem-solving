import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static enum Direction {
        HORIZONTAL, VERTICAL
    }

    static ArrayDeque<Character> cmd = new ArrayDeque<>();
    static int[][] paper;

    static void unfold(char cmd) {
        int[][] copy = copy(paper);

        if (cmd == 'L' || cmd == 'R')
            copy = flip(copy, Direction.VERTICAL);
        else
            copy = flip(copy, Direction.HORIZONTAL);

        if (cmd == 'L')
            paper = verticalMerge(paper, copy);
        else if (cmd == 'R')
            paper = verticalMerge(copy, paper);
        else if (cmd == 'U')
            paper = horizontalMerge(paper, copy);
        else
            paper = horizontalMerge(copy, paper);
    }

    static int[][] horizontalMerge(int[][] up, int[][] down) {
        int[][] newArr = new int[up.length + down.length][up[0].length];

        for (int row = 0; row < newArr.length; row++) {
            if (row < up.length)
                newArr[row] = Arrays.copyOf(up[row], up[0].length);
            else
                newArr[row] = Arrays.copyOf(down[row - up.length], down[0].length);
        }

        return newArr;
    }

    static int[][] verticalMerge(int[][] left, int[][] right) {
        int[][] newArr = new int[left.length][left[0].length + right[0].length];

        for (int row = 0; row < newArr.length; row++) {
            for (int col = 0; col < left[0].length; col++) {
                newArr[row][col] = left[row][col];
            }
            for (int col = left[0].length; col < left[0].length + right[0].length; col++) {
                newArr[row][col] = right[row][col - left[0].length];
            }
        }

        return newArr;
    }

    static int[][] flip(int[][] original, Direction dir) {
        int[] flipVertical = { 1, 0, 3, 2 };
        int[] flipHorizontal = { 2, 3, 0, 1 };

        if (dir == Direction.VERTICAL) { // 3 <-> 2 , 0 <-> 1
            for (int row = 0; row < original.length; row++) {
                for (int col = 0; col < original[0].length; col++) {
                    original[row][original[0].length - col - 1] = flipVertical[original[row][col]];
                }
            }
        } else {
            for (int row = 0; row < original.length; row++) {
                for (int col = 0; col < original[0].length; col++) {
                    original[original.length - row - 1][col] = flipHorizontal[original[row][col]];
                }
            }
        }

        return original;
    }

    private static int[][] copy(int[][] original) {
        int[][] result = new int[original.length][original[0].length];

        for (int row = 0; row < result.length; row++) {
            result[row] = Arrays.copyOf(original[row], original[row].length);
        }

        return result;
    }

    static void print() {
        for (int[] data : paper) {
            for (int d : data) {
                System.out.print(d + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(br.readLine().trim());
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        for (int idx = 0; idx < k * 2; idx++) {
            cmd.offer(st.nextToken().charAt(0));
        }
        int start = Integer.parseInt(br.readLine().trim());
        paper = new int[][] { { start } };

        while (!cmd.isEmpty()) {
            char command = cmd.pop();

            unfold(command);
        }

        print();
    }
}