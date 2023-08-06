import java.io.*;
import java.util.*;

public class Main {
    static class CCTV {
        private final int RIGHT = 0;
        private final int DOWN = 1;
        private final int LEFT = 2;
        private final int UP = 3;

        final int[][] DIR = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
        int[][] direction;
        int currentDirection;
        int directionCnt;
        int x;
        int y;

        public CCTV(int num, int x, int y) {
            this.directionCnt = setDirection(num);
            this.currentDirection = 0;
            this.x = x;
            this.y = y;
        }

        private int setDirection(int num) {
            switch (num) {
                case 1:
                    direction = new int[][] { { RIGHT }, { DOWN }, { LEFT }, { UP } };
                    break;
                case 2:
                    direction = new int[][] { { LEFT, RIGHT }, { UP, DOWN } };
                    break;
                case 3:
                    direction = new int[][] {
                            { UP, RIGHT },
                            { RIGHT, DOWN },
                            { DOWN, LEFT },
                            { LEFT, UP } };
                    break;
                case 4:
                    direction = new int[][] {
                            { LEFT, UP, RIGHT },
                            { UP, RIGHT, DOWN },
                            { RIGHT, DOWN, LEFT },
                            { DOWN, LEFT, UP } };
                    break;
                case 5:
                    direction = new int[][] { { UP, RIGHT, DOWN, LEFT } };
                    break;
                default:
                    break;
            }
            return direction.length;
        }

        public void rotate() {
            this.currentDirection = (this.currentDirection + 1) % directionCnt;
        }

        public void record(int[][] map) {
            for (int di : direction[currentDirection]) {
                int cnt = 1;
                while (true) {
                    int nx = this.x + DIR[di][0] * cnt;
                    int ny = this.y + DIR[di][1] * cnt;

                    if (nx < 0 || nx >= N || ny < 0 || ny >= M || map[nx][ny] == 6)
                        break;

                    if (map[nx][ny] == 0) {
                        map[nx][ny] = -1;
                    }

                    cnt += 1;
                }
            }
        }
    }

    static int N;
    static int M;
    static int[][] map;
    static int answer = Integer.MAX_VALUE;
    static List<CCTV> CCTVList;

    static void startRecord(int count, int start, int[][] tempMap) {
        if (count == CCTVList.size()) {
            answer = Math.min(answer, calcBlindSpot(tempMap));
            return;
        }
        for (int idx = start; idx < CCTVList.size(); idx++) {
            CCTV current = CCTVList.get(idx);
            for (int dir = 0; dir < current.directionCnt; dir++) {
                int[][] newMap = copyMap(tempMap);
                current.record(newMap);
                startRecord(count + 1, idx + 1, newMap);
                current.rotate();
            }
        }
    }

    static int[][] copyMap(int[][] original) {
        int[][] copy = new int[N][M];

        for (int idx = 0; idx < N; idx++) {
            copy[idx] = Arrays.copyOf(original[idx], M);
        }

        return copy;
    }

    static int calcBlindSpot(int[][] temp) {
        int cnt = 0;

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                if (temp[row][col] == 0)
                    cnt += 1;
            }
        }

        return cnt;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        CCTVList = new ArrayList<>();

        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int col = 0; col < M; col++) {
                int data = Integer.parseInt(st.nextToken());
                if (1 <= data && data <= 5)
                    CCTVList.add(new CCTV(data, row, col));
                map[row][col] = data;
            }
        }

        startRecord(0, 0, map);

        System.out.println(answer);
    }
}