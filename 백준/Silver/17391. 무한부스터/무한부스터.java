import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int mapRow, mapCol;
    static int[][] map;
    static final int[][] DIR = { { 0, 1 }, { 1, 0 } };

    static class Kart {
        int r, c;
        int cnt;

        public Kart(int r, int c, int cnt) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        mapRow = Integer.parseInt(st.nextToken());
        mapCol = Integer.parseInt(st.nextToken());

        map = new int[mapRow][mapCol];

        for (int row = 0; row < mapRow; row++) {
            st = new StringTokenizer(br.readLine().trim());

            for (int col = 0; col < mapCol; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(move(new Kart(0, 0, 0)));
    }

    static int move(Kart kart) {
        boolean[][] visited = new boolean[mapRow][mapCol];
        visited[kart.r][kart.c] = true;

        Queue<Kart> q = new ArrayDeque<>();
        q.offer(kart);

        while (!q.isEmpty()) {
            Kart now = q.poll();

            if (now.r == mapRow - 1 && now.c == mapCol - 1) {
                return now.cnt;
            }

            for (int[] dir : DIR) {
                for (int mul = map[now.r][now.c]; mul > 0; mul--) {
                    int nr = now.r + dir[0] * mul;
                    int nc = now.c + dir[1] * mul;
                    int nd = now.cnt + 1;

                    if (nr < 0 || nr >= mapRow || nc < 0 || nc >= mapCol || visited[nr][nc]) {
                        continue;
                    }

                    visited[nr][nc] = true;
                    q.offer(new Kart(nr, nc, nd));
                }
            }
        }

        return -1;
    }
}