import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int size;
	static long[] sour;
	static long[] bitter;
	static boolean[] isSelected;
	static long diff = Long.MAX_VALUE;

	static void getDiff(int count, int selected) {
		if (count == size) {
			if (selected == 0)
				return;

			long foodSour = 1;
			long foodBitter = 0;
			for (int index = 0; index < size; index++) {
				if (isSelected[index]) {
					foodSour *= sour[index];
					foodBitter += bitter[index];
				}
			}
			diff = Math.min(diff, Math.abs(foodSour - foodBitter));
			return;
		}

		isSelected[count] = true;
		getDiff(count + 1, selected + 1);
		isSelected[count] = false;
		getDiff(count + 1, selected);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		size = Integer.parseInt(br.readLine().trim());
		sour = new long[size];
		bitter = new long[size];
		isSelected = new boolean[size];

		for (int index = 0; index < size; index++) {
			st = new StringTokenizer(br.readLine().trim());
			sour[index] = Long.parseLong(st.nextToken());
			bitter[index] = Long.parseLong(st.nextToken());
		}
		getDiff(0, 0);
		System.out.println(diff);
	}
}
