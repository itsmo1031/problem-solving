import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int mapRow, mapCol;
    static char[][] map;
    static boolean[][] visited;
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

        mapRow = Integer.parseInt(st.nextToken());
        mapCol = Integer.parseInt(st.nextToken());

        map = new char[mapRow][mapCol];

        for (int row = 0; row < mapRow; row++) {
            map[row] = br.readLine().trim().toCharArray();
        }

        int answer = 0;

        for (int row = 0; row < mapRow; row++) {
            for (int col = 0; col < mapCol; col++) {
                if (map[row][col] == 'L')
                    answer = Math.max(search(new Pos(row, col, 0)), answer);
            }
        }

        System.out.println(answer);
    }

    static int search(Pos pos) {
        int result = 0;
        visited = new boolean[mapRow][mapCol];
        visited[pos.x][pos.y] = true;
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(pos);

        while (!q.isEmpty()) {
            Pos now = q.poll();
            result = Math.max(now.dist, result);

            for (int[] dir : DIR) {
                int nx = now.x + dir[0];
                int ny = now.y + dir[1];

                if (nx < 0 || nx >= mapRow || ny < 0 || ny >= mapCol || visited[nx][ny])
                    continue;

                if (map[nx][ny] == 'L') {
                    visited[nx][ny] = true;
                    q.offer(new Pos(nx, ny, now.dist + 1));
                }
            }
        }

        return result;
    }
}