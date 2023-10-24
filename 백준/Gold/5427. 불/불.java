import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int mapCol, mapRow;
    static final int[][] DIR = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
    static char[][] map;
    static final char FIRE = '*', WALL = '#';
    static final int INF = (int) 1e9;
    static int[][] fire;
    static Pos person;

    static class Pos {
        int r, c, time;

        public Pos(int r, int c, int time) {
            this.r = r;
            this.c = c;
            this.time = time;
        }

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        List<Pos> fireList;

        int testCase = Integer.parseInt(br.readLine().trim());
        for (int tc = 0; tc < testCase; tc++) {
            st = new StringTokenizer(br.readLine().trim());
            mapCol = Integer.parseInt(st.nextToken());
            mapRow = Integer.parseInt(st.nextToken());

            map = new char[mapRow][mapCol];
            fire = new int[mapRow][mapCol];
            fireList = new ArrayList<>();

            for (int row = 0; row < mapRow; row++) {
                Arrays.fill(fire[row], INF);
                char[] line = br.readLine().trim().toCharArray();
                for (int col = 0; col < mapCol; col++) {
                    char data = line[col];
                    if (line[col] == '@') {
                        person = new Pos(row, col, 0);
                        data = '.';
                    } else if (line[col] == FIRE) {
                        fire[row][col] = 0;
                        fireList.add(new Pos(row, col, 0));
                    }
                    map[row][col] = data;
                }
            }

            spread(fireList);

            int answer = escape(person);

            System.out.println(answer == -1 ? "IMPOSSIBLE" : answer);
        }
    }

    static int escape(Pos pos) {
        boolean[][] visited = new boolean[mapRow][mapCol];
        visited[pos.r][pos.c] = true;
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(pos);

        while (!q.isEmpty()) {
            Pos now = q.poll();

            if (isEscaped(now))
                return now.time + 1;

            for (int[] dir : DIR) {
                int nr = dir[0] + now.r;
                int nc = dir[1] + now.c;
                int nt = now.time + 1;

                if (map[nr][nc] == WALL || visited[nr][nc])
                    continue;

                if (nt < fire[nr][nc]) {
                    visited[nr][nc] = true;
                    q.offer(new Pos(nr, nc, nt));
                }
            }
        }

        return -1;
    }

    static boolean isEscaped(Pos pos) {
        return pos.r == 0 || pos.c == 0 || pos.r == mapRow - 1 || pos.c == mapCol - 1;
    }

    static void spread(List<Pos> fireList) {
        Queue<Pos> q = new ArrayDeque<>(fireList);

        while (!q.isEmpty()) {
            Pos now = q.poll();

            for (int[] dir : DIR) {
                int nr = now.r + dir[0];
                int nc = now.c + dir[1];
                int nt = now.time + 1;
                if (isInvalid(nr, nc) || map[nr][nc] == WALL)
                    continue;

                if (nt < fire[nr][nc]) {
                    fire[nr][nc] = nt;
                    q.offer(new Pos(nr, nc, nt));
                }
            }
        }

    }

    static boolean isInvalid(int r, int c) {
        return r < 0 || r >= mapRow || c < 0 || c >= mapCol;
    }
}