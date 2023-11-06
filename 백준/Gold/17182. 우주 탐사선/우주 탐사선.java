import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int planetCnt;
    static int start;
    static int answer;
    static int[][] planet;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        planetCnt = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(st.nextToken());

        planet = new int[planetCnt][planetCnt];

        for (int row = 0; row < planetCnt; row++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int col = 0; col < planetCnt; col++) {
                planet[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        for (int k = 0; k < planetCnt; k++) {
            for (int i = 0; i < planetCnt; i++) {
                for (int j = 0; j < planetCnt; j++) {
                    planet[i][j] = Math.min(planet[i][j], planet[i][k] + planet[k][j]);
                }
            }
        }

        answer = Integer.MAX_VALUE;
        visited = new boolean[planetCnt];
        visited[start] = true;
        explore(1, start, 0);
        System.out.println(answer);
    }

    static void explore(int count, int current, int dist) {
        if (count == visited.length) {
            answer = Math.min(answer, dist);
            return;
        }

        if (answer < dist)
            return;

        for (int idx = 0; idx < planetCnt; idx++) {
            if (visited[idx] || current == idx)
                continue;
            visited[idx] = true;
            explore(count + 1, idx, dist + planet[current][idx]);
            visited[idx] = false;
        }
    }
}