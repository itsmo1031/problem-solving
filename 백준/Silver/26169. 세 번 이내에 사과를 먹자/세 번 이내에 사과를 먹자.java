import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;
    static final int SIZE = 5, OBSTACLE = -1;
    static final int[][] DIR = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
    static boolean canEat;

    static class Pos {
        int r, c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;

        map = new int[SIZE][SIZE];

        for (int row = 0; row < SIZE; row++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int col = 0; col < SIZE; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine().trim());

        Pos start = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        map[start.r][start.c] = OBSTACLE;
        eat(start, 0, 0);

        System.out.println(canEat ? 1 : 0);
    }

    static void eat(Pos current, int depth, int apple) {
        if (apple == 2) {
            canEat = true;
        }

        if (canEat || depth == 3) {
            return;
        }

        for (int[] dir : DIR) {
            int nr = current.r + dir[0];
            int nc = current.c + dir[1];

            if (nr < 0 || nr >= SIZE || nc < 0 || nc >= SIZE || map[nr][nc] == OBSTACLE) {
                continue;
            }

            int original = map[nr][nc];
            apple += original;
            map[nr][nc] = OBSTACLE;
            eat(new Pos(nr, nc), depth + 1, apple);
            map[nr][nc] = original;
            apple -= original;
        }
    }
}