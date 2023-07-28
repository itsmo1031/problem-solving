import java.util.Scanner;

public class Main {
    static int[] tetra = new int[122];

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        for (int i = 1; i < 122; i++) {
            tetra[i] = i * (i + 1) * (i + 2) / 6;
        }

        int N = sc.nextInt();
        int[] cannon = new int[N + 1];
        for (int i : tetra) {
            if (i > N)
                break;
            for (int j = i; j <= N; j++) {
                if (i == 1)
                    cannon[j] = cannon[j - 1] + 1;
                else
                    cannon[j] = Math.min(cannon[j - i] + 1, cannon[j]);
            }
        }
        System.out.println(cannon[N]);

        sc.close();
    }
}