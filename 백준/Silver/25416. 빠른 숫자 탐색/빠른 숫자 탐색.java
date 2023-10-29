import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static final int[][] DIR = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
    static final int SIZE = 5;
    static int[][] map;

    static class Pos {
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new int[SIZE][SIZE];
        StringTokenizer st;

        for (int row = 0; row < SIZE; row++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int col = 0; col < SIZE; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine().trim());
        Pos current = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

        System.out.println(bfs(current));
    }

    static int bfs(Pos start) {
        Queue<Pos> q = new ArrayDeque<>();
        int[][] visited = new int[SIZE][SIZE];
        q.offer(start);

        while (!q.isEmpty()) {
            Pos now = q.poll();

            if (map[now.x][now.y] == 1) {
                return visited[now.x][now.y];
            }

            for (int[] dir : DIR) {
                int nx = now.x + dir[0];
                int ny = now.y + dir[1];

                if (nx < 0 || nx >= SIZE || ny < 0 || ny >= SIZE || map[nx][ny] == -1 || visited[nx][ny] != 0)
                    continue;

                visited[nx][ny] = visited[now.x][now.y] + 1;

                q.offer(new Pos(nx, ny));
            }
        }
        return -1;
    }
}