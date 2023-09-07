import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int mapSize;
    static int minHeight = Integer.MAX_VALUE;
    static int maxHeight = Integer.MIN_VALUE;
    static int[][] map;
    static boolean[][] visited;
    static final int[][] DIR = { { 0, 1 }, { 1, 0 }, { -1, 0 }, { 0, -1 } };

    static class Pos {
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int getSafeArea(int height) {
        visited = new boolean[mapSize][mapSize];
        int result = 0;

        for (int row = 0; row < mapSize; row++) {
            for (int col = 0; col < mapSize; col++) {
                if (!visited[row][col] && map[row][col] > height) {
                    result += bfs(new Pos(row, col), height);
                }
            }
        }

        return result;

    }

    static int bfs(Pos pos, int height) {
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(pos);
        visited[pos.x][pos.y] = true;
        while (!q.isEmpty()) {
            Pos now = q.poll();

            for (int[] dir : DIR) {
                int nx = now.x + dir[0];
                int ny = now.y + dir[1];

                if (nx < 0 || nx >= mapSize || ny < 0 || ny >= mapSize || visited[nx][ny])
                    continue;

                visited[nx][ny] = true;

                if (map[nx][ny] > height) {
                    q.offer(new Pos(nx, ny));
                }
            }
        }

        return 1;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        mapSize = Integer.parseInt(br.readLine().trim());

        map = new int[mapSize][mapSize];

        for (int row = 0; row < mapSize; row++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int col = 0; col < mapSize; col++) {
                int data = Integer.parseInt(st.nextToken());
                map[row][col] = data;
                minHeight = Math.min(data, minHeight);
                maxHeight = Math.max(data, maxHeight);
            }
        }

        int answer = 1;

        for (int height = minHeight; height < maxHeight; height++) {
            answer = Math.max(getSafeArea(height), answer);
        }

        System.out.println(answer);
    }

}