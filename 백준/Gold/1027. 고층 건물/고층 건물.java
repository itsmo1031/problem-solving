import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int[] buildings;
    private static int N;

    public static double[] getSlopes(int pos) {
        double[] slopes = new double[N];
        for (int i = 0; i < N; i++) {
            if (pos == i)
                continue;
            slopes[i] = (double) (buildings[pos] - buildings[i]) / (pos - i);
        }
        return slopes;
    }

    public static int getPossible(int now, double[] slopes) {
        int count = 0;
        for (int i = now - 1; i > -1; i--) {
            boolean possible = true;
            for (int j = i + 1; j < now; j++) {
                if (slopes[i] >= slopes[j]) {
                    possible = false;
                    break;
                }
            }
            if (possible)
                count += 1;
        }
        for (int i = now + 1; i < N; i++) {
            boolean possible = true;
            for (int j = i - 1; j > now; j--) {
                if (slopes[i] <= slopes[j]) {
                    possible = false;
                    break;
                }
            }
            if (possible)
                count += 1;
        }
        return count;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        buildings = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            buildings[i] = Integer.parseInt(st.nextToken());
        }
        int result = 0;
        for (int now = 0; now < N; now++) {
            double[] slopes = getSlopes(now);
            result = Integer.max(result, getPossible(now, slopes));
        }
        System.out.println(result);
    }
}