import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static int[] VIP;
    static int seatCnt, VIPCnt;
    static int[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        seatCnt = Integer.parseInt(br.readLine().trim());
        dp = new int[seatCnt + 1];
        dp[0] = 1;
        dp[1] = 1;

        VIPCnt = Integer.parseInt(br.readLine().trim());

        int index = 1;
        for (int vip = 0; vip < VIPCnt; vip++) {
            int nextVIP = Integer.parseInt(br.readLine().trim());
            dp[index] = dp[index - 1];
            for (int idx = index + 1; idx < nextVIP; idx++) {
                dp[idx] = dp[idx - 1] + dp[idx - 2];
            }
            dp[nextVIP] = dp[nextVIP - 1];
            index = nextVIP + 1;
        }

        if (index <= seatCnt) {
            dp[index] = dp[index - 1];
            for (int idx = index + 1; idx <= seatCnt; idx++) {
                dp[idx] = dp[idx - 1] + dp[idx - 2];
            }
        }

        System.out.println(dp[seatCnt]);
    }
}