import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int friendCnt;
    static List<List<Integer>> friends;

    static class Friend {
        int num, depth;

        public Friend(int num, int depth) {
            this.num = num;
            this.depth = depth;
        }

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        friendCnt = Integer.parseInt(br.readLine().trim());
        friends = new ArrayList<>();

        for (int idx = 0; idx <= friendCnt; idx++) {
            friends.add(new ArrayList<>());
        }

        int connections = Integer.parseInt(br.readLine().trim());
        StringTokenizer st;

        for (int idx = 0; idx < connections; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            friends.get(from).add(to);
            friends.get(to).add(from);
        }

        System.out.println(bfs());

    }

    static int bfs() {
        boolean[] visited = new boolean[friendCnt + 1];
        visited[1] = true;
        Queue<Friend> q = new ArrayDeque<>();
        q.offer(new Friend(1, 0));

        int count = -1;

        while (!q.isEmpty()) {
            Friend now = q.poll();

            if (now.depth > 2) {
                return count;
            }

            count += 1;

            for (int next : friends.get(now.num)) {
                if (visited[next])
                    continue;

                visited[next] = true;
                q.offer(new Friend(next, now.depth + 1));
            }
        }

        return count;
    }
}