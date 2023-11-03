import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
    static class Deck {
        private final Queue<Integer> q;

        public Deck(int num) {
            q = new ArrayDeque<>();

            for (int idx = 1; idx <= num; idx++) {
                q.offer(idx);
            }
        }

        public void drop() {
            StringBuilder sb = new StringBuilder();

            while (q.size() > 1) {
                sb.append(q.poll()).append(" ");
                q.offer(q.poll());
            }
            sb.append(q.poll());

            System.out.println(sb);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cardCnt = Integer.parseInt(br.readLine().trim());

        new Deck(cardCnt).drop();
    }
}