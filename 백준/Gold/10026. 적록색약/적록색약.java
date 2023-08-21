import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	static int size;
	static char[][] painting;
	static boolean[][] visited;
	static boolean isColorBlind;
	static final int[][] DIR = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

	static class Pixel {
		int x, y;
		char value;

		public Pixel(int x, int y) {
			this.x = x;
			this.y = y;
			this.value = getColor();
		}

		char getColor() {
			if (!isColorBlind)
				return painting[x][y];
			if (painting[x][y] == 'R' || painting[x][y] == 'G')
				return 'R';
			else
				return 'B';
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		size = Integer.parseInt(br.readLine().trim());
		painting = new char[size][size];

		for (int row = 0; row < size; row++) {
			String line = br.readLine().trim();
			for (int col = 0; col < size; col++) {
				painting[row][col] = line.charAt(col);
			}
		}

		for (boolean status : new boolean[] { false, true }) {
			isColorBlind = status;
			visited = new boolean[size][size];
			int answer = 0;
			for (int row = 0; row < size; row++) {
				for (int col = 0; col < size; col++) {
					if (!visited[row][col]) {
						visited[row][col] = true;
						answer += bfs(new Pixel(row, col));
					}
				}
			}
			sb.append(answer + " ");
		}
		System.out.println(sb);
	}

	static int bfs(Pixel pixel) {
		Queue<Pixel> q = new ArrayDeque<>();
		q.offer(pixel);

		while (!q.isEmpty()) {
			Pixel now = q.poll();
			for (int[] dir : DIR) {
				int nx = now.x + dir[0];
				int ny = now.y + dir[1];

				if (nx < 0 || nx >= size || ny < 0 || ny >= size || visited[nx][ny])
					continue;

				Pixel next = new Pixel(nx, ny);
				if (now.value == next.value) {
					visited[nx][ny] = true;
					q.offer(next);
				}
			}
		}

		return 1;
	}
}
