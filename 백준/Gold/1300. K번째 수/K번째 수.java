import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long N = Integer.parseInt(br.readLine().trim());
        int target = Integer.parseInt(br.readLine().trim());

        long lo = 1;
        long hi = N * N;

        while (lo <= hi) {
            long mid = lo + (hi - lo) / 2;

            if (decide(N, mid, target)) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        System.out.println(lo);
    }

    static boolean decide(long N, long mid, int target) {
        long row = N - 1;
        long col = 0;
        long cnt = 0;

        while (row >= 0 && col < N) {
            if ((row + 1) * (col + 1) <= mid) {
                col += 1;
            } else {
                row -= 1;
                cnt += col;
            }
        }

        cnt += (row + 1) * col;

        return cnt >= target;
    }
}