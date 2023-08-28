import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int vertexCnt, edgeCnt;
    static int[] parent;
    static List<Edge> edgeList;

    static class Edge implements Comparable<Edge> {
        int from, to, weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }

    }

    static int find(int child) {
        if (parent[child] != child)
            parent[child] = find(parent[child]);
        return parent[child];
    }

    static void union(int child1, int child2) {
        int parent1 = find(child1);
        int parent2 = find(child2);

        if (parent1 < parent2)
            parent[parent2] = parent1;
        else
            parent[parent1] = parent2;
    }

    static int findMST() {
        Collections.sort(edgeList);
        int result = 0;

        for (Edge edge : edgeList) {
            if (find(edge.from) != find(edge.to)) {
                union(edge.from, edge.to);
                result += edge.weight;
            }
        }

        return result;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        vertexCnt = Integer.parseInt(st.nextToken());
        edgeCnt = Integer.parseInt(st.nextToken());

        parent = new int[vertexCnt + 1];
        for (int idx = 1; idx <= vertexCnt; idx++) {
            parent[idx] = idx;
        }

        edgeList = new ArrayList<>();

        for (int idx = 0; idx < edgeCnt; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            edgeList.add(new Edge(from, to, weight));
        }

        System.out.println(findMST());
    }
}