import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Pos implements Comparable<Pos> {
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Pos o) {
            return this.x == o.x ? Integer.compare(this.y, o.y) : Integer.compare(this.x, o.x);
        }

        @Override
        public String toString() {
            return x + " " + y + "\n";
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(br.readLine().trim());

        Queue<Pos> q = new PriorityQueue<>();
        StringTokenizer st;
        for (int idx = 0; idx < count; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            Pos pos = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            q.offer(pos);
        }

        StringBuilder sb = new StringBuilder();
        while (!q.isEmpty()) {
            sb.append(q.poll());
        }
        System.out.print(sb);
    }
}