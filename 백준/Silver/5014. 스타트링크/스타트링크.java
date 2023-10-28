import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int height;
    static int[] button;

    static class Elevator {
        int floor, count;

        public Elevator(int floor, int count) {
            this.floor = floor;
            this.count = count;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        height = Integer.parseInt(st.nextToken());
        int current = Integer.parseInt(st.nextToken());
        int startLink = Integer.parseInt(st.nextToken());
        button = new int[2];
        button[0] = Integer.parseInt(st.nextToken());
        button[1] = -Integer.parseInt(st.nextToken());

        int result = bfs(current, startLink);

        System.out.println(result == -1 ? "use the stairs" : result);
    }

    static int bfs(int start, int dest) {
        Queue<Elevator> q = new ArrayDeque<>();
        boolean[] visited = new boolean[height + 1];
        visited[start] = true;
        q.offer(new Elevator(start, 0));

        while (!q.isEmpty()) {
            Elevator now = q.poll();

            if (now.floor == dest)
                return now.count;

            for (int btn : button) {
                int nf = now.floor + btn;

                if (nf < 1 || nf > height || visited[nf])
                    continue;

                visited[nf] = true;

                q.offer(new Elevator(nf, now.count + 1));
            }
        }

        return -1;
    }
}