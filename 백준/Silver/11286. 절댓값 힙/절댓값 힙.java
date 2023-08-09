import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
	static PriorityQueue<Abs> pq;
	static StringBuilder sb;

	static class Abs implements Comparable<Abs> {
		double abs;
		int val;

		public Abs(double abs, int val) {
			this.abs = abs;
			this.val = val;
		}

		@Override
		public int compareTo(Abs o) {
			if (this.abs > o.abs) {
				return 1;
			} else if (this.abs == o.abs) {
				return 0;
			} else
				return -1;
		}
	}

	static void cmd(int num) {
		if (num == 0) {
			print();
		} else {
			add(num);
		}
	}

	static void print() {
		int result = 0;
		if (!pq.isEmpty()) {
			result = pq.poll().val;
		}
		sb.append(result).append("\n");
	}

	static void add(int num) {
		double abs = Math.abs(num + 0.4);
		pq.offer(new Abs(abs, num));
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int testCase = Integer.parseInt(br.readLine().trim());
		pq = new PriorityQueue<>();
		for (int tc = 0; tc < testCase; tc++) {
			int command = Integer.parseInt(br.readLine().trim());
			cmd(command);
		}
		System.out.println(sb);
	}
}
