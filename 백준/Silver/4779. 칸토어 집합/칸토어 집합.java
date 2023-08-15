import java.util.Scanner;

public class Main {
    static StringBuilder sb;

    static void cantor(int start, int length) {
        if (length == 1)
            return;

        int newLength = length / 3;

        for (int idx = start + newLength; idx < start + 2 * newLength; idx++) {
            sb.setCharAt(idx, ' ');
        }

        cantor(start, newLength);
        cantor(start + 2 * newLength, newLength);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            int N = sc.nextInt();
            int length = (int) Math.pow(3, N);
            sb = new StringBuilder();

            for (int idx = 0; idx < length; idx++) {
                sb.append("-");
            }

            cantor(0, length);
            System.out.println(sb);
        }

        sc.close();
    }
}