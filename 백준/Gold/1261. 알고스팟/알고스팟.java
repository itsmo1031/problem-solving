import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int mapRow, mapCol;
    static int[][] maze, cost;
    static final int[][] DIR = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

    static class Pos implements Comparable<Pos> {
        int x, y, cost;

        public Pos(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        @Override
        public int compareTo(Pos o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    static int dijkstra() {
        cost[0][0] = 0;
        PriorityQueue<Pos> pq = new PriorityQueue<>();
        pq.offer(new Pos(0, 0, 0));

        while (!pq.isEmpty()) {
            Pos now = pq.poll();

            if (now.x == mapRow - 1 && now.y == mapCol - 1)
                return now.cost;

            for (int[] dir : DIR) {
                int nx = now.x + dir[0];
                int ny = now.y + dir[1];

                if (nx < 0 || nx >= mapRow || ny < 0 || ny >= mapCol)
                    continue;

                int nc = now.cost + maze[nx][ny];
                if (nc < cost[nx][ny]) {
                    cost[nx][ny] = nc;
                    pq.offer(new Pos(nx, ny, nc));
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        mapCol = Integer.parseInt(st.nextToken());
        mapRow = Integer.parseInt(st.nextToken());

        maze = new int[mapRow][mapCol];

        for (int row = 0; row < mapRow; row++) {
            char[] line = br.readLine().trim().toCharArray();
            for (int col = 0; col < mapCol; col++) {
                maze[row][col] = line[col] - '0';
            }
        }

        cost = new int[mapRow][mapCol];

        for (int[] line : cost) {
            Arrays.fill(line, Integer.MAX_VALUE);
        }

        System.out.println(dijkstra());
    }
}