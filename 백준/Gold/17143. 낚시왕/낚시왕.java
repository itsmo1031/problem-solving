import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[][] DIR = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };
    static int seaRow, seaCol;
    static Queue<Shark>[][] sea;

    static class Shark {
        int x, y;
        int speed;
        int dir;
        int size;

        public Shark(int x, int y, int speed, int dir, int size) {
            this.x = x;
            this.y = y;
            this.speed = speed;
            this.dir = dir;
            this.size = size;
        }

        void move() {

            int nx = x + DIR[dir][0] * speed;
            int ny = y + DIR[dir][1] * speed;

            while (nx < 0 || nx >= seaRow || ny < 0 || ny >= seaCol) {
                if (nx < 0) { // x가 0보다 작은 경우 위쪽으로 이동한 것
                    dir = flip();
                    nx = -nx;
                }
                if (nx >= seaRow) {
                    dir = flip();
                    nx = (seaRow - 1) - (nx - (seaRow - 1));
                }

                if (ny < 0) {
                    dir = flip();
                    ny = -ny;
                }
                if (ny >= seaCol) {
                    dir = flip();
                    ny = (seaCol - 1) - (ny - (seaCol - 1));
                }
            }

            x = nx;
            y = ny;

            sea[x][y].offer(this);
        }

        int flip() {
            switch (dir) {
                case 0:
                    return 1;
                case 1:
                    return 0;
                case 2:
                    return 3;
                case 3:
                    return 2;
                default:
                    return dir;
            }
        }

        static Shark max(Shark s1, Shark s2) {
            return s1.size > s2.size ? s1 : s2;
        }

        static void fight(Queue<Shark> queue) {
            Shark winner = queue.poll();

            while (!queue.isEmpty()) {
                Shark now = queue.poll();
                winner = Shark.max(winner, now);
            }

            queue.offer(winner);
        }
    }

    static void moveAll() {
        List<Shark> sharkList = new ArrayList<>();

        for (int row = 0; row < seaRow; row++) {
            for (int col = 0; col < seaCol; col++) {
                if (sea[row][col].size() != 0) {
                    sharkList.add(sea[row][col].poll());
                }
            }
        }

        sharkList.forEach(shark -> shark.move());

        for (int row = 0; row < seaRow; row++) {
            for (int col = 0; col < seaCol; col++) {
                if (sea[row][col].size() > 1) {
                    Shark.fight(sea[row][col]);
                }
            }
        }
    }

    static int catchShark(int col) {
        for (int row = 0; row < seaRow; row++) {
            if (sea[row][col].size() != 0) {
                Shark target = sea[row][col].poll();
                sea[row][col].clear();
                return target.size;
            }
        }

        return 0;
    }

    static void print() {
        for (int row = 0; row < seaRow; row++) {
            System.out.println(Arrays.toString(sea[row]));
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        seaRow = Integer.parseInt(st.nextToken());
        seaCol = Integer.parseInt(st.nextToken());

        sea = new ArrayDeque[seaRow][seaCol];
        for (int row = 0; row < seaRow; row++) {
            for (int col = 0; col < seaCol; col++) {
                sea[row][col] = new ArrayDeque<>();
            }
        }

        int sharkCnt = Integer.parseInt(st.nextToken());

        for (int cnt = 0; cnt < sharkCnt; cnt++) {
            st = new StringTokenizer(br.readLine().trim());

            int row = Integer.parseInt(st.nextToken()) - 1;
            int col = Integer.parseInt(st.nextToken()) - 1;
            int speed = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken()) - 1;
            int size = Integer.parseInt(st.nextToken());

            sea[row][col].add(new Shark(row, col, speed, direction, size));
        }

        int answer = 0;

        for (int col = 0; col < seaCol; col++) {
            answer += catchShark(col);
            moveAll();
        }

        System.out.println(answer);
    }
}