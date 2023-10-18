import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int transformCnt;
    static Robot[] robot;
    static int[] cost;
    static final int INF = (int) 1e9;
    static int from, to;

    static class Robot {
        String[] status;

        public Robot(String str) {
            status = str.split("");
        }

        int transform(Robot r) {
            int result = 0;

            for (int idx = 0; idx < status.length; idx++) {
                int a = status[idx].charAt(0) - '0';
                int b = r.status[idx].charAt(0) - '0';

                result += Math.pow(a - b, 2);
            }

            return result;
        }
    }

    static class Info implements Comparable<Info> {
        int idx, cost;

        public Info(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }

        @Override
        public int compareTo(Info o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        transformCnt = Integer.parseInt(br.readLine().trim());

        robot = new Robot[transformCnt + 1];
        cost = new int[transformCnt + 1];
        Arrays.fill(cost, INF);

        for (int idx = 1; idx <= transformCnt; idx++) {
            robot[idx] = new Robot(br.readLine().trim());
        }

        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        from = Integer.parseInt(st.nextToken());
        to = Integer.parseInt(st.nextToken());

        System.out.println(dijkstra(from));
    }

    static int dijkstra(int start) {
        cost[start] = 0;
        Queue<Info> q = new PriorityQueue<>();
        q.offer(new Info(start, 0));

        while (!q.isEmpty()) {
            Info now = q.poll();

            if (now.idx == to)
                return now.cost;

            for (int idx = 1; idx <= transformCnt; idx++) {
                if (idx == now.idx)
                    continue;
                int nc = now.cost + robot[now.idx].transform(robot[idx]);
                if (nc < cost[idx]) {
                    cost[idx] = nc;
                    q.offer(new Info(idx, nc));
                }
            }
        }
        return -1;
    }
}