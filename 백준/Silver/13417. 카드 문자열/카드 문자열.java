import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    static Deque<Character> q;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int testCase = Integer.parseInt(br.readLine().trim());
        for (int tc = 0; tc < testCase; tc++) {
            q = new ArrayDeque<>();
            int wordCnt = Integer.parseInt(br.readLine().trim());
            st = new StringTokenizer(br.readLine().trim());

            for (int idx = 0; idx < wordCnt; idx++) {
                char ch = st.nextToken().charAt(0);
                if (q.isEmpty()) {
                    q.offer(ch);
                    continue;
                }
                char first = q.peek();
                if (ch <= first) {
                    q.offerFirst(ch);
                } else {
                    q.offerLast(ch);
                }
            }

            for (char ch : q) {
                sb.append(ch);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}