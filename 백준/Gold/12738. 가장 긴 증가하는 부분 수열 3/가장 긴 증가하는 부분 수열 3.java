import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int size;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        size = Integer.parseInt(br.readLine().trim());

        arr = new int[size];

        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        for (int idx = 0; idx < size; idx++) {
            arr[idx] = Integer.parseInt(st.nextToken());
        }

        System.out.println(findLIS());
    }

    static int findLIS() {
        int len = 0;
        int[] memo = new int[size];
        memo[0] = arr[0];

        for (int idx = 1; idx < size; idx++) {
            if (memo[len] < arr[idx]) {
                memo[++len] = arr[idx];
            } else {
                memo[binarySearch(memo, 0, len, arr[idx])] = arr[idx];
            }
        }

        return len + 1;
    }

    static int binarySearch(int[] list, int start, int end, int target) {
        int mid = 0;
        while (start < end) {
            mid = (start + end) / 2;
            if (list[mid] < target) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return end;
    }
}