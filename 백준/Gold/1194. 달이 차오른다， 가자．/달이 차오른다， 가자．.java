import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static final int[][] DIR = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
    static final char WALL = '#';
    static final char EXIT = '1';
    static final char EMPTY = '.';
    static char[][] map;
    static int mapRow, mapCol;

    static class Pos {
        int x, y, time;
        int key;

        public Pos(int x, int y, int time, int key) {
            this.x = x;
            this.y = y;
            this.time = time;
            this.key = key;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        mapRow = Integer.parseInt(st.nextToken());
        mapCol = Integer.parseInt(st.nextToken());

        map = new char[mapRow][mapCol];
        Pos start = null;

        for (int row = 0; row < mapRow; row++) {
            char[] line = br.readLine().trim().toCharArray();
            for (int col = 0; col < mapCol; col++) {
                map[row][col] = line[col];
                if (map[row][col] == '0') {
                    start = new Pos(row, col, 0, 0);
                    map[row][col] = '.';
                }
            }
        }

        int answer = escape(start);

        System.out.println(answer);
    }

    static int escape(Pos start) {
        boolean[][][] visited = new boolean[1 << 6][mapRow][mapCol];
        visited[0][start.x][start.y] = true;
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(start);

        while (!q.isEmpty()) {
            Pos now = q.poll();

            for (int[] dir : DIR) {
                int nx = now.x + dir[0];
                int ny = now.y + dir[1];
                int nt = now.time + 1;

                if (nx < 0 || nx >= mapRow || ny < 0 || ny >= mapCol || visited[now.key][nx][ny]
                        || map[nx][ny] == WALL)
                    continue;

                if (map[nx][ny] == EXIT)
                    return nt;

                int newKey = now.key;
                visited[newKey][nx][ny] = true;

                if (isDoor(map[nx][ny])) {
                    if (canOpen(newKey, map[nx][ny])) {
                        q.offer(new Pos(nx, ny, nt, newKey));
                    }
                    continue;
                }

                if (isKey(map[nx][ny])) {
                    newKey = addKey(now.key, map[nx][ny]);
                }

                q.offer(new Pos(nx, ny, nt, newKey));
            }
        }

        return -1;
    }

    static boolean isDoor(char ch) {
        return ch >= 'A' && ch <= 'F';
    }

    static boolean isKey(char ch) {
        return ch >= 'a' && ch <= 'f';
    }

    static int addKey(int original, char keyCh) {
        int newKey = 1 << (keyCh - 'a');

        return original | newKey;
    }

    static boolean canOpen(int key, char door) {
        int cand = 1 << (door - 'A');

        return (key & cand) > 0;
    }

}