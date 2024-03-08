import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int length = Integer.parseInt(br.readLine().trim());

        arr = new int[length];

        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        for (int i = 0; i < length; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int targetLength = Integer.parseInt(br.readLine().trim());
        st = new StringTokenizer(br.readLine().trim());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < targetLength; i++) {
            if (check(Integer.parseInt(st.nextToken()))) {
                sb.append("1\n");
            } else {
                sb.append("0\n");
            }
            ;
        }

        System.out.println(sb);
    }

    static boolean check(int target) {
        int lo = 0;
        int hi = arr.length - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (arr[mid] == target) {
                return true;
            } else if (target < arr[mid]) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        return false;
    }
}