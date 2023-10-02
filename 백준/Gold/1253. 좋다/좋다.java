import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int arrLen;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        arrLen = Integer.parseInt(br.readLine().trim());
        arr = new int[arrLen];

        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        for (int idx = 0; idx < arrLen; idx++) {
            arr[idx] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        System.out.println(findGoodNum());
    }

    static int findGoodNum() {
        if (arrLen < 3)
            return 0;

        int goodNum = 0;

        for (int idx1 = 0; idx1 < arrLen; idx1++) {
            for (int idx2 = 0; idx2 < arrLen; idx2++) {
                if (idx1 == idx2)
                    continue;
                int target = arr[idx1] - arr[idx2];
                if (isValid(target, idx1, idx2)) {
                    goodNum += 1;
                    break;
                }
            }
        }

        return goodNum;
    }

    private static boolean isValid(int target, int idx1, int idx2) {
        int lowerBound = bisect_left(arr, 0, arrLen, target);
        int upperBound = bisect_right(arr, 0, arrLen, target);

        for (int idx = lowerBound; idx < upperBound; idx++) {
            if (idx != idx1 && idx != idx2)
                return true;
        }

        return false;
    }

    static int bisect_left(int[] array, int left, int right, int target) {
        while (left < right) {
            int mid = (left + right) / 2;
            if (array[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return right;
    }

    static int bisect_right(int[] array, int left, int right, int target) {
        while (left < right) {
            int mid = (left + right) / 2;
            if (array[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return right;
    }
}