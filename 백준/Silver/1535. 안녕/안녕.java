import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int peopleCnt;
    static int[] life, joy;
    static final int MAX_HEALTH = 100;
    static int[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        dp = new int[MAX_HEALTH + 1];

        peopleCnt = Integer.parseInt(br.readLine().trim());
        life = new int[peopleCnt];
        joy = new int[peopleCnt];

        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        for (int idx = 0; idx < peopleCnt; idx++) {
            life[idx] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine().trim());

        for (int idx = 0; idx < peopleCnt; idx++) {
            joy[idx] = Integer.parseInt(st.nextToken());
        }

        for (int idx = 0; idx < peopleCnt; idx++) {
            for (int lim = MAX_HEALTH; lim > life[idx]; lim--) {
                if (life[idx] <= lim) {
                    dp[lim] = Math.max(dp[lim - life[idx]] + joy[idx], dp[lim]);
                }
            }
        }
        System.out.println(dp[MAX_HEALTH]);
    }
}