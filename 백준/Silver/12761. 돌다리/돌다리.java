import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static final int MAX_POS = 100001;
    static boolean[] visited = new boolean[MAX_POS];
    static int[] pogo;
    static int start, end;

    static class Pos {
        int now;
        int time;

        public Pos(int now, int time) {
            this.now = now;
            this.time = time;
        }

        int[] next(int[] flags) {
            int[] result = new int[8];
            Arrays.fill(result, this.now);
            int idx = 0;

            for (int flag : flags) {
                result[idx++] += flag;
                result[idx++] -= flag;
                result[idx++] *= flag;
            }

            result[idx++] += 1;
            result[idx] -= 1;

            return result;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        pogo = new int[2];
        pogo[0] = Integer.parseInt(st.nextToken());
        pogo[1] = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        System.out.println(bfs());
    }

    static int bfs() {
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(new Pos(start, 0));
        visited[start] = true;

        while (!q.isEmpty()) {
            Pos now = q.poll();

            if (now.now == end)
                return now.time;

            for (int np : now.next(pogo)) {
                if (np < 0 || np >= MAX_POS || visited[np])
                    continue;
                visited[np] = true;
                q.offer(new Pos(np, now.time + 1));
            }
        }

        return -1;
    }
}