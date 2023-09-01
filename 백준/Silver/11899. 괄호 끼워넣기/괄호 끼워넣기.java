import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] data = br.readLine().trim().toCharArray();

        System.out.println(findMinBrace(data));
    }

    private static int findMinBrace(char[] data) {
        Stack<Character> stack = new Stack<>();

        for (char ch : data) {
            if (stack.isEmpty())
                stack.add(ch);
            else if (stack.peek() == '(' && ch == ')')
                stack.pop();
            else
                stack.add(ch);
        }

        return stack.size();
    }
}