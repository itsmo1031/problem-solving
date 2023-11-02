import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int mapRow, mapCol;
    static char[][] map;
    static boolean[][] visited;
    static final char WOLF = 'W', WALL = '#', ICE = '+', GRASS = '.';
    static final int[][] DIR = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

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

        List<Pos> wolfList = new ArrayList<>();

        for (int row = 0; row < mapRow; row++) {
            char[] line = br.readLine().trim().toCharArray();
            for (int col = 0; col < mapCol; col++) {
                char data = line[col];
                if (data == WOLF) {
                    wolfList.add(new Pos(row, col));
                }
                map[row][col] = data;
            }
        }

        visited = new boolean[mapRow][mapCol];

        for (Pos wolf : wolfList) {
            find(wolf);
        }

        printResult();
    }

    static void printResult() {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < mapRow; row++) {
            for (int col = 0; col < mapCol; col++) {
                if (map[row][col] == GRASS && !visited[row][col]) {
                    sb.append('P');
                } else {
                    sb.append(map[row][col]);
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static void find(Pos wolf) {
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(wolf);
        visited[wolf.r][wolf.c] = true;

        while (!q.isEmpty()) {
            Pos now = q.poll();

            for (int[] dir : DIR) {
                int nr = now.r + dir[0];
                int nc = now.c + dir[1];

                if (isNotValid(nr, nc)) {
                    continue;
                }

                if (map[nr][nc] == ICE) {
                    Pos np = slide(nr, nc, dir);
                    nr = np.r;
                    nc = np.c;
                }

                if (visited[nr][nc] || map[nr][nc] == WALL) {
                    continue;
                }

                visited[nr][nc] = true;

                q.offer(new Pos(nr, nc));
            }
        }
    }

    static Pos slide(int nr, int nc, int[] dir) {
        while (true) {
            nr += dir[0];
            nc += dir[1];
            if (isNotValid(nr, nc) || map[nr][nc] == WALL) {
                nr -= dir[0];
                nc -= dir[1];
                break;
            }

            if (map[nr][nc] == GRASS)
                break;
        }

        return new Pos(nr, nc);
    }

    static boolean isNotValid(int nr, int nc) {
        return (nr <= 0 || nr > mapRow || nc <= 0 || nc > mapCol);
    }
}