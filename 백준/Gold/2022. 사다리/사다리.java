import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static double x, y, c;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        x = Double.parseDouble(st.nextToken());
        y = Double.parseDouble(st.nextToken());
        c = Double.parseDouble(st.nextToken());

        System.out.printf("%.3f\n", getDistance());
    }

    static double getDistance() {
        double start = 0;
        double end = Math.min(x, y);

        while (end - start >= 0.001) {
            double mid = (start + end) / 2;

            double h1 = Math.sqrt(Math.pow(x, 2) - Math.pow(mid, 2));
            double h2 = Math.sqrt(Math.pow(y, 2) - Math.pow(mid, 2));

            double target = (h1 * h2) / (h1 + h2);

            if (c < target) {
                start = mid;
            } else {
                end = mid;
            }
        }

        return end;
    }
}