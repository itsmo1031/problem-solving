import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int peopleCnt, target;
    static List<List<Integer>> graph;

    static class Person {
        int num, cnt;

        public Person(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        peopleCnt = Integer.parseInt(st.nextToken());
        target = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();

        for (int idx = 0; idx < peopleCnt; idx++) {
            graph.add(new ArrayList<>());
        }

        for (int idx = 0; idx < peopleCnt; idx++) {
            int next = Integer.parseInt(br.readLine().trim());

            graph.get(idx).add(next);
        }

        System.out.println(doGame());
    }

    static int doGame() {
        Queue<Person> q = new ArrayDeque<>();
        q.offer(new Person(0, 0));

        while (!q.isEmpty()) {
            Person now = q.poll();

            if (now.num == target)
                return now.cnt;

            if (now.cnt > peopleCnt)
                break;

            for (int next : graph.get(now.num)) {
                q.offer(new Person(next, now.cnt + 1));
            }
        }

        return -1;
    }
}