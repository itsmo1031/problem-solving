import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static final int INF = (int) 1e9;
    static int cityCnt;
    static List<List<Edge>> busList;
    static int[] history;
    static int[] cost;

    static class Edge implements Comparable<Edge> {
        int num;
        int weight;

        public Edge(int num, int weight) {
            this.num = num;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        cityCnt = Integer.parseInt(br.readLine().trim());
        int busCnt = Integer.parseInt(br.readLine().trim());
        busList = new ArrayList<>();
        history = new int[cityCnt + 1];
        cost = new int[cityCnt + 1];

        Arrays.fill(cost, INF);

        for (int idx = 1; idx <= cityCnt; idx++) {
            history[idx] = idx;
        }

        for (int idx = 0; idx <= cityCnt; idx++) {
            busList.add(new ArrayList<>());
        }

        StringTokenizer st;

        for (int idx = 0; idx < busCnt; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            busList.get(from).add(new Edge(to, weight));
        }

        st = new StringTokenizer(br.readLine().trim());

        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        System.out.println(dijkstra(start, end));
        System.out.println(getHistory(start, end));
    }

    static String getHistory(int start, int end) {
        StringBuilder sb = new StringBuilder();
        Deque<Integer> q = new ArrayDeque<>();
        int now = end;

        while (now != start) {
            q.offer(history[now]);
            now = history[now];
        }

        sb.append(q.size() + 1).append("\n");

        while (!q.isEmpty()) {
            sb.append(q.pollLast()).append(" ");
        }

        sb.append(end);

        return sb.toString();
    }

    static int dijkstra(int start, int end) {
        boolean[] visited = new boolean[cityCnt + 1];
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        visited[start] = true;
        cost[start] = 0;

        pq.offer(new Edge(start, 0));

        while (!pq.isEmpty()) {
            Edge now = pq.poll();

            if (now.num == end)
                return now.weight;

            for (Edge next : busList.get(now.num)) {
                int weight = now.weight + next.weight;

                if (weight < cost[next.num]) {
                    cost[next.num] = weight;
                    history[next.num] = now.num;
                    pq.offer(new Edge(next.num, weight));
                }
            }
        }

        return -1;
    }
}