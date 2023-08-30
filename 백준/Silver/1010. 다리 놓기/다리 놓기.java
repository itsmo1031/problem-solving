import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int total;
    static int select;
    static int[][] dp;

    static int choose(int times, int got) {
        if (times == total) {
            return got == select ? 1 : 0;
        }

        if (dp[times][got] != -1)
            return dp[times][got];

        return dp[times][got] = choose(times + 1, got) + choose(times + 1, got + 1);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int testCase = Integer.parseInt(br.readLine().trim());

        for (int tc = 0; tc < testCase; tc++) {
            st = new StringTokenizer(br.readLine().trim());
            select = Integer.parseInt(st.nextToken());
            total = Integer.parseInt(st.nextToken());

            dp = new int[total][total];

            for (int[] line : dp) {
                Arrays.fill(line, -1);
            }

            int answer = choose(0, 0);

            System.out.println(answer);
        }
    }
}
