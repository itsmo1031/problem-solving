import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int totalCnt;
    static List<List<Integer>> barn;
    static int[] dist;

    static void dijkstra() {
        PriorityQueue<Barn> pq = new PriorityQueue<>();

        pq.add(new Barn(1, 0));

        while (!pq.isEmpty()) {
            Barn now = pq.poll();
            int nd = now.dist + 1;

            for (int next : barn.get(now.num)) {
                if (nd < dist[next]) {
                    dist[next] = nd;
                    pq.add(new Barn(next, nd));
                }
            }
        }
    }

    static class Barn implements Comparable<Barn> {
        int num;
        int dist;

        public Barn(int num, int dist) {
            this.num = num;
            this.dist = dist;
        }

        @Override
        public int compareTo(Barn o) {
            return Integer.compare(this.dist, o.dist);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        totalCnt = Integer.parseInt(st.nextToken());
        int edgeCnt = Integer.parseInt(st.nextToken());

        dist = new int[totalCnt + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;

        barn = new ArrayList<>();

        for (int idx = 0; idx < totalCnt + 1; idx++) {
            barn.add(new ArrayList<>());
        }

        for (int idx = 0; idx < edgeCnt; idx++) {
            st = new StringTokenizer(br.readLine().trim());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            barn.get(from).add(to);
            barn.get(to).add(from);
        }

        dijkstra();

        int answer = -1;
        int answerIdx = 0;
        int answerCnt = 0;

        for (int idx = 1; idx <= totalCnt; idx++) {
            if (answer < dist[idx]) {
                answerCnt = 1;
                answer = dist[idx];
                answerIdx = idx;
            } else if (dist[idx] == answer)
                answerCnt += 1;
        }

        System.out.printf("%d %d %d\n", answerIdx, answer, answerCnt);
    }
}
