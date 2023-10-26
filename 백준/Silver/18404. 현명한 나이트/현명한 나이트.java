import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int mapSize, enemyCnt;
    static int[][] DIR = { { -2, -1 }, { -2, 1 }, { 2, 1 }, { 2, -1 }, { 1, 2 }, { 1, -2 }, { -1, 2 }, { -1, -2 } };
    static int[][] visited;
    static final int INF = (int) 1e9;

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
        mapSize = Integer.parseInt(st.nextToken());
        enemyCnt = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine().trim());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        Pos knight = new Pos(r, c);
        StringBuilder sb = new StringBuilder();
        visited = new int[mapSize + 1][mapSize + 1];

        find(knight);

        for (int idx = 0; idx < enemyCnt; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            sb.append(visited[r][c]).append(" ");
        }

        System.out.println(sb.toString().trim());
    }

    static void find(Pos knight) {
        visited[knight.r][knight.c] = 0;
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(knight);

        while (!q.isEmpty()) {
            Pos now = q.poll();

            for (int[] dir : DIR) {
                int nr = now.r + dir[0];
                int nc = now.c + dir[1];
                int nd = visited[now.r][now.c] + 1;

                if (nr < 1 || nr > mapSize || nc < 1 || nc > mapSize || visited[nr][nc] != 0)
                    continue;

                visited[nr][nc] = nd;

                q.offer(new Pos(nr, nc));
            }
        }
    }
}