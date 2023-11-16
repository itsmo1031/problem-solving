import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
    static final int FLUSH = 0, TERMINATE = -1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine().trim());

        Queue<Integer> q = new ArrayDeque<>();

        while (true) {
            int next = Integer.parseInt(br.readLine().trim());

            if (next == TERMINATE) {
                break;
            }

            if (next == FLUSH) {
                q.poll();
                continue;
            }

            if (q.size() < size) {
                q.offer(next);
            }
        }

        if (q.isEmpty()) {
            System.out.println("empty");
        } else {
            StringBuilder sb = new StringBuilder();
            q.forEach(elem -> sb.append(elem).append(" "));
            System.out.println(sb.toString().trim());
        }
    }
}