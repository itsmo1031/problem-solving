import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static int arrLen;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        arrLen = Integer.parseInt(br.readLine().trim());
        arr = new int[arrLen];

        String[] data = br.readLine().trim().split(" ");

        for (int idx = 0; idx < arrLen; idx++) {
            arr[idx] = Integer.parseInt(data[idx]);
        }

        System.out.println(findLIS());
    }

    static int findLIS() {
        int len = 0;
        int[] memo = new int[arrLen];
        memo[0] = arr[0];

        for (int idx = 0; idx < arrLen; idx++) {
            if (memo[len] < arr[idx]) {
                memo[++len] = arr[idx];
            } else {
                int changeIdx = binarySearch(memo, 0, len, arr[idx]);
                memo[changeIdx] = arr[idx];
            }
        }

        return len + 1;
    }

    static int binarySearch(int[] list, int start, int end, int target) {
        while (start < end) {
            int mid = (start + end) / 2;
            if (list[mid] < target) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }

        return end;
    }
}