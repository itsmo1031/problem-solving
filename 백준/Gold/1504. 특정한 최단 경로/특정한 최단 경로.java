import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int nodeCnt, edgeCnt;
    static List<List<Edge>> edgeList;
    static int keyNode1, keyNode2;
    static final int INF = (int) 1e8;

    static class Edge implements Comparable<Edge> {
        int no, dist;

        public Edge(int no, int dist) {
            this.no = no;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge o) {
            return this.dist - o.dist;
        }

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        nodeCnt = Integer.parseInt(st.nextToken());
        edgeCnt = Integer.parseInt(st.nextToken());

        edgeList = new ArrayList<>();

        for (int idx = 0; idx <= nodeCnt; idx++) {
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

        st = new StringTokenizer(br.readLine().trim());
        keyNode1 = Integer.parseInt(st.nextToken());
        keyNode2 = Integer.parseInt(st.nextToken());

        int[] distFromFirstNode = getDist(1);
        int[] distFromKeyNode1 = getDist(keyNode1);
        int[] distFromKeyNode2 = getDist(keyNode2);

        int result = Math.min(distFromFirstNode[keyNode1] + distFromKeyNode1[keyNode2] + distFromKeyNode2[nodeCnt],
                distFromFirstNode[keyNode2] + distFromKeyNode2[keyNode1] + distFromKeyNode1[nodeCnt]);

        System.out.println(result >= INF ? -1 : result);
    }

    static int[] getDist(int nodeIdx) {
        int[] dist = new int[nodeCnt + 1];
        Arrays.fill(dist, INF);
        dist[nodeIdx] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(nodeIdx, 0));

        while (!pq.isEmpty()) {
            Edge now = pq.poll();

            for (Edge next : edgeList.get(now.no)) {
                int cost = dist[now.no] + next.dist;
                if (cost < dist[next.no]) {
                    dist[next.no] = cost;
                    pq.offer(new Edge(next.no, cost));
                }
            }
        }

        return dist;
    }
}