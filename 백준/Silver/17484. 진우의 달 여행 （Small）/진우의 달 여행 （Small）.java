import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int spaceRow, spaceCol;
    static final int DIST = 3;
    static int[][][] cost;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        spaceRow = Integer.parseInt(st.nextToken());
        spaceCol = Integer.parseInt(st.nextToken());
        cost = new int[spaceRow][spaceCol + 2][DIST];

        for (int row = 0; row < spaceRow; row++) {
            for (int col = 0; col < spaceCol + 2; col++) {
                Arrays.fill(cost[row][col], (int) 1e9);
            }
        }

        for (int row = 0; row < spaceRow; row++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int col = 1; col <= spaceCol; col++) {
                int dist = Integer.parseInt(st.nextToken());
                for (int idx = 0; idx < DIST; idx++) {
                    cost[row][col][idx] = dist;
                }
            }
        }

        for (int row = 1; row < spaceRow; row++) {
            for (int col = 1; col <= spaceCol; col++) {
                cost[row][col][0] += Math.min(cost[row - 1][col + 1][1], cost[row - 1][col + 1][2]);
                cost[row][col][1] += Math.min(cost[row - 1][col][0], cost[row - 1][col][2]);
                cost[row][col][2] += Math.min(cost[row - 1][col - 1][0], cost[row - 1][col - 1][1]);
            }
        }

        int answer = Integer.MAX_VALUE;

        for (int col = 1; col <= spaceCol; col++) {
            for (int result : cost[spaceRow - 1][col]) {
                answer = Math.min(answer, result);
            }
        }

        System.out.println(answer);
    }
}