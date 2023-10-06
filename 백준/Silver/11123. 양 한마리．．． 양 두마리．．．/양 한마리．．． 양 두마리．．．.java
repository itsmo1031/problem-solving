import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int mapRow, mapCol;
    static final char SHEEP = '#';
    static final int[][] DIR = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
    static boolean[][] visited;
    static char[][] map;

    static class Pos {
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine().trim());
        StringTokenizer st;

        for (int tc = 0; tc < testCase; tc++) {
            st = new StringTokenizer(br.readLine().trim());
            mapRow = Integer.parseInt(st.nextToken());
            mapCol = Integer.parseInt(st.nextToken());

            visited = new boolean[mapRow][mapCol];
            map = new char[mapRow][mapCol];

            for (int row = 0; row < mapRow; row++) {
                map[row] = br.readLine().trim().toCharArray();
            }

            int answer = 0;

            for (int row = 0; row < mapRow; row++) {
                for (int col = 0; col < mapCol; col++) {
                    if (map[row][col] == SHEEP && !visited[row][col]) {
                        answer += bfs(new Pos(row, col));
                    }
                }
            }

            System.out.println(answer);
        }
    }

    private static int bfs(Pos pos) {
        visited[pos.x][pos.y] = true;

        Queue<Pos> q = new ArrayDeque<>();
        q.offer(pos);

        while (!q.isEmpty()) {
            Pos now = q.poll();

            for (int[] dir : DIR) {
                int nx = now.x + dir[0];
                int ny = now.y + dir[1];

                if (nx < 0 || nx >= mapRow || ny < 0 || ny >= mapCol || visited[nx][ny])
                    continue;

                visited[nx][ny] = true;

                if (map[nx][ny] == SHEEP) {
                    q.offer(new Pos(nx, ny));
                }
            }
        }

        return 1;
    }
}