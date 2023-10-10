import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int curveCnt;
    static boolean[][] dragonCurve;
    static final int[][] DIR = { { 0, 1 }, { -1, 0 }, { 0, -1 }, { 1, 0 } };
    static final int MAX_SIZE = 101;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        curveCnt = Integer.parseInt(br.readLine().trim());
        dragonCurve = new boolean[MAX_SIZE][MAX_SIZE];

        for (int idx = 0; idx < curveCnt; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            int gen = Integer.parseInt(st.nextToken());

            makeCurve(x, y, dir, gen);
        }

        System.out.println(count());
    }

    static int count() {
        int cnt = 0;

        for (int x = 0; x < MAX_SIZE - 1; x++) {
            for (int y = 0; y < MAX_SIZE - 1; y++) {
                if (dragonCurve[y][x] && isSquare(x, y)) {
                    cnt += 1;
                }
            }
        }

        return cnt;
    }

    static boolean isSquare(int x, int y) {
        return dragonCurve[y][x + 1] && dragonCurve[y + 1][x] && dragonCurve[y + 1][x + 1];
    }

    static void makeCurve(int x, int y, int dir, int gen) {
        List<Integer> dirList = new ArrayList<>();
        dirList.add(dir);

        for (int genIdx = 1; genIdx <= gen; genIdx++) {
            for (int idx = dirList.size() - 1; idx > -1; idx--) {
                dirList.add((dirList.get(idx) + 1) % 4);
            }
        }

        dragonCurve[y][x] = true;

        for (int direction : dirList) {
            x += DIR[direction][1];
            y += DIR[direction][0];

            if (x < 0 || x >= MAX_SIZE || y < 0 || y >= MAX_SIZE)
                continue;

            dragonCurve[y][x] = true;
        }
    }
}