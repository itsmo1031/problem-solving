import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static final int[][] DIR = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
    static final char WALL = '#';
    static int mapRow, mapCol;
    static char[][] map;
    static Pos hole;
    static Marble[] marbleList;
    static Marble red, blue;
    static int answer = -1;

    static class Marble extends Pos {

        public Marble(int r, int c) {
            super(r, c);
        }

        public boolean isHole() {
            return hole.r == this.r && hole.c == this.c;
        }

    }

    static class Pos {
        int r, c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public String toString() {
            return "Pos [r=" + r + ", c=" + c + "]";
        }

        public boolean isSamePos(Pos pos) {
            return this.r == pos.r && this.c == pos.c;
        }

        public Pos getPos() {
            return new Pos(r, c);
        }

        public void setPos(Pos pos) {
            this.r = pos.r;
            this.c = pos.c;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        mapRow = Integer.parseInt(st.nextToken());
        mapCol = Integer.parseInt(st.nextToken());
        map = new char[mapRow][mapCol];

        for (int row = 0; row < mapRow; row++) {
            char[] line = br.readLine().trim().toCharArray();
            for (int col = 0; col < mapCol; col++) {
                if (line[col] == WALL) {
                    map[row][col] = WALL;
                    continue;
                }

                if (line[col] == 'B')
                    blue = new Marble(row, col);
                else if (line[col] == 'R')
                    red = new Marble(row, col);
                else if (line[col] == 'O')
                    hole = new Pos(row, col);
                map[row][col] = '.';
            }
        }

        dfs(0);
        System.out.println(answer);
    }

    static void dfs(int time) {
        if (time > 10 || (answer != -1 && time >= answer)) {
            return;
        }

        if (red.isSamePos(blue) || blue.isHole())
            return;
        if (red.isHole()) {
            answer = time;
            return;
        }

        for (int dir = 0; dir < 4; dir++) {
            Pos redPos = red.getPos();
            Pos bluePos = blue.getPos();
            gravity(dir);
            if (red.isSamePos(redPos) && blue.isSamePos(bluePos))
                continue;
            dfs(time + 1);
            red.setPos(redPos);
            blue.setPos(bluePos);
        }
    }

    static void gravity(int dir) {
        Marble[] marbleList = { red, blue };
        Arrays.sort(marbleList, (Marble m1, Marble m2) -> {
            int comp;
            if (dir == 0) {
                comp = Integer.compare(m1.r, m2.r);
            } else if (dir == 1) {
                comp = Integer.compare(m2.r, m1.r);
            } else if (dir == 2) {
                comp = Integer.compare(m1.c, m2.c);
            } else {
                comp = Integer.compare(m2.c, m1.c);
            }

            return comp;
        });

        for (int idx = 0; idx < 2; idx++) {
            Marble current = marbleList[idx];
            Marble opposite = marbleList[(idx + 1) % 2];

            while (true) {
                int nr = current.r + DIR[dir][0];
                int nc = current.c + DIR[dir][1];

                if (nr < 0 || nr >= mapRow || nc < 0 || nc >= mapCol || map[nr][nc] == WALL)
                    break;

                if (nr == hole.r && nc == hole.c) {
                    current.r = nr;
                    current.c = nc;
                    break;
                }
                if (nr == opposite.r && nc == opposite.c)
                    break;

                current.r = nr;
                current.c = nc;
            }
        }
    }
}