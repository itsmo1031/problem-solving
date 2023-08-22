import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static char[] cand;
	static char[] selected;
	static PriorityQueue<String> result;
	static int length, alphabet;

	static void combination(int count, int start) {
		if (count == length) {
			if (isAvailable()) {
				String password = makePassword();
				result.add(password);
			}
			return;
		}

		for (int idx = start; idx < alphabet; idx++) {
			selected[count] = cand[idx];

			combination(count + 1, idx + 1);
		}

	}

	static String makePassword() {
		char[] res = Arrays.copyOf(selected, length);

		Arrays.sort(res);

		return new String(res);
	}

	static boolean isAvailable() {
		int vowelCnt = 0;
		int consonantCnt = 0;

		for (int idx = 0; idx < length; idx++) {
			if (isVowel(selected[idx]))
				vowelCnt += 1;
			else
				consonantCnt += 1;
		}

		return vowelCnt >= 1 && consonantCnt >= 2 ? true : false;
	}

	static boolean isVowel(char c) {
		if ("aeiou".indexOf(c) != -1)
			return true;
		return false;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());

		length = Integer.parseInt(st.nextToken());
		alphabet = Integer.parseInt(st.nextToken());

		cand = new char[alphabet];
		selected = new char[length];
		result = new PriorityQueue<>();

		st = new StringTokenizer(br.readLine().trim());

		for (int idx = 0; idx < alphabet; idx++) {
			cand[idx] = st.nextToken().charAt(0);
		}

		combination(0, 0);

		StringBuilder sb = new StringBuilder();

		while (!result.isEmpty()) {
			sb.append(result.poll()).append("\n");
		}

		System.out.print(sb);
	}
}
