import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static final int[][] D = { { 0, 1 }, { 1, 0 }, { -1, 0 }, { 0, -1 } };
    static int[][] map, dp;
    static int N, M;

    static int dfs(int x, int y) {
        if (x == N - 1 && y == M - 1)
            return 1;
        if (dp[x][y] != -1)
            return dp[x][y];

        dp[x][y] = 0;
        for (int[] d : D) {
            int dx = x + d[0];
            int dy = y + d[1];
            if (dx < 0 || dx >= N || dy < 0 || dy >= M)
                continue;
            if (map[dx][dy] < map[x][y]) {
                dp[x][y] += dfs(dx, dy);
            }
        }

        return dp[x][y];
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        dp = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;
            }
        }

        System.out.println(dfs(0, 0));
    }
}