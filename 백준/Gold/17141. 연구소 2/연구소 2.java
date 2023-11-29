import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static final int[][] DIR = { { 0, 1 }, { 1, 0 }, { -1, 0 }, { 0, -1 } };
    static final int EMPTY = 0, WALL = 1, VIRUS = 2;
    static int mapSize, virusCnt, answer;
    static int[][] lab;
    static List<Pos> virusList;

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
                int next = Integer.parseInt(st.nextToken());
                if (next == VIRUS) {
                    virusList.add(new Pos(row, col, 0));
                    next = EMPTY;
                }
                lab[row][col] = next;
            }
        }

        answer = Integer.MAX_VALUE;
        combination(new Pos[virusCnt], 0, 0);

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    static void combination(Pos[] selected, int select, int start) {
        if (select == virusCnt) {
            answer = Math.min(answer, simulate(selected));
            return;
        }

        for (int idx = start; idx < virusList.size(); idx++) {
            selected[select] = virusList.get(idx);
            combination(selected, select + 1, idx + 1);
        }
    }

    static int simulate(Pos[] selected) {
        int[][] visit = new int[mapSize][mapSize];
        for (int[] line : visit) {
            Arrays.fill(line, Integer.MAX_VALUE);
        }

        Queue<Pos> q = new ArrayDeque<>();

        for (Pos pos : selected) {
            q.offer(pos);
            visit[pos.r][pos.c] = pos.time;
        }

        int time = 0;

        while (!q.isEmpty()) {
            Pos now = q.poll();

            time = Math.max(time, now.time);

            for (int[] dir : DIR) {
                int nr = now.r + dir[0];
                int nc = now.c + dir[1];
                int nt = now.time + 1;

                if (nr < 0 || nr >= mapSize || nc < 0 || nc >= mapSize || lab[nr][nc] == WALL)
                    continue;

                if (nt < visit[nr][nc]) {
                    visit[nr][nc] = nt;
                    q.offer(new Pos(nr, nc, nt));
                }
            }
        }

        return isAvailable(visit) ? time : Integer.MAX_VALUE;
    }

    private static boolean isAvailable(int[][] visit) {
        for (int row = 0; row < mapSize; row++) {
            for (int col = 0; col < mapSize; col++) {
                if (lab[row][col] == EMPTY && visit[row][col] == Integer.MAX_VALUE) {
                    return false;
                }
            }
        }

        return true;
    }
}