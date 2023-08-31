import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int mapRow, mapCol;
    static char[][] map;
    static boolean[][] visited;
    static final int[][] DIR = { { 0, 1 }, { 1, 0 }, { -1, 0 }, { 0, -1 } };

    static class Pos {
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int getAreaSize(Pos pos) {
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

                if (nx < 0 || nx >= mapRow || ny < 0 || ny >= mapCol || visited[nx][ny])
                    continue;

                if (map[nx][ny] == '.') {
                    visited[nx][ny] = true;
                    q.offer(new Pos(nx, ny));
                }

            }
        }

        return size == 0 ? -1 : size;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        mapRow = Integer.parseInt(st.nextToken());
        mapCol = Integer.parseInt(st.nextToken());

        map = new char[mapRow][mapCol];
        visited = new boolean[mapRow][mapCol];

        for (int row = 0; row < mapRow; row++) {
            map[row] = br.readLine().trim().toCharArray();
        }

        int answer = -1;

        for (int row = 0; row < mapRow; row++) {
            for (int col = 0; col < mapCol; col++) {
                if (map[row][col] == '.')
                    answer = Math.max(answer, getAreaSize(new Pos(row, col)));
            }
        }

        System.out.println(answer);
    }
}