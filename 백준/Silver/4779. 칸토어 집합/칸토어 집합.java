import java.util.Scanner;

public class Main {
    static String cantor(int n, String str) {
        if (n == 0)
            return "-";
        String newStr = cantor(n - 1, "-".repeat((int) Math.pow(3, n - 1)));
        return newStr + " ".repeat((int) Math.pow(3, n - 1)) + newStr;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            int N = sc.nextInt();
            String str = cantor(N, "-".repeat((int) Math.pow(3, N)));
            System.out.println(str);
        }

        sc.close();
    }
}