import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long t = Long.parseLong(br.readLine());
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arrA = new int[n];

		for (int i = 0; i < n; i++) {
			arrA[i] = Integer.parseInt(st.nextToken());
		}

		List<Long> aSum = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			long sum = 0;
			for (int j = i; j < n; j++) {
				sum += arrA[j];
				aSum.add(sum);
			}
		}

		int m = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		int[] arrB = new int[m];

		for (int i = 0; i < m; i++) {
			arrB[i] = Integer.parseInt(st.nextToken());
		}

		List<Long> bSum = new ArrayList<>();

		for (int i = 0; i < m; i++) {
			long sum = 0;
			for (int j = i; j < m; j++) {
				sum += arrB[j];
				bSum.add(sum);
			}
		}

		Collections.sort(bSum);
		long answer = 0;

		for (long a : aSum) {
			long target = t - a;
			answer += upper_bound(target, bSum) - lower_bound(target, bSum);
		}

		System.out.println(answer);
	}

	static long upper_bound(long target, List<Long> sum) {
		int lo = 0;
		int hi = sum.size() - 1;

		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;

			if (sum.get(mid) <= target) {
				lo = mid + 1;
			} else {
				hi = mid - 1;
			}
		}

		return lo;
	}

	static long lower_bound(long target, List<Long> sum) {
		int lo = 0;
		int hi = sum.size() - 1;

		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;

			if (sum.get(mid) < target) {
				lo = mid + 1;
			} else {
				hi = mid - 1;
			}
		}

		return lo;
	}
}
