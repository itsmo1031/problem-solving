import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numCnt = Integer.parseInt(br.readLine().trim());
        int[] num = new int[numCnt];

        for (int idx = 0; idx < numCnt; idx++) {
            num[idx] = Integer.parseInt(br.readLine().trim());
        }

        int gcdNum = Math.abs(num[1] - num[0]);

        for (int idx = 2; idx < numCnt; idx++) {
            gcdNum = gcd(gcdNum, Math.abs(num[idx] - num[idx - 1]));
        }

        List<Integer> divisor = new ArrayList<>();

        for (int idx = 2; idx <= Math.sqrt(gcdNum); idx++) {
            if (gcdNum % idx == 0) {
                divisor.add(idx);
                if (idx * idx != gcdNum) {
                    divisor.add(gcdNum / idx);
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        Collections.sort(divisor);

        for (int div : divisor) {
            sb.append(div).append(" ");
        }

        sb.append(gcdNum);

        System.out.println(sb);
    }
}