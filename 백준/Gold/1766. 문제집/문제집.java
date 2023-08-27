import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int questionCnt;
    static int[] indegree;
    static boolean[] visited;
    static List<List<Integer>> question;
    static PriorityQueue<Integer> pq;
    static StringBuilder sb;

    static void solve() {
        while (!pq.isEmpty()) {
            int now = pq.poll();

            sb.append(now + " ");

            for (int next : question.get(now)) {
                if (!visited[next] && --indegree[next] == 0) {
                    visited[next] = true;
                    pq.offer(next);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        sb = new StringBuilder();
        questionCnt = Integer.parseInt(st.nextToken());
        indegree = new int[questionCnt + 1];
        visited = new boolean[questionCnt + 1];

        int infoCnt = Integer.parseInt(st.nextToken());

        question = new ArrayList<>();

        for (int idx = 0; idx <= questionCnt; idx++) {
            question.add(new ArrayList<>());
        }

        for (int idx = 0; idx < infoCnt; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            question.get(from).add(to);
            indegree[to] += 1;
        }

        pq = new PriorityQueue<>();

        for (int idx = 1; idx <= questionCnt; idx++) {
            if (indegree[idx] == 0) {
                visited[idx] = true;
                pq.offer(idx);
            }
        }

        solve();

        System.out.println(sb);
    }
}