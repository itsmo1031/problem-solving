import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int mapRow, mapCol;
	static int[][] DIR = { { -1, 1 }, { 0, 1 }, { 1, 1 } };
	static char[][] map;
	static boolean[][] visited;
	static int cnt;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		mapRow = Integer.parseInt(st.nextToken());
		mapCol = Integer.parseInt(st.nextToken());

		map = new char[mapRow][mapCol];
		visited = new boolean[mapRow][mapCol];

		for (int row = 0; row < mapRow; row++) {
			String data = br.readLine().trim();
			for (int col = 0; col < mapCol; col++) {
				map[row][col] = data.charAt(col);
			}
		}

		cnt = 0;
		for (int row = 0; row < mapRow; row++) {
			if (dfs(new Pos(row, 0)))
				cnt += 1;
		}

		System.out.println(cnt);
	}

	static boolean dfs(Pos pos) {
		visited[pos.x][pos.y] = true;

		if (pos.y == mapCol - 1) {
			return true;
		}
		for (int[] dir : DIR) {
			int nx = pos.x + dir[0];
			int ny = pos.y + dir[1];
			if (nx < 0 || nx >= mapRow || ny < 0 || ny >= mapCol || map[nx][ny] == 'x' || visited[nx][ny])
				continue;
			if (dfs(new Pos(nx, ny)))
				return true;
		}

		return false;
	}

	static class Pos {
		int x, y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
