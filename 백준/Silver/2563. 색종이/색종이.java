import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        boolean[][] paper = new boolean[101][101];

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine().trim());
        int answer = 0;

        for (int idx = 0; idx < N; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            int startX = Integer.parseInt(st.nextToken());
            int startY = Integer.parseInt(st.nextToken());

            for (int row = startX; row < startX + 10; row++) {
                for (int col = startY; col < startY + 10; col++) {
                    if (!paper[row][col]) {
                        paper[row][col] = true;
                        answer += 1;
                    }
                }
            }
        }

        System.out.println(answer);
    }
}
