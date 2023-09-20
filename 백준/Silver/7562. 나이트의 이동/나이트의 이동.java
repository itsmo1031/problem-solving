import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int mapSize;
    static Pos startPos, endPos;
    static final int[][] DIR = { { 1, 2 }, { -1, 2 }, { 1, -2 }, { -1, -2 }, { 2, 1 }, { -2, 1 }, { 2, -1 },
            { -2, -1 } };

    static class Pos {
        int x, y, time;

        public Pos(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int testCase = Integer.parseInt(br.readLine().trim());

        for (int tc = 0; tc < testCase; tc++) {
            mapSize = Integer.parseInt(br.readLine().trim());

            st = new StringTokenizer(br.readLine().trim());
            int startX = Integer.parseInt(st.nextToken());
            int startY = Integer.parseInt(st.nextToken());
            startPos = new Pos(startX, startY, 0);

            st = new StringTokenizer(br.readLine().trim());
            int endX = Integer.parseInt(st.nextToken());
            int endY = Integer.parseInt(st.nextToken());
            endPos = new Pos(endX, endY, 0);

            sb.append(bfs(startPos)).append("\n");
        }

        System.out.print(sb);
    }

    private static int bfs(Pos start) {
        boolean[][] visited = new boolean[mapSize][mapSize];
        visited[start.x][start.y] = true;

        Queue<Pos> q = new ArrayDeque<>();
        q.add(start);

        while (!q.isEmpty()) {
            Pos now = q.poll();

            if (now.x == endPos.x && now.y == endPos.y)
                return now.time;

            for (int[] dir : DIR) {
                int nx = now.x + dir[0];
                int ny = now.y + dir[1];

                if (nx < 0 || nx >= mapSize || ny < 0 || ny >= mapSize || visited[nx][ny])
                    continue;

                visited[nx][ny] = true;
                q.offer(new Pos(nx, ny, now.time + 1));
            }
        }

        return 0;
    }
}