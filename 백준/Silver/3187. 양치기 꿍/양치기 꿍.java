import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int mapRow, mapCol;
    static int totalSheep, totalWolf;
    static char[][] map;
    static boolean[][] visited;
    static final int[][] DIR = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
    static final char SHEEP = 'k', WOLF = 'v', WALL = '#';

    static class Pos {
        int r, c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
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

        for (int row = 0; row < mapRow; row++) {
            for (int col = 0; col < mapCol; col++) {
                if (map[row][col] != WALL && !visited[row][col]) {
                    bfs(new Pos(row, col));
                }
            }
        }

        System.out.println(String.format("%d %d", totalSheep, totalWolf));
    }

    static void bfs(Pos pos) {
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(pos);
        visited[pos.r][pos.c] = true;
        int sheep = 0;
        int wolf = 0;

        while (!q.isEmpty()) {
            Pos now = q.poll();

            if (map[now.r][now.c] == SHEEP) {
                sheep += 1;
            } else if (map[now.r][now.c] == WOLF) {
                wolf += 1;
            }

            for (int[] dir : DIR) {
                int nr = now.r + dir[0];
                int nc = now.c + dir[1];

                if (nr < 0 || nr >= mapRow || nc < 0 || nc >= mapCol || visited[nr][nc] || map[nr][nc] == WALL)
                    continue;

                visited[nr][nc] = true;
                q.offer(new Pos(nr, nc));
            }
        }

        if (sheep > wolf) {
            totalSheep += sheep;
        } else {
            totalWolf += wolf;
        }
    }
}