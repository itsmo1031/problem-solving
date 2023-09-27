import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static Pos[] CVS;
    static Pos homePos, destPos;

    static class Pos {
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Pos(StringTokenizer st) {
            this.x = Integer.parseInt(st.nextToken());
            this.y = Integer.parseInt(st.nextToken());
        }

        int dist(Pos target) {
            return Math.abs(target.x - this.x) + Math.abs(target.y - this.y);
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + x;
            result = prime * result + y;
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Pos other = (Pos) obj;
            if (x != other.x)
                return false;
            if (y != other.y)
                return false;
            return true;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine().trim());
        StringTokenizer st;

        for (int tc = 1; tc <= testCase; tc++) {
            int CVSCnt = Integer.parseInt(br.readLine().trim());
            CVS = new Pos[CVSCnt];

            st = new StringTokenizer(br.readLine().trim());
            homePos = new Pos(st);

            for (int idx = 0; idx < CVSCnt; idx++) {
                st = new StringTokenizer(br.readLine().trim());
                CVS[idx] = new Pos(st);
            }

            st = new StringTokenizer(br.readLine().trim());
            destPos = new Pos(st);

            String answer = move(homePos);

            System.out.println(answer);
        }
    }

    static String move(Pos start) {
        Set<Pos> visited = new HashSet<>();
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(start);

        while (!q.isEmpty()) {
            Pos now = q.poll();

            if (now.dist(destPos) <= 1000)
                return "happy";

            for (Pos next : CVS) {
                if (visited.contains(next))
                    continue;

                if (now.dist(next) <= 1000) {
                    visited.add(next);
                    q.offer(next);
                }
            }
        }

        return "sad";
    }

}
