import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int PCCnt, dependencyCnt, hackedPCNo;
    static List<List<Dependency>> PCList;

    static class Dependency implements Comparable<Dependency> {
        int dest, time;

        public Dependency(int dest, int time) {
            this.dest = dest;
            this.time = time;
        }

        @Override
        public int compareTo(Dependency o) {
            return Integer.compare(this.time, o.time);
        }

    }

    static class Result {
        int infestedPCCnt, totalTime;

        public Result(int infestedPCCnt, int totalTime) {
            this.infestedPCCnt = infestedPCCnt;
            this.totalTime = totalTime;
        }

        @Override
        public String toString() {
            return String.format("%d %d", infestedPCCnt, totalTime);
        }
    }

    static Result dijkstra(int start) {
        int infested = 0;
        int totalTime = 0;
        PriorityQueue<Dependency> pq = new PriorityQueue<>();
        int[] cost = new int[PCCnt + 1];
        Arrays.fill(cost, Integer.MAX_VALUE);
        cost[start] = 0;
        pq.offer(new Dependency(start, 0));

        while (!pq.isEmpty()) {
            Dependency now = pq.poll();

            if (cost[now.dest] < now.time)
                continue;

            for (Dependency next : PCList.get(now.dest)) {
                if (now.time + next.time < cost[next.dest]) {
                    cost[next.dest] = now.time + next.time;
                    pq.offer(new Dependency(next.dest, cost[next.dest]));
                }
            }
        }

        for (int idx = 1; idx <= PCCnt; idx++) {
            if (cost[idx] != Integer.MAX_VALUE) {
                infested += 1;
                totalTime = Math.max(totalTime, cost[idx]);
            }
        }

        return new Result(infested, totalTime);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int testCase = Integer.parseInt(br.readLine().trim());

        for (int tc = 0; tc < testCase; tc++) {
            st = new StringTokenizer(br.readLine().trim());

            PCCnt = Integer.parseInt(st.nextToken());
            dependencyCnt = Integer.parseInt(st.nextToken());
            hackedPCNo = Integer.parseInt(st.nextToken());

            PCList = new ArrayList<>();

            for (int idx = 0; idx <= PCCnt; idx++) {
                PCList.add(new ArrayList<>());
            }

            for (int idx = 0; idx < dependencyCnt; idx++) {
                st = new StringTokenizer(br.readLine().trim());
                int to = Integer.parseInt(st.nextToken());
                int from = Integer.parseInt(st.nextToken());
                int time = Integer.parseInt(st.nextToken());

                PCList.get(from).add(new Dependency(to, time));
            }

            System.out.println(dijkstra(hackedPCNo));
        }
    }
}