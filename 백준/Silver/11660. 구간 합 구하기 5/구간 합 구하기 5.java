import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int[][] prefixSumTable;

	static int sum(int fromX, int fromY, int toX, int toY) {
		return prefixSumTable[toX][toY] - prefixSumTable[fromX - 1][toY] - prefixSumTable[toX][fromY - 1]
				+ prefixSumTable[fromX - 1][fromY - 1];
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		int tableSize = Integer.parseInt(st.nextToken());
		int testCase = Integer.parseInt(st.nextToken());

		prefixSumTable = new int[tableSize + 1][tableSize + 1];

		for (int row = 1; row <= tableSize; row++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int col = 1; col <= tableSize; col++) {
				prefixSumTable[row][col] = Integer.parseInt(st.nextToken()) - sum(row, col, row, col);
			}
		}
		for (int tc = 0; tc < testCase; tc++) {
			st = new StringTokenizer(br.readLine().trim());
			int fromX = Integer.parseInt(st.nextToken());
			int fromY = Integer.parseInt(st.nextToken());
			int toX = Integer.parseInt(st.nextToken());
			int toY = Integer.parseInt(st.nextToken());
			sb.append(sum(fromX, fromY, toX, toY)).append("\n");
		}
		System.out.print(sb);
	}
}
