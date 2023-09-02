import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int size = Integer.parseInt(br.readLine().trim());
        int[] num = new int[size];
        int[] answer = new int[size];

        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        for (int idx = 0; idx < size; idx++) {
            num[idx] = Integer.parseInt(st.nextToken());
        }

        Stack<Integer> stack = new Stack<>();

        for (int idx = size - 1; idx > -1; idx--) {
            int now = num[idx];

            while (!stack.isEmpty()) {
                int top = stack.peek();
                if (now < top) {
                    answer[idx] = top;
                    break;
                } else {
                    stack.pop();
                }
            }
            if (stack.isEmpty()) {
                answer[idx] = -1;
            }

            stack.push(now);
        }

        StringBuilder sb = new StringBuilder();
        for (int res : answer) {
            sb.append(res + " ");
        }

        System.out.println(sb.toString().trim());

    }
}