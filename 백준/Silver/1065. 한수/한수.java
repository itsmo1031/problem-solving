import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int num = Integer.parseInt(br.readLine().trim());

        int count = 0;
        for (int i = 1; i <= num; i++) {
            if (isHan(i)) {
                count += 1;
            }
        }
        System.out.println(count);
    }

    private static boolean isHan(int i) {
        char[] arr = String.valueOf(i).toCharArray();

        if (arr.length <= 2)
            return true;

        else if (arr.length == 3) {
            if (arr[1] - arr[0] == arr[2] - arr[1]) {
                return true;
            }
        }

        return false;
    }

}