import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static class Pos {
        int r, c;

        public Pos(String position) {
            this.r = position.charAt(1) - '1';
            this.c = position.charAt(0) - 'A';
        }

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }

        Pos getPos() {
            return new Pos(r, c);
        }

        Pos getNextPos(String cmds) {
            int nr = r;
            int nc = c;
            for (char cmd : cmds.toCharArray()) {
                int[] dir = DIR.get(cmd);

                nr += dir[0];
                nc += dir[1];
            }

            return new Pos(nr, nc);
        }

        boolean movable(Pos pos) {
            int nr = pos.r;
            int nc = pos.c;

            if (nr < 0 || nr >= BOARD_SIZE || nc < 0 || nc >= BOARD_SIZE)
                return false;

            return true;
        }

        void move(Pos pos) {
            r = pos.r;
            c = pos.c;
        }

        String print() {
            StringBuilder sb = new StringBuilder();

            sb.append((char) (c + 'A'));
            sb.append((char) (r + '1'));

            return sb.toString();
        }

        boolean isSamePos(Pos o) {
            return this.r == o.r && this.c == o.c;
        }
    }

    static Map<Character, int[]> DIR;
    static final int BOARD_SIZE = 8;

    public static void main(String[] args) throws Exception {
        DIR = new HashMap<>();
        DIR.put('R', new int[] { 0, 1 });
        DIR.put('L', new int[] { 0, -1 });
        DIR.put('B', new int[] { -1, 0 });
        DIR.put('T', new int[] { 1, 0 });

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        Pos king = new Pos(st.nextToken());
        Pos stone = new Pos(st.nextToken());

        int cmdCnt = Integer.parseInt(st.nextToken());

        for (int idx = 0; idx < cmdCnt; idx++) {
            String cmd = br.readLine().trim();

            Pos current = king.getPos();
            Pos next = king.getNextPos(cmd);
            Pos nextStone = stone.getNextPos(cmd);

            if (king.movable(next)) {
                king.move(next);
                if (king.isSamePos(stone)) {
                    if (stone.movable(nextStone))
                        stone.move(nextStone);
                    else {
                        king.move(current);
                    }
                }
            }
        }

        System.out.println(king.print());
        System.out.println(stone.print());
    }
}