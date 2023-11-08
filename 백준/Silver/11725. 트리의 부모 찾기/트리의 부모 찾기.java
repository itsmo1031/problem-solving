import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int nodeCnt;
    static List<List<Integer>> edge;

    static class Node {
        int from;
        int to;

        public Node(int from, int to) {
            this.from = from;
            this.to = to;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        nodeCnt = Integer.parseInt(br.readLine().trim());
        edge = new ArrayList<>();

        for (int idx = 0; idx <= nodeCnt; idx++) {
            edge.add(new ArrayList<>());
        }

        StringTokenizer st;
        for (int idx = 1; idx < nodeCnt; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            edge.get(from).add(to);
            edge.get(to).add(from);
        }

        bfs();
    }

    static void bfs() {
        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node(0, 1));
        int[] parent = new int[nodeCnt + 1];
        boolean[] visited = new boolean[nodeCnt + 1];
        visited[1] = true;

        while (!q.isEmpty()) {
            Node now = q.poll();

            if (parent[now.to] == 0) {
                parent[now.to] = now.from;
            }

            for (int next : edge.get(now.to)) {
                if (visited[next])
                    continue;

                visited[next] = true;
                q.offer(new Node(now.to, next));
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int idx = 2; idx <= nodeCnt; idx++) {
            sb.append(parent[idx]).append("\n");
        }

        System.out.print(sb);

    }
}