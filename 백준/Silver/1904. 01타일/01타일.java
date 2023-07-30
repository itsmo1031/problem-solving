import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] tile = new int[N + 1];
        tile[1] = 1;
        if (N > 1)
            tile[2] = 2;
        for (int i = 3; i < tile.length; i++) {
            tile[i] = (tile[i - 2] + tile[i - 1]) % 15746;
        }

        System.out.println(tile[N]);

        sc.close();
    }
}