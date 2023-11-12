import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int length;
    static int[] maze;

    static class Pos {
        int idx, depth;

        public Pos(int idx, int depth) {
            this.idx = idx;
            this.depth = depth;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        length = Integer.parseInt(br.readLine().trim());
        maze = new int[length];

        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        for (int idx = 0; idx < length; idx++) {
            maze[idx] = Integer.parseInt(st.nextToken());
        }

        System.out.println(bfs());
    }

    static int bfs() {
        boolean[] visited = new boolean[length];
        visited[0] = true;
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(new Pos(0, 0));

        while (!q.isEmpty()) {
            Pos now = q.poll();

            if (now.idx == length - 1) {
                return now.depth;
            }

            for (int idx = now.idx + maze[now.idx]; idx > 0; idx--) {
                if (idx >= length || visited[idx])
                    continue;

                visited[idx] = true;
                q.offer(new Pos(idx, now.depth + 1));
            }
        }

        return -1;
    }
}