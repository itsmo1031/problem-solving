import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int X, Y, Z;

    static int odd(int x, int y) {
        return (int) (((double) y * 100) / x);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        Z = odd(X, Y);

        int lo = 1;
        int hi = 1_000_000_000;

        while (lo <= hi) {
            int mid = (lo + hi) / 2;

            if (decide(mid)) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        System.out.println(lo <= 1_000_000_000 ? lo : -1);
    }

    static boolean decide(int mid) {
        return Z < odd(X + mid, Y + mid);
    }
}