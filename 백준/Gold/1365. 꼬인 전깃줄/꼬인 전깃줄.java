import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int poleCnt;
    static int[] pole;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        poleCnt = Integer.parseInt(br.readLine().trim());

        pole = new int[poleCnt];

        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        for (int idx = 0; idx < poleCnt; idx++) {
            pole[idx] = Integer.parseInt(st.nextToken());
        }

        System.out.println(poleCnt - findLIS());
    }

    static int findLIS() {
        int len = 0;
        int[] memo = new int[poleCnt];
        memo[0] = pole[0];

        for (int idx = 1; idx < poleCnt; idx++) {
            if (memo[len] < pole[idx]) {
                memo[++len] = pole[idx];
            } else {
                int changeIdx = binarySearch(memo, 0, len, pole[idx]);
                memo[changeIdx] = pole[idx];
            }
        }

        return len + 1;
    }

    static int binarySearch(int[] arr, int left, int right, int target) {
        while (left < right) {
            int mid = (left + right) / 2;
            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return right;
    }
}