import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static final int[][] DIR = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
    static final int TRASH = 1;
    static int[][] map;
    static boolean[][] visited;
    static int mapWidth, mapHeight;

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

        mapWidth = Integer.parseInt(st.nextToken());
        mapHeight = Integer.parseInt(st.nextToken());

        map = new int[mapWidth][mapHeight];
        visited = new boolean[mapWidth][mapHeight];
        int trashCnt = Integer.parseInt(st.nextToken());

        for (int cnt = 0; cnt < trashCnt; cnt++) {
            st = new StringTokenizer(br.readLine().trim());

            int row = Integer.parseInt(st.nextToken()) - 1;
            int col = Integer.parseInt(st.nextToken()) - 1;

            map[row][col] = TRASH;
        }

        int answer = 0;

        for (int row = 0; row < mapWidth; row++) {
            for (int col = 0; col < mapHeight; col++) {
                if (map[row][col] == TRASH && !visited[row][col])
                    answer = Math.max(findSize(new Pos(row, col)), answer);
            }
        }

        System.out.println(answer);
    }

    static int findSize(Pos pos) {
        Queue<Pos> q = new ArrayDeque<>();
        visited[pos.x][pos.y] = true;
        q.offer(pos);
        int size = 0;

        while (!q.isEmpty()) {
            Pos now = q.poll();
            size += 1;

            for (int[] dir : DIR) {
                int nx = now.x + dir[0];
                int ny = now.y + dir[1];

                if (nx < 0 || nx >= mapWidth || ny < 0 || ny >= mapHeight || visited[nx][ny])
                    continue;

                visited[nx][ny] = true;

                if (map[nx][ny] == TRASH) {
                    q.offer(new Pos(nx, ny));
                }
            }
        }

        return size;
    }

}