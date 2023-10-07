import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int pcCnt, lineCnt;
    static Queue<Line> lineList;
    static int[] parent;

    static class Line implements Comparable<Line> {
        int from, to, cost;

        public Line(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Line o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        pcCnt = Integer.parseInt(br.readLine().trim());
        lineCnt = Integer.parseInt(br.readLine().trim());

        lineList = new PriorityQueue<>();

        for (int idx = 0; idx < lineCnt; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            lineList.add(new Line(from, to, cost));
        }

        parent = new int[pcCnt + 1];

        for (int idx = 1; idx <= pcCnt; idx++) {
            parent[idx] = idx;
        }

        System.out.println(findMST());
    }

    static int findMST() {
        int totalCost = 0;
        int count = 0;

        while (count < pcCnt - 1) {
            Line line = lineList.poll();
            if (find(line.from) != find(line.to)) {
                union(line.from, line.to);
                totalCost += line.cost;
                count++;
            }
        }

        return totalCost;
    }

    static void union(int child1, int child2) {
        int parent1 = find(child1);
        int parent2 = find(child2);

        if (parent1 < parent2) {
            parent[parent2] = parent1;
        } else {
            parent[parent1] = parent2;
        }
    }

    static int find(int child) {
        if (parent[child] != child)
            parent[child] = find(parent[child]);
        return parent[child];
    }
}