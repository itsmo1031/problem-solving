import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int portCnt;
    static int[] port;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        portCnt = Integer.parseInt(br.readLine().trim());

        port = new int[portCnt];

        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        for (int idx = 0; idx < portCnt; idx++) {
            port[idx] = Integer.parseInt(st.nextToken());
        }

        System.out.println(findLIS());
    }

    static int findLIS() {
        int len = 0;
        int[] memo = new int[portCnt];
        memo[0] = port[0];

        for (int idx = 1; idx < portCnt; idx++) {
            if (memo[len] < port[idx]) {
                memo[++len] = port[idx];
            } else {
                int changeIdx = binarySearch(memo, 0, len, port[idx]);
                memo[changeIdx] = port[idx];
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