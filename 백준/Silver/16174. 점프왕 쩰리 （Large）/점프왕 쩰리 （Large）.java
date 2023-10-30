import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int size;
    static int[][] map;
    static final int[][] DIR = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

    static class Pos {
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        size = Integer.parseInt(br.readLine().trim());
        map = new int[size][size];

        for (int row = 0; row < size; row++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int col = 0; col < size; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(jumpking());
    }

    private static String jumpking() {
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(new Pos(0, 0));
        boolean[][] visited = new boolean[size][size];
        visited[0][0] = true;

        while (!q.isEmpty()) {
            Pos now = q.poll();

            int mul = map[now.x][now.y];

            if (mul == -1)
                return "HaruHaru";

            for (int[] dir : DIR) {
                int nx = now.x + dir[0] * mul;
                int ny = now.y + dir[1] * mul;

                if (nx < 0 || nx >= size || ny < 0 || ny >= size || visited[nx][ny])
                    continue;

                visited[nx][ny] = true;

                q.offer(new Pos(nx, ny));
            }
        }

        return "Hing";
    }
}