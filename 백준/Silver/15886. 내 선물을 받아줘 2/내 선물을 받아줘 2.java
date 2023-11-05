import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
    static int mapSize;
    static char[] map;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        mapSize = Integer.parseInt(br.readLine().trim());
        map = br.readLine().trim().toCharArray();

        visited = new boolean[mapSize];

        int answer = 0;

        for (int idx = 0; idx < mapSize; idx++) {
            if (map[idx] == 'E' && !visited[idx]) {
                answer += bfs(idx);
            }
        }

        System.out.println(answer);
    }

    static int bfs(int idx) {
        visited[idx] = true;
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(idx);

        while (!q.isEmpty()) {
            int now = q.poll();
            int next;

            if (map[now] == 'E') {
                next = now + 1;
            } else {
                next = now - 1;
            }

            if (next < 0 || next >= mapSize || visited[next])
                continue;

            visited[next] = true;
            q.offer(next);
        }

        return 1;
    }
}