import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static final int BG_SIZE = 100;
	static final int PAPER_SIZE = 10;
	static boolean[][] background = new boolean[BG_SIZE][BG_SIZE];
	static int total = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int paperCnt = Integer.parseInt(br.readLine().trim());
		for (int cnt = 0; cnt < paperCnt; cnt++) {
			st = new StringTokenizer(br.readLine().trim());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			total += draw(x, y);
		}
		System.out.println(total);
	}

	static int draw(int x, int y) {
		int count = 0;

		for (int row = x; row < x + PAPER_SIZE; row++) {
			for (int col = y; col < y + PAPER_SIZE; col++) {
				if (!background[row][col]) {
					background[row][col] = true;
					count += 1;
				}
			}
		}

		return count;
	}
}