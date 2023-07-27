import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] arr;
    static int N, S;

    static int getShortestSum() {
        int minNum = Integer.MAX_VALUE;
        int sp = 0, ep = 1;
        int currentSum = arr[sp];
        while (sp < ep) {
            if (currentSum < S) {
                if (ep == N)
                    break;
                currentSum += arr[ep++];
            } else {
                minNum = Math.min(minNum, ep - sp);
                currentSum -= arr[sp++];
            }
        }

        return minNum == Integer.MAX_VALUE ? 0 : minNum;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(getShortestSum());
    }
}