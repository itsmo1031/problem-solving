import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int mapRow, mapCol;
    static char[][] map;
    static boolean[][] visited;
    static final int[][] DIR = { { 0, 1 }, { 1, 0 } };
    static final char PANEL_ROW = '-';

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

        for (int row = 0; row < mapRow; row++) {
            map[row] = br.readLine().trim().toCharArray();
        }

        int answer = 0;
        visited = new boolean[mapRow][mapCol];

        for (int row = 0; row < mapRow; row++) {
            for (int col = 0; col < mapCol; col++) {
                if (!visited[row][col]) {
                    answer += check(new Pos(row, col));
                }
            }
        }

        System.out.println(answer);
    }

    static int check(Pos pos) {
        visited[pos.r][pos.c] = true;
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(pos);

        int dirIdx = getDir(map[pos.r][pos.c]);

        while (!q.isEmpty()) {
            Pos now = q.poll();

            int nr = now.r + DIR[dirIdx][0];
            int nc = now.c + DIR[dirIdx][1];

            if (nr < 0 || nr >= mapRow || nc < 0 || nc >= mapCol || map[nr][nc] != map[now.r][now.c])
                continue;

            visited[nr][nc] = true;

            q.offer(new Pos(nr, nc));
        }

        return 1;
    }

    static int getDir(char c) {
        return c == PANEL_ROW ? 0 : 1;
    }
}