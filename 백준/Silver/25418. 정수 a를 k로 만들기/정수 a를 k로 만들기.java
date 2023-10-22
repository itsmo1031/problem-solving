import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        int a = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] dp = new int[k + 1];

        for (int idx = a + 1; idx <= k; idx++) {
            if (idx < a * 2 || idx % 2 == 1) {
                dp[idx] = dp[idx - 1] + 1;
            } else {
                dp[idx] = Math.min(dp[idx / 2], dp[idx - 1]) + 1;
            }
        }

        System.out.println(dp[k]);
    }
}