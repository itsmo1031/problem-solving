import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int arrSize;
    static int[][] arr;
    static final int INF = (int) 1e9;
    static final int[][] DIR = { { 0, 1 }, { 1, 0 } };

    static class Pos implements Comparable<Pos> {
        int r, c, dist;

        public Pos(int r, int c, int dist) {
            this.r = r;
            this.c = c;
            this.dist = dist;
        }

        @Override
        public int compareTo(Pos o) {
            return Integer.compare(this.dist, o.dist);
        }

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        arrSize = Integer.parseInt(br.readLine().trim());
        arr = new int[arrSize][arrSize];

        for (int row = 0; row < arrSize; row++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int col = 0; col < arrSize; col++) {
                arr[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(dijkstra());
    }

    static int dijkstra() {
        Queue<Pos> pq = new PriorityQueue<>();
        int[][] cost = new int[arrSize][arrSize];
        for (int row = 0; row < arrSize; row++) {
            Arrays.fill(cost[row], INF);
        }
        cost[0][0] = 0;

        pq.offer(new Pos(0, 0, 0));

        while (!pq.isEmpty()) {
            Pos now = pq.poll();

            if (now.r == arrSize - 1 && now.c == arrSize - 1)
                return now.dist;

            for (int[] dir : DIR) {
                int nr = now.r + dir[0];
                int nc = now.c + dir[1];
                int nd = now.dist;
                if (nr < 0 || nr >= arrSize || nc < 0 || nc >= arrSize)
                    continue;
                if (arr[now.r][now.c] <= arr[nr][nc]) {
                    nd += arr[nr][nc] - arr[now.r][now.c] + 1;
                }
                if (nd < cost[nr][nc]) {
                    cost[nr][nc] = nd;
                    pq.offer(new Pos(nr, nc, nd));
                }
            }
        }

        return -1;
    }
}