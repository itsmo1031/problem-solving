import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int teamCnt;
	static int[][] ability;
	static boolean[] isTeamA;
	static int diff;
	static int[] teamA;
	static int[] teamB;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		teamCnt = Integer.parseInt(br.readLine().trim());
		ability = new int[teamCnt][teamCnt];

		for (int row = 0; row < teamCnt; row++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int col = 0; col < teamCnt; col++) {
				ability[row][col] = Integer.parseInt(st.nextToken());
			}
		}

		isTeamA = new boolean[teamCnt];
		diff = Integer.MAX_VALUE;
		findMinDiff(0, 0);

		System.out.println(diff);
	}

	static void findMinDiff(int count, int start) {
		if (count == teamCnt / 2) {
			diff = Math.min(diff, getDiff());
			return;
		}
		for (int idx = start; idx < teamCnt; idx++) {
			isTeamA[idx] = true;
			findMinDiff(count + 1, idx + 1);
			isTeamA[idx] = false;
		}
	}

	static int getDiff() {
		teamA = new int[teamCnt / 2];
		teamB = new int[teamCnt / 2];
		int aIdx = 0;
		int bIdx = 0;
		for (int idx = 0; idx < teamCnt; idx++) {
			if (isTeamA[idx]) {
				teamA[aIdx++] = idx;
			} else
				teamB[bIdx++] = idx;
		}

		return Math.abs(calcSynergy(teamA) - calcSynergy(teamB));
	}

	static int calcSynergy(int[] arr) {
		int result = 0;
		for (int first = 0; first < teamCnt / 2; first++) {
			for (int second = first + 1; second < teamCnt / 2; second++) {
				result += ability[arr[first]][arr[second]] + ability[arr[second]][arr[first]];
			}
		}
		return result;
	}
}
