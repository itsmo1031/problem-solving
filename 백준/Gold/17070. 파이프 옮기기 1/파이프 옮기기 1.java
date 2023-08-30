import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static final int HORIZONTAL = 0, VERTICAL = 1, DIAGONAL = 2;
    static int mapSize;
    static int[][] map;
    static int[][][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        mapSize = Integer.parseInt(br.readLine().trim());
        map = new int[mapSize + 2][mapSize + 2];
        dp = new int[mapSize + 2][mapSize + 2][3];

        dp[1][2][HORIZONTAL] = 1;

        for (int row = 1; row <= mapSize; row++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int col = 1; col <= mapSize; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(push());
    }

    static int push() {
        for (int row = 1; row <= mapSize; row++) {
            for (int col = 1; col <= mapSize; col++) {
                int[] now = dp[row][col];
                if (map[row][col + 1] == 0) {
                    dp[row][col + 1][HORIZONTAL] += now[HORIZONTAL] + now[DIAGONAL];
                }
                if (map[row + 1][col] == 0) {
                    dp[row + 1][col][VERTICAL] += now[VERTICAL] + now[DIAGONAL];
                }
                if (map[row][col + 1] == 0 && map[row + 1][col] == 0 && map[row + 1][col + 1] == 0) {
                    dp[row + 1][col + 1][DIAGONAL] += now[VERTICAL] + now[HORIZONTAL] + now[DIAGONAL];
                }
            }
        }

        int result = 0;

        for (int res : dp[mapSize][mapSize]) {
            result += res;
        }

        return result;
    }
}
