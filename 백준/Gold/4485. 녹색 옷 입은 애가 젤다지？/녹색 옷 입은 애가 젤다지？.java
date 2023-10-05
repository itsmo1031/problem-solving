import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static final int[][] DIR = { { 0, 1 }, { 1, 0 }, { -1, 0 }, { 0, -1 } };
    static final int INF = (int) 1e9;
    static int[][] cave;
    static int[][] cost;
    static int caveSize;

    static class Pos implements Comparable<Pos> {
        int r, c, weight;

        public Pos(int r, int c, int weight) {
            this.r = r;
            this.c = c;
            this.weight = weight;
        }

        @Override
        public int compareTo(Pos o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int testCase = 0;
        while (true) {
            caveSize = Integer.parseInt(br.readLine().trim());
            if (caveSize == 0)
                break;

            testCase += 1;

            cave = new int[caveSize][caveSize];
            cost = new int[caveSize][caveSize];

            for (int row = 0; row < caveSize; row++) {
                st = new StringTokenizer(br.readLine().trim());
                Arrays.fill(cost[row], INF);
                for (int col = 0; col < caveSize; col++) {
                    cave[row][col] = Integer.parseInt(st.nextToken());
                }
            }

            int answer = getLowestCost();

            sb.append("Problem ").append(testCase).append(": ").append(answer).append("\n");
        }

        System.out.print(sb);
    }

    static int getLowestCost() {
        PriorityQueue<Pos> pq = new PriorityQueue<>();
        pq.offer(new Pos(0, 0, cave[0][0]));

        while (!pq.isEmpty()) {
            Pos now = pq.poll();

            for (int[] dir : DIR) {
                int nr = now.r + dir[0];
                int nc = now.c + dir[1];

                if (nr < 0 || nr >= caveSize || nc < 0 || nc >= caveSize) {
                    continue;
                }

                int nw = now.weight + cave[nr][nc];
                if (nw < cost[nr][nc]) {
                    cost[nr][nc] = nw;
                    pq.offer(new Pos(nr, nc, nw));
                }
            }
        }

        return cost[caveSize - 1][caveSize - 1];
    }
}
