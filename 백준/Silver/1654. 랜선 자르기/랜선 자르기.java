import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int count, target;
    static int[] cables;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        count = Integer.parseInt(st.nextToken());
        target = Integer.parseInt(st.nextToken());

        cables = new int[count];

        long lo = 1;
        long hi = Integer.MIN_VALUE;

        for (int i = 0; i < count; i++) {
            int next = Integer.parseInt(br.readLine().trim());
            cables[i] = next;
            if (next > hi) {
                hi = next;
            }
        }

        while (lo <= hi) {
            long mid = lo + (hi - lo) / 2;

            if (check(mid)) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        System.out.println(lo - 1);
    }

    static boolean check(long length) {
        long cnt = 0;

        for (int cable : cables) {
            cnt += cable / length;
            if (cnt >= target) {
                return true;
            }
        }

        return false;
    }
}