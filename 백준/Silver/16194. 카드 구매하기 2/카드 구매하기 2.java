import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] dp, cardPack;
    static int cardCnt;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        cardCnt = Integer.parseInt(br.readLine().trim());

        dp = new int[cardCnt + 1];
        cardPack = new int[cardCnt + 1];

        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        for (int idx = 1; idx <= cardCnt; idx++) {
            cardPack[idx] = Integer.parseInt(st.nextToken());
            dp[idx] = Integer.MAX_VALUE;
        }

        for (int cardIdx = 1; cardIdx <= cardCnt; cardIdx++) {
            int current = cardPack[cardIdx];
            for (int idx = cardIdx; idx <= cardCnt; idx++) {
                dp[idx] = Math.min(dp[idx], dp[idx - cardIdx] + current);
            }
        }

        System.out.println(dp[cardCnt]);
    }
}