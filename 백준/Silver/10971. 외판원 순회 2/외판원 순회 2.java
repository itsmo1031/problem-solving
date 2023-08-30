import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int cityCnt;
    static int answer;
    static int[][] city;
    static boolean[] visited;

    static void trip(int startIdx, int currentIdx, int cost, int count) {
        if (count == cityCnt) {
            if (city[currentIdx][startIdx] != 0)
                answer = Math.min(answer, cost + city[currentIdx][startIdx]);
            return;
        }

        if (cost > answer)
            return;

        for (int idx = 0; idx < cityCnt; idx++) {
            int nextCost = city[currentIdx][idx];
            if (visited[idx] || nextCost == 0)
                continue;
            visited[idx] = true;
            trip(startIdx, idx, cost + nextCost, count + 1);
            visited[idx] = false;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        cityCnt = Integer.parseInt(br.readLine().trim());
        StringTokenizer st;
        city = new int[cityCnt][cityCnt];
        visited = new boolean[cityCnt];

        for (int row = 0; row < cityCnt; row++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int col = 0; col < cityCnt; col++) {
                city[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        answer = Integer.MAX_VALUE;

        for (int idx = 0; idx < cityCnt; idx++) {
            visited[idx] = true;
            trip(idx, idx, 0, 1);
            visited[idx] = false;
        }

        System.out.println(answer);
    }
}
