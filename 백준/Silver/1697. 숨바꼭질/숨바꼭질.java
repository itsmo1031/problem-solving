import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int hider;
	static int seeker;
	static boolean[] visited = new boolean[200001];

	static class Seek {
		int pos;
		int time;

		public Seek(int pos, int time) {
			this.pos = pos;
			this.time = time;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine().trim());

		seeker = Integer.parseInt(st.nextToken());
		hider = Integer.parseInt(st.nextToken());

		System.out.println(hideAndSeek(new Seek(seeker, 0)));
	}

	static int hideAndSeek(Seek pos) {
		Queue<Seek> q = new ArrayDeque<>();
		q.offer(pos);
		visited[pos.pos] = true;
		if (pos.pos == hider)
			return pos.time;

		while (!q.isEmpty()) {
			Seek now = q.poll();
			for (int move : new int[] { 1, -1, now.pos }) {
				int next = now.pos + move;
				if (next < 0 || next > 200000)
					continue;
				if (next == hider)
					return now.time + 1;
				if (!visited[next]) {
					visited[next] = true;
					q.offer(new Seek(next, now.time + 1));
				}
			}
		}

		return -1;
	}
}
