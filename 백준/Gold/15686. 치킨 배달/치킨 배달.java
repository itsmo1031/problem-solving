import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] selected;
	static List<Pos> house;
	static List<Pos> chicken;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		house = new ArrayList<>();
		chicken = new ArrayList<>();

		for (int row = 0; row < N; row++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int col = 0; col < N; col++) {
				int info = Integer.parseInt(st.nextToken());
				if (info == 1)
					house.add(new Pos(row, col));
				else if (info == 2)
					chicken.add(new Pos(row, col));
			}
		}

		selected = new int[chicken.size()];

		int cnt = 0;
		while (++cnt <= M)
			selected[selected.length - cnt] = 1;

		int answer = Integer.MAX_VALUE;

		do {
			answer = Math.min(answer, getChickenDist());
		} while (nextPermutation(selected));

		System.out.println(answer);
	}

	static int getChickenDist() {
		int result = 0;

		for (Pos pos : house) {
			result += calcDist(pos);
		}

		return result;
	}

	static int calcDist(Pos home) {
		int minDist = Integer.MAX_VALUE;

		for (int idx = 0; idx < selected.length; idx++) {
			if (selected[idx] != 0) {
				minDist = Math.min(minDist, chicken.get(idx).dist(home));
			}
		}

		return minDist;
	}

	static boolean nextPermutation(int[] arr) {
		int top = arr.length - 1;
		while (top > 0 && arr[top - 1] >= arr[top])
			--top;

		if (top == 0)
			return false;

		int target = arr.length - 1;
		while (arr[top - 1] >= arr[target])
			--target;

		int temp = arr[top - 1];
		arr[top - 1] = arr[target];
		arr[target] = temp;

		Arrays.sort(arr, top, arr.length);

		return true;
	}

	static class Pos {
		int x;
		int y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public int dist(Pos other) {
			return Math.abs(other.x - this.x) + Math.abs(other.y - this.y);
		}
	}

}
