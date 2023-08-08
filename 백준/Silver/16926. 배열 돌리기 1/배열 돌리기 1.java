import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static String[][] arr;
	static boolean[][] visited;
	static final int[][] DIR = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
	static int maxDepth;
	static int totalRow, totalCol;

	static void rotate(int count) {
		for (int cnt = 0; cnt < count; cnt++) {
			visited = new boolean[totalRow][totalCol];
			for (int depth = 0; depth < maxDepth; depth++) {
				change(depth, depth, arr[depth][depth], 0);
			}
		}
	}

	static void change(int x, int y, String data, int direction) {
		if (direction == 4) {
			return;
		}

		int nx = x + DIR[direction][0];
		int ny = y + DIR[direction][1];

		if (nx < 0 || nx >= totalRow || ny < 0 || ny >= totalCol || visited[nx][ny]) {
			direction += 1;
			change(x, y, data, direction);
			return;
		}
		visited[nx][ny] = true;
		String nextData = arr[nx][ny];
		arr[nx][ny] = data;
		change(nx, ny, nextData, direction);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());

		totalRow = Integer.parseInt(st.nextToken());
		totalCol = Integer.parseInt(st.nextToken());
		int rotateCnt = Integer.parseInt(st.nextToken());
		maxDepth = Math.min(totalRow, totalCol) / 2;
		arr = new String[totalRow][totalCol];

		for (int row = 0; row < totalRow; row++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int col = 0; col < totalCol; col++) {
				arr[row][col] = st.nextToken();
			}
		}

		rotate(rotateCnt);

		for (String[] line : arr) {
			System.out.println(String.join(" ", line));
		}
	}
}
