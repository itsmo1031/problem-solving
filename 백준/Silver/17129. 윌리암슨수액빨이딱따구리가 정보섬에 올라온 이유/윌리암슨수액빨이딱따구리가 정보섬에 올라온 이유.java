import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int mapRow, mapCol;
    static int[][] map;
    static Pos startPos;
    static final int[][] DIR = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
    static final int WALL = 1;
    static final List<Integer> FOOD = Arrays.asList(3, 4, 5);

    static class Pos {
        int r, c, dist;

        public Pos(int r, int c, int dist) {
            this.r = r;
            this.c = c;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        mapRow = Integer.parseInt(st.nextToken());
        mapCol = Integer.parseInt(st.nextToken());

        map = new int[mapRow][mapCol];

        for (int row = 0; row < mapRow; row++) {
            char[] line = br.readLine().toCharArray();
            for (int col = 0; col < mapCol; col++) {
                int data = line[col] - '0';
                if (data == 2) {
                    startPos = new Pos(row, col, 0);
                    data = 0;
                }
                map[row][col] = data;
            }
        }

        int answer = bfs();

        if (answer == -1) {
            System.out.println("NIE");
        } else {
            System.out.println("TAK");
            System.out.println(answer);
        }
    }

    static int bfs() {
        boolean[][] visited = new boolean[mapRow][mapCol];
        visited[startPos.r][startPos.c] = true;

        Queue<Pos> q = new ArrayDeque<>();

        q.offer(startPos);

        while (!q.isEmpty()) {
            Pos now = q.poll();

            if (isFood(map[now.r][now.c])) {
                return now.dist;
            }

            for (int[] dir : DIR) {
                int nr = now.r + dir[0];
                int nc = now.c + dir[1];

                if (nr < 0 || nr >= mapRow || nc < 0 || nc >= mapCol || visited[nr][nc])
                    continue;

                if (map[nr][nc] != WALL) {
                    visited[nr][nc] = true;
                    q.offer(new Pos(nr, nc, now.dist + 1));
                }
            }
        }

        return -1;
    }

    static boolean isFood(int data) {
        return FOOD.contains(data);
    }
}