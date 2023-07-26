import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] num = new int[N];
        long[][] dp = new long[N - 1][21];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }
        dp[0][num[0]] = 1;

        for (int i = 1; i < N - 1; i++) {
            for (int j = 0; j < 21; j++) {
                if (dp[i - 1][j] == 0)
                    continue;
                int next = j + num[i];
                if (next >= 0 && next <= 20)
                    dp[i][next] += dp[i - 1][j];

                next = j - num[i];
                if (next >= 0 && next <= 20)
                    dp[i][next] += dp[i - 1][j];
            }
        }

        System.out.println(dp[N - 2][num[N - 1]]);
    }
}