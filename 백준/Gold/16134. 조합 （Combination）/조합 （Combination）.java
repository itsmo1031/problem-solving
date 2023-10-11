import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static final int PRIME = (int) 1e9 + 7;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        int n = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        System.out.println(getCombinationCnt(n, r));
    }

    static long getCombinationCnt(int n, int r) {
        if (r == 0)
            return 1;

        long[] factorial = new long[n + 1];
        factorial[0] = 1;

        for (int idx = 1; idx <= n; idx++) {
            factorial[idx] = factorial[idx - 1] * idx % PRIME;
        }

        return ((factorial[n] * power(factorial[r], PRIME - 2) % PRIME) * (power(factorial[n - r], PRIME - 2) % PRIME))
                % PRIME;
    }

    static long power(long a, long b) {
        long res = 1L;
        a %= PRIME;

        while (b > 0) {
            if (b % 2 == 1) {
                res = (res * a) % PRIME;
            }
            b = b >> 1;
            a = (a * a) % PRIME;
        }

        return res;
    }
}