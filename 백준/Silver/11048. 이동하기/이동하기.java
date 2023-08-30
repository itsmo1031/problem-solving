import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        
        int mapRow = Integer.parseInt(st.nextToken());
        int mapCol = Integer.parseInt(st.nextToken());

        int[][] map = new int[mapRow + 1][mapCol + 1];
        
        for (int row = 1; row <= mapRow; row++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int col = 1; col <= mapCol; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        for (int row = 1; row <= mapRow; row++) {
            for (int col = 1; col <= mapCol; col++) {
                map[row][col] += Math.max(map[row - 1][col], map[row][col - 1]);
            }
        }

        System.out.println(map[mapRow][mapCol]);
    }
}
