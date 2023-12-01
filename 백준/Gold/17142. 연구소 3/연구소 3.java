import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static final int EMPTY = 0, WALL = 1, VIRUS = 2;
    static final int[][] DIR = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
    static final int MAX = Integer.MAX_VALUE;
    static List<Pos> virusList;
    static int mapSize, virusCnt;
    static int[][] lab;

    static class Pos {
        int r, c, time;

        public Pos(int r, int c, int time) {
            this.r = r;
            this.c = c;
            this.time = time;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        mapSize = Integer.parseInt(st.nextToken());
        virusCnt = Integer.parseInt(st.nextToken());

        lab = new int[mapSize][mapSize];
        virusList = new ArrayList<>();

        for (int row = 0; row < mapSize; row++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int col = 0; col < mapSize; col++) {
                int data = Integer.parseInt(st.nextToken());
                if (data == VIRUS) {
                    virusList.add(new Pos(row, col, 0));
                }
                lab[row][col] = data;
            }
        }

        int[] selected = new int[virusList.size()];
        for (int idx = virusList.size() - 1; idx > virusList.size() - 1 - virusCnt; idx--) {
            selected[idx] = 1;
        }

        int answer = MAX;

        do {
            answer = Math.min(spread(selected), answer);
            // System.out.println(answer);
            // System.out.println(Arrays.toString(selected));
            // System.out.println("---");
        } while (nextPermutation(selected));

        System.out.println(answer == MAX ? -1 : answer);
    }

    static boolean nextPermutation(int[] arr) {
        int N = arr.length;
        int top = N - 1;

        while (top > 0 && arr[top - 1] >= arr[top])
            --top;

        if (top == 0)
            return false;

        int target = N - 1;
        while (arr[top - 1] >= arr[target])
            --target;

        int temp = arr[top - 1];
        arr[top - 1] = arr[target];
        arr[target] = temp;

        Arrays.sort(arr, top, arr.length);

        return true;
    }

    static int spread(int[] selected) {
        int[][] visited = new int[mapSize][mapSize];
        Queue<Pos> q = new ArrayDeque<>();
        int time = 0;

        for (int[] line : visited) {
            Arrays.fill(line, MAX);
        }

        for (int idx = 0; idx < virusList.size(); idx++) {
            if (selected[idx] == 1) {
                Pos virus = virusList.get(idx);
                q.offer(virus);
                visited[virus.r][virus.c] = 0;
            }
        }

        if (isFull(visited))
            return 0;

        while (!q.isEmpty()) {
            Pos now = q.poll();

            time = Math.max(time, now.time);

            for (int[] dir : DIR) {
                int nr = now.r + dir[0];
                int nc = now.c + dir[1];

                if (nr < 0 || nr >= mapSize || nc < 0 || nc >= mapSize || lab[nr][nc] == WALL) {
                    continue;
                }

                int nt = now.time + 1;

                if (nt < visited[nr][nc]) {
                    visited[nr][nc] = nt;
                    if (isFull(visited))
                        return nt;
                    q.offer(new Pos(nr, nc, nt));
                }
            }
        }

        return MAX;
    }

    static boolean isFull(int[][] visited) {

        for (int row = 0; row < mapSize; row++) {
            for (int col = 0; col < mapSize; col++) {
                if (lab[row][col] == EMPTY && visited[row][col] == MAX) {
                    return false;
                }
            }
        }

        return true;
    }

}