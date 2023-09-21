import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static final int MAX_POS = 100_001;
    static int hider, seeker;
    static int[] history;

    static class Person {
        int pos;
        int time;

        public Person(int pos, int time) {
            this.pos = pos;
            this.time = time;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        seeker = Integer.parseInt(st.nextToken());
        hider = Integer.parseInt(st.nextToken());
        history = new int[MAX_POS];
        Arrays.fill(history, -1);

        int answer = hideNSeek();
        StringBuilder sb = new StringBuilder();

        sb.append(answer).append("\n");

        Deque<Integer> deque = new ArrayDeque<>();

        deque.offer(hider);
        for (int idx = 0; idx < answer; idx++) {
            deque.offerFirst(history[deque.peekFirst()]);
        }

        for (int elem : deque) {
            sb.append(elem).append(" ");
        }

        System.out.println(sb);
    }

    static List<Integer> copy(List<Integer> original) {
        List<Integer> copied = new ArrayList<>();

        for (int elem : original) {
            copied.add(elem);
        }

        return copied;
    }

    static int hideNSeek() {
        boolean[] visited = new boolean[MAX_POS];
        Queue<Person> q = new ArrayDeque<>();
        q.offer(new Person(seeker, 0));
        visited[seeker] = true;

        while (!q.isEmpty()) {
            Person now = q.poll();

            if (now.pos == hider) {
                return now.time;
            }

            for (int next : new int[] { 1, -1, now.pos }) {
                int np = now.pos + next;

                if (np < 0 || np >= MAX_POS || visited[np])
                    continue;

                visited[np] = true;
                history[np] = now.pos;
                q.offer(new Person(np, now.time + 1));
            }
        }

        return -1;
    }
}