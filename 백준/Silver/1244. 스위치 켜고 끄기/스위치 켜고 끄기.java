import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int switchNum;
	static int[] switches;

	static void turnSwitches(int gender, int switchIdx) {
		List<Integer> flip = new ArrayList<>();

		if (gender == 1) {
			for (int idx = switchIdx; idx <= switchNum; idx += switchIdx) {
				flip.add(idx);
			}
		} else {
			flip.add(switchIdx);
			int left = switchIdx - 1;
			int right = switchIdx + 1;
			while (left > 0 && right <= switchNum) {
				if (switches[left] != switches[right])
					break;
				flip.add(left--);
				flip.add(right++);
			}
		}

		for (int index : flip) {
			switches[index] = switches[index] == 0 ? 1 : 0;
		}
	}

	static void printSwitches() {
		StringBuilder sb = new StringBuilder();

		for (int index = 1; index <= switchNum; index++) {
			sb.append(switches[index]).append(" ");
			if (index % 20 == 0) {
				sb.append("\n");
			}
		}

		System.out.println(sb);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		switchNum = Integer.parseInt(br.readLine().trim());
		switches = new int[switchNum + 1];
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		for (int index = 1; index <= switchNum; index++) {
			switches[index] = Integer.parseInt(st.nextToken());
		}

		int studentNum = Integer.parseInt(br.readLine().trim());
		for (int index = 0; index < studentNum; index++) {
			st = new StringTokenizer(br.readLine().trim());
			int gender = Integer.parseInt(st.nextToken());
			int switchIdx = Integer.parseInt(st.nextToken());
			turnSwitches(gender, switchIdx);
		}

		printSwitches();

	}
}
