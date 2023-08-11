import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static StringTokenizer st;
	static PriorityQueue<String> number;
	static final List<String> numToString = Arrays.asList("zero", "one", "two", "three", "four", "five", "six", "seven",
			"eight", "nine");

	static String parseWord(int num) {
		String word = numToString.get(num % 10);

		if (num >= 10) {
			word = numToString.get(num / 10).concat(" " + word);
		}

		return word;
	}

	static String parseNum(String str) {
		StringBuilder sb = new StringBuilder();
		st = new StringTokenizer(str);

		while (st.hasMoreTokens()) {
			sb.append(numToString.indexOf(st.nextToken()));
		}

		return sb.toString();
	}

	static void print() {
		StringBuilder sb = new StringBuilder();

		int cnt = 0;
		while (!number.isEmpty()) {
			sb.append(parseNum(number.poll()));
			if (++cnt % 10 == 0)
				sb.append("\n");
			else
				sb.append(" ");
		}

		System.out.print(sb);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine().trim());

		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		number = new PriorityQueue<>();

		for (int num = M; num <= N; num++) {
			number.add(parseWord(num));
		}
		print();
	}
}
