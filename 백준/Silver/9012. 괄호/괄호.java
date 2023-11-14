import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine().trim());

        for (int tc = 0; tc < testCase; tc++) {
            System.out.println(test(br.readLine().trim().toCharArray()));
        }
    }

    static String test(char[] charArray) {
        Deque<Character> stack = new ArrayDeque<>();

        for (char ch : charArray) {
            Character cur = stack.peekLast();

            if (cur == null) {
                if (ch == ')') {
                    return "NO";
                }
                stack.offerLast(ch);
                continue;
            }

            if (cur.charValue() == ch) {
                stack.offerLast(ch);
            } else {
                stack.pollLast();
            }
        }

        return stack.isEmpty() ? "YES" : "NO";
    }
}