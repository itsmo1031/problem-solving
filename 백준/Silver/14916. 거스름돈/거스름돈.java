import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int leftover = Integer.parseInt(br.readLine().trim());
        int result = 0;

        while (leftover >= 2) {
            if (leftover % 5 == 0) {
                result += leftover / 5;
                leftover = 0;
            } else {
                result += 1;
                leftover -= 2;
            }
        }

        System.out.println(leftover == 0 ? result : -1);
    }
}