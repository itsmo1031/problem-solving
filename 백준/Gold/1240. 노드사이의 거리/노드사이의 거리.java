import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int nodeCnt, targetCnt;
    static List<List<Edge>> list;

    static class Edge {
        int no, dist;

        public Edge(int no, int dist) {
            this.no = no;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        nodeCnt = Integer.parseInt(st.nextToken());
        targetCnt = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();

        for (int idx = 0; idx <= nodeCnt; idx++) {
            list.add(new ArrayList<>());
        }

        for (int idx = 0; idx < nodeCnt - 1; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            list.get(from).add(new Edge(to, dist));
            list.get(to).add(new Edge(from, dist));
        }

        StringBuilder sb = new StringBuilder();

        for (int idx = 0; idx < targetCnt; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            sb.append(getDist(from, to)).append("\n");
        }
        System.out.println(sb);
    }

    static int getDist(int from, int to) {
        Queue<Edge> q = new ArrayDeque<>();
        boolean[] visited = new boolean[nodeCnt + 1];
        visited[from] = true;
        q.offer(new Edge(from, 0));

        while (!q.isEmpty()) {
            Edge now = q.poll();

            if (now.no == to) {
                return now.dist;
            }

            for (Edge next : list.get(now.no)) {
                if (visited[next.no])
                    continue;

                visited[next.no] = true;
                q.offer(new Edge(next.no, now.dist + next.dist));
            }
        }

        return 0;
    }
}