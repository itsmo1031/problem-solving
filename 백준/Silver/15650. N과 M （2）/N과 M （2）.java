import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] num, target;
	static int length, limit;
	static StringBuilder sb = new StringBuilder();

	static void combination(int count, int startIdx) {
		if (count == limit) {
			for (int index = 0; index < limit; index++) {
				sb.append(target[index]).append(" ");
			}
			sb.append("\n");
			return;
		}
		for (int numIdx = startIdx; numIdx < length; numIdx++) {
			target[count] = num[numIdx];
			combination(count + 1, numIdx + 1);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		length = Integer.parseInt(st.nextToken());
		limit = Integer.parseInt(st.nextToken());
		num = new int[length];
		target = new int[limit];
		for (int index = 0; index < length; index++) {
			num[index] = index + 1;
		}
		combination(0, 0);
		System.out.println(sb);
	}
}
