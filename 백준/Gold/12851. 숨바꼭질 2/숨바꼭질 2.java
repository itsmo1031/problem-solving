import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int seeker, hider;
    static final int MAX_POS = 100001;

    static class Person {
        int pos, time;

        public Person(int pos, int time) {
            this.pos = pos;
            this.time = time;
        }
    }

    static class Result {
        int time, cnt;

        public Result(int time, int cnt) {
            this.time = time;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        seeker = Integer.parseInt(st.nextToken());
        hider = Integer.parseInt(st.nextToken());

        Result result = hideNSeek();

        System.out.println(result.time);
        System.out.println(result.cnt);
    }

    static Result hideNSeek() {
        int[] visited = new int[MAX_POS];
        Queue<Person> q = new ArrayDeque<>();
        Arrays.fill(visited, Integer.MAX_VALUE);
        visited[seeker] = 0;
        q.offer(new Person(seeker, 0));
        int minTime = Integer.MAX_VALUE;
        int count = 0;

        while (!q.isEmpty()) {
            Person now = q.poll();
            if (now.pos == hider) {
                if (now.time < minTime) {
                    count = 1;
                    minTime = now.time;
                } else if (now.time == minTime)
                    count += 1;
                continue;
            }

            for (int next : new int[] { 1, -1, now.pos }) {
                int np = now.pos + next;
                int nt = now.time + 1;

                if (np < 0 || np >= MAX_POS || visited[np] < nt)
                    continue;

                visited[np] = nt;
                q.offer(new Person(np, nt));
            }
        }

        return new Result(minTime, count);
    }
}