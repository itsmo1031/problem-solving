import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int[] hat;
	static int[] selected;
	static final int LEN = 9;
	static final int SELECT = 7;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		hat = new int[LEN];
		selected = new int[LEN];

		for (int idx = 0; idx < LEN; idx++) {
			hat[idx] = Integer.parseInt(br.readLine().trim());
		}

		int cnt = 0;
		while (++cnt <= SELECT) {
			selected[LEN - cnt] = 1;
		}

		do {
			if (calc() == 100)
				break;
		} while (nextPermutation(selected));

		for (int idx = 0; idx < LEN; idx++) {
			if (selected[idx] != 0)
				System.out.println(hat[idx]);
		}
	}

	static int calc() {
		int sum = 0;
		for (int idx = 0; idx < LEN; idx++) {
			if (selected[idx] != 0)
				sum += hat[idx];
		}
		return sum;
	}

	static boolean nextPermutation(int[] arr) {
		int top = LEN - 1;
		while (top > 0 && arr[top - 1] >= arr[top])
			--top;

		if (top == 0)
			return false;

		int switching = LEN - 1;
		while (arr[top - 1] >= arr[switching])
			--switching;

		int temp = arr[top - 1];
		arr[top - 1] = arr[switching];
		arr[switching] = temp;

		Arrays.sort(arr, top, LEN);

		return true;
	}
}
