import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[][] country;
	static final int WIN = 0, DRAW = 1, LOSE = 2;
	static int[] flag;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		for (int tc = 0; tc < 4; tc++) {
			st = new StringTokenizer(br.readLine().trim());
			country = new int[6][3];
			boolean isPossible = true;
			for (int num = 0; num < 6; num++) {
				int win = Integer.parseInt(st.nextToken());
				int draw = Integer.parseInt(st.nextToken());
				int lose = Integer.parseInt(st.nextToken());

				if (win + draw + lose != 5) {
					isPossible = false;
					break;
				}
				country[num][WIN] = win;
				country[num][DRAW] = draw;
				country[num][LOSE] = lose;
			}

			flag = new int[] { 0, 0, 0, 0, 1, 1 };
			if (isPossible && doGame(true, flag))
				sb.append("1 ");
			else
				sb.append("0 ");
		}
		System.out.println(sb);
	}

	static boolean checkResult() {
		for (int[] team : country) {
			if (team[WIN] != 0 || team[DRAW] != 0 || team[LOSE] != 0)
				return false;
		}
		return true;
	}

	static boolean doGame(boolean hasNext, int[] flags) {

		if (!hasNext)
			return checkResult();

		int homeIdx = -1;
		int awayIdx = -1;
		for (int idx = 0; idx < flags.length; idx++) {
			if (flags[idx] == 1) {
				if (homeIdx == -1)
					homeIdx = idx;
				else
					awayIdx = idx;
			}
		}

		int[] home = country[homeIdx];
		int[] away = country[awayIdx];

		boolean next = nextPermutation(flags);

		for (int[] result : new int[][] { { WIN, LOSE }, { DRAW, DRAW }, { LOSE, WIN } }) {
			if (home[result[0]] > 0 && away[result[1]] > 0) {
				home[result[0]] -= 1;
				away[result[1]] -= 1;
				if (doGame(next, Arrays.copyOf(flags, flags.length)))
					return true;
				home[result[0]] += 1;
				away[result[1]] += 1;
			}
		}

		return false;
	}

	static boolean nextPermutation(int[] arr) {
		int top = arr.length - 1;
		while (top > 0 && arr[top - 1] >= arr[top])
			--top;

		if (top == 0)
			return false;

		int target = arr.length - 1;
		while (arr[target] <= arr[top - 1])
			--target;

		int temp = arr[target];
		arr[target] = arr[top - 1];
		arr[top - 1] = temp;

		Arrays.sort(arr, top, arr.length);

		return true;
	}
}
