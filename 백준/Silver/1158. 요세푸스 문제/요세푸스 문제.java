import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static boolean[] isEliminated;
	static int N;
	static int K;

	static String josephus() {
		StringBuilder sb = new StringBuilder();
		sb.append("<");
		int printCnt = 0;
		int now = K - 1;

		while (printCnt < N) {
			if (!isEliminated[now]) {
				isEliminated[now] = true;
				sb.append(now + 1);
				printCnt += 1;
			}
			if (printCnt < N) {
				now = getNext(now);
				sb.append(", ");
			}
		}

		sb.append(">");
		return sb.toString();
	}

	static int getNext(int now) {
		int cnt = 0;

		while (cnt != K) {
			now = (now + 1) % N;
			if (!isEliminated[now]) {
				cnt += 1;
			}
		}

		return now;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());

		N = Integer.parseInt(st.nextToken());
		isEliminated = new boolean[N];
		K = Integer.parseInt(st.nextToken());

		System.out.println(josephus());
	}
}
