import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static Pos goal;
    static int[][] map;
    static final int POOL = 1;
    static final int[][] DIR = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

    static class Pos {
        int x, y, dist;

        public Pos(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        int goalX = Integer.parseInt(st.nextToken()) + 500;
        int goalY = Integer.parseInt(st.nextToken()) + 500;

        goal = new Pos(goalX, goalY, 0);

        int poolCnt = Integer.parseInt(st.nextToken());
        map = new int[1001][1001];

        for (int idx = 0; idx < poolCnt; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            int x = Integer.parseInt(st.nextToken()) + 500;
            int y = Integer.parseInt(st.nextToken()) + 500;

            map[x][y] = POOL;
        }

        System.out.println(bfs());
    }

    private static int bfs() {
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(new Pos(500, 500, 0));
        boolean[][] visited = new boolean[1001][1001];
        visited[500][500] = true;

        while (!q.isEmpty()) {
            Pos now = q.poll();

            if (now.x == goal.x && now.y == goal.y)
                return now.dist;

            for (int[] dir : DIR) {
                int nx = now.x + dir[0];
                int ny = now.y + dir[1];

                if (nx < 0 || nx > 1000 || ny < 0 || ny > 1000 || visited[nx][ny] || map[nx][ny] == POOL)
                    continue;

                visited[nx][ny] = true;
                q.offer(new Pos(nx, ny, now.dist + 1));
            }
        }

        return -1;
    }

}