import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int[] prefixSum;

	static int sum(int from, int to) {
		return prefixSum[to] - prefixSum[from - 1];
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int arrLength = Integer.parseInt(st.nextToken());
		int testCase = Integer.parseInt(st.nextToken());
		prefixSum = new int[arrLength + 1];

		st = new StringTokenizer(br.readLine());
		for (int index = 1; index <= arrLength; index++) {
			prefixSum[index] = prefixSum[index - 1] + Integer.parseInt(st.nextToken());
		}

		for (int tc = 0; tc < testCase; tc++) {
			st = new StringTokenizer(br.readLine());
			int fromIdx = Integer.parseInt(st.nextToken());
			int toIdx = Integer.parseInt(st.nextToken());
			sb.append(sum(fromIdx, toIdx)).append("\n");
		}
		System.out.println(sb);
	}
}
