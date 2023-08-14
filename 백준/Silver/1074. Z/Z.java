import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int Z(int N, int row, int col) {
		if (N == 0) {
			return 0;
		}
		return 2 * (row % 2) + col % 2 + 4 * Z(N - 1, row / 2, col / 2);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		int N = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());

		System.out.println(Z(N, r, c));
	}
}
