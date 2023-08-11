import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static final int MAX_VALUE = (int) (1e9);

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine().trim());
		int[] sugar = new int[N + 3];

        Arrays.fill(sugar, MAX_VALUE);
		sugar[3] = 1;
		sugar[5] = 1;

		for (int i = 6; i < sugar.length; i++) {
			sugar[i] = Math.min(sugar[i - 3], sugar[i - 5]) + 1;
		}

		if (sugar[N] >= MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(sugar[N]);
	}
}
