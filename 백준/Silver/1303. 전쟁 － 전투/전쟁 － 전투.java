import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int rowSize, colSize;
    static int white, blue;
    static final int[][] DIR = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
    static char[][] map;
    static boolean[][] visited;

    static class Pos {
        int r, c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        colSize = Integer.parseInt(st.nextToken());
        rowSize = Integer.parseInt(st.nextToken());

        map = new char[rowSize][colSize];
        visited = new boolean[rowSize][colSize];

        for (int row = 0; row < rowSize; row++) {
            map[row] = br.readLine().trim().toCharArray();
        }

        white = blue = 0;

        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {
                if (visited[row][col])
                    continue;
                if (map[row][col] == 'W') {
                    white += getPower(new Pos(row, col), 'W');
                } else {
                    blue += getPower(new Pos(row, col), 'B');
                }
            }
        }

        System.out.printf("%d %d", white, blue);
    }

    private static int getPower(Pos start, char type) {
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(start);
        visited[start.r][start.c] = true;
        int count = 0;

        while (!q.isEmpty()) {
            Pos now = q.poll();

            count += 1;

            for (int[] dir : DIR) {
                int nr = now.r + dir[0];
                int nc = now.c + dir[1];

                if (nr < 0 || nr >= rowSize || nc < 0 || nc >= colSize || visited[nr][nc])
                    continue;

                if (map[nr][nc] == type) {
                    visited[nr][nc] = true;
                    q.offer(new Pos(nr, nc));
                }
            }
        }

        return count * count;
    }

}