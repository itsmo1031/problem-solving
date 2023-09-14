import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static final int[][] DIR = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
    static int mapRow, mapCol;
    static int[][] fiber;
    static boolean[][] visited;

    static class Pos {
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        mapRow = Integer.parseInt(st.nextToken());
        mapCol = Integer.parseInt(st.nextToken());

        fiber = new int[mapRow][mapCol];
        visited = new boolean[mapRow][mapCol];

        for (int row = 0; row < mapRow; row++) {
            char[] data = br.readLine().trim().toCharArray();
            for (int col = 0; col < mapCol; col++) {
                fiber[row][col] = data[col] - '0';
            }
        }

        System.out.println(canConnect() ? "YES" : "NO");
    }

    private static boolean canConnect() {
        Queue<Pos> q;

        for (int col = 0; col < mapCol; col++) {
            if (fiber[0][col] == 1 || visited[0][col])
                continue;
            visited[0][col] = true;
            q = new ArrayDeque<>();
            q.offer(new Pos(0, col));

            while (!q.isEmpty()) {
                Pos now = q.poll();
                if (now.x == mapRow - 1)
                    return true;
                for (int[] dir : DIR) {
                    int nx = now.x + dir[0];
                    int ny = now.y + dir[1];

                    if (nx < 0 || nx >= mapRow || ny < 0 || ny >= mapCol || visited[nx][ny])
                        continue;

                    if (fiber[nx][ny] == 0) {
                        visited[nx][ny] = true;
                        q.offer(new Pos(nx, ny));
                    }
                }
            }
        }

        return false;
    }
}