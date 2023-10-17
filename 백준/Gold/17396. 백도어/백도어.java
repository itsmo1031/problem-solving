import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] visibility;
    static List<List<Edge>> edgeList;
    static int courseCnt, edgeCnt;
    static final long INF = (long) 2e10;

    static class Edge implements Comparable<Edge> {
        int no;
        long cost;

        public Edge(int no, long cost) {
            this.no = no;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Long.compare(this.cost, o.cost);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        courseCnt = Integer.parseInt(st.nextToken());
        edgeCnt = Integer.parseInt(st.nextToken());

        visibility = new int[courseCnt];

        st = new StringTokenizer(br.readLine().trim());
        edgeList = new ArrayList<>();

        for (int idx = 0; idx < courseCnt; idx++) {
            visibility[idx] = Integer.parseInt(st.nextToken());
            edgeList.add(new ArrayList<>());
        }

        for (int idx = 0; idx < edgeCnt; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            edgeList.get(from).add(new Edge(to, cost));
            edgeList.get(to).add(new Edge(from, cost));
        }

        System.out.println(dijkstra());
    }

    static long dijkstra() {
        Queue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(0, 0));
        long[] cost = new long[courseCnt];
        Arrays.fill(cost, INF);
        cost[0] = 0;

        while (!pq.isEmpty()) {
            Edge now = pq.poll();

            if (now.no == courseCnt - 1)
                return now.cost;

            if (now.cost > cost[now.no])
                continue;

            for (Edge next : edgeList.get(now.no)) {
                long nc = now.cost + next.cost;
                if (next.no != courseCnt - 1 && visibility[next.no] == 1) {
                    continue;
                }
                if (nc < cost[next.no]) {
                    cost[next.no] = nc;
                    pq.offer(new Edge(next.no, nc));
                }
            }
        }

        return -1;
    }
}