import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int[] num;
    static int length;
    static int[] result;
    static int limit;

    static void permutation(int cnt) {
        if (cnt == limit) {
            for (int idx = 0; idx < limit; idx++) {
                sb.append(result[idx]);
                if (idx < limit - 1)
                    sb.append(" ");
            }
            sb.append("\n");
            
            return;
        }

        for (int n : num) {
            result[cnt] = n;
            permutation(cnt + 1);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        length = Integer.parseInt(st.nextToken());
        num = new int[length];
        for (int index = 0; index < length; index++) {
            num[index] = index + 1;
        }
        limit = Integer.parseInt(st.nextToken());
        result = new int[limit];

        permutation(0);

        System.out.print(sb);
    }
}