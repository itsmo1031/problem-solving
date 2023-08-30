import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static final int[][] DIR = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };
    static final int[][] HORSE = { { -1, -2 }, { -2, -1 }, { -2, 1 }, { -1, 2 }, { 1, 2 }, { 2, 1 }, { 2, -1 },
            { 1, -2 } };
    static int mapWidth, mapHeight;
    static int horseMoveLimit;
    static int[][] map;

    static class Monkey {
        int x, y;
        int moveCnt;
        int horseMoveCnt;

        public Monkey(int x, int y, int moveCnt, int horseMoveCnt) {
            this.x = x;
            this.y = y;
            this.moveCnt = moveCnt;
            this.horseMoveCnt = horseMoveCnt;
        }
    }

    static int move() {
        Queue<Monkey> q = new ArrayDeque<>();
        boolean[][][] visited = new boolean[horseMoveLimit + 1][mapHeight][mapWidth];
        q.offer(new Monkey(0, 0, 0, 0));
        visited[0][0][0] = true;

        while (!q.isEmpty()) {
            Monkey now = q.poll();
            
            if (arrived(now.x, now.y))
                return now.moveCnt;

            int layer = now.horseMoveCnt;

            for (int[] dir : DIR) {
                int nx = now.x + dir[0];
                int ny = now.y + dir[1];
                if (nx < 0 || nx >= mapHeight || ny < 0 || ny >= mapWidth || visited[layer][nx][ny] || map[nx][ny] == 1)
                    continue;
                visited[layer][nx][ny] = true;
                q.offer(new Monkey(nx, ny, now.moveCnt + 1, layer));
            }

            if (layer == horseMoveLimit)
                continue;

            for (int[] dir : HORSE) {
                int nx = now.x + dir[0];
                int ny = now.y + dir[1];
                if (nx < 0 || nx >= mapHeight || ny < 0 || ny >= mapWidth || visited[layer + 1][nx][ny]
                        || map[nx][ny] == 1)
                    continue;
                visited[layer + 1][nx][ny] = true;
                q.offer(new Monkey(nx, ny, now.moveCnt + 1, layer + 1));
            }

        }

        return -1;
    }

    static boolean arrived(int x, int y) {
        return x == mapHeight - 1 && y == mapWidth - 1;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        horseMoveLimit = Integer.parseInt(br.readLine().trim());
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        mapWidth = Integer.parseInt(st.nextToken());
        mapHeight = Integer.parseInt(st.nextToken());

        map = new int[mapHeight][mapWidth];

        for (int row = 0; row < mapHeight; row++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int col = 0; col < mapWidth; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(move());
    }

}
