import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int N, answer;
	static boolean[] row, rightTop, rightBtm;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine().trim());
		row = new boolean[N];
		rightTop = new boolean[2 * N];
		rightBtm = new boolean[2 * N];
        
		nQueen(0);
		System.out.println(answer);
	}

	static void nQueen(int y) {
		if (y == N) {
			answer++;
			return;
		}
		for (int x = 0; x < N; x++) {
			if (row[x] || rightTop[x + y] || rightBtm[x - y + N])
				continue;
			row[x] = true;
			rightTop[x + y] = true;
			rightBtm[x - y + N] = true;

			nQueen(y + 1);

			row[x] = false;
			rightTop[x + y] = false;
			rightBtm[x - y + N] = false;
		}
	}
}