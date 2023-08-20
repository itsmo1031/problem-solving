import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[] array;
    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine().trim());

        array = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        for (int idx = 0; idx < N; idx++) {
            array[idx] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(array);

        int answer = Integer.MIN_VALUE;

        do {
            answer = Math.max(answer, sum());
        } while (nextPermutation(array));

        System.out.println(answer);
    }

    static int sum() {
        int now = array[0];
        int result = 0;

        for (int idx = 1; idx < N; idx++) {
            result += Math.abs(now - array[idx]);
            now = array[idx];
        }

        return result;
    }

    static boolean nextPermutation(int[] arr) {
        int top = arr.length - 1;
        while (top > 0 && arr[top - 1] >= arr[top])
            --top;

        if (top == 0)
            return false;

        int target = arr.length - 1;
        while (arr[top - 1] >= arr[target])
            --target;

        int temp = arr[target];
        arr[target] = arr[top - 1];
        arr[top - 1] = temp;

        Arrays.sort(arr, top, arr.length);

        return true;
    }
}