import java.io.*;
import java.util.*;

public class Main {
	static List<Integer> bowmanList;
	static final int[][] DIR = { { 0, -1 }, { -1, 0 }, { 0, 1 } };
	static int[][] map;
	static int mapRow, mapCol, maxRange;

	static class Arrow extends Pos {
		int range;

		public Arrow(int x, int y, int range) {
			super(x, y);
			this.range = range;
		}
	}

	static class Pos {
		int x, y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + x;
			result = prime * result + y;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Pos other = (Pos) obj;
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "Pos [x=" + x + ", y=" + y + "]";
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());

		mapRow = Integer.parseInt(st.nextToken());
		mapCol = Integer.parseInt(st.nextToken());
		maxRange = Integer.parseInt(st.nextToken());

		int[][] original = new int[mapRow][mapCol];
		int[] bowmanIdx = new int[mapCol];
		int bowmanCnt = 0;
		while (++bowmanCnt <= 3)
			bowmanIdx[mapCol - bowmanCnt] = 1;

		for (int row = 0; row < mapRow; row++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int col = 0; col < mapCol; col++) {
				original[row][col] = Integer.parseInt(st.nextToken());
			}
		}

		int answer = 0;

		do {
			map = deepcopy(original);
			bowmanList = new ArrayList<>();

			for (int idx = 0; idx < mapCol; idx++) {
				if (bowmanIdx[idx] == 1) {
					bowmanList.add(idx);
				}
			}

			answer = Math.max(answer, startGame());
		} while (nextPermutation(bowmanIdx));

		System.out.println(answer);
	}

	static int startGame() {
		int result = 0;
		HashSet<Pos> set = new HashSet<>();

		while (isEnemyLeft()) {
			set.clear();

			for (int bowman : bowmanList) {
				Pos target = shoot(bowman);
				if (target != null)
					set.add(target);
			}

			for (Pos pos : set) {
				map[pos.x][pos.y] = 0;
				result += 1;
			}

			enemyDown();
		}

		return result;
	}

	static void enemyDown() {
		int[][] newMap = new int[mapRow][mapCol];

		for (int row = 1; row < mapRow; row++) {
			newMap[row] = Arrays.copyOf(map[row - 1], mapCol);
		}

		map = newMap;
	}

	static Pos shoot(int bowman) {
		Queue<Arrow> q = new ArrayDeque<>();
		boolean[][] visited = new boolean[mapRow][mapCol];
		q.add(new Arrow(mapRow, bowman, 0));

		while (!q.isEmpty()) {
			Arrow arrow = q.poll();

			for (int[] dir : DIR) {
				int nx = arrow.x + dir[0];
				int ny = arrow.y + dir[1];

				if (nx < 0 || nx >= mapRow || ny < 0 || ny >= mapCol || visited[nx][ny])
					continue;

				if (arrow.range < maxRange) {
					if (map[nx][ny] == 1)
						return new Pos(nx, ny);
					visited[nx][ny] = true;
					q.add(new Arrow(nx, ny, arrow.range + 1));
				}
			}
		}
		return null;
	}

	static boolean isEnemyLeft() {
		for (int row = mapRow - 1; row > -1; row--) {
			for (int col = mapCol - 1; col > -1; col--) {
				if (map[row][col] == 1)
					return true;
			}
		}
		return false;
	}

	static int[][] deepcopy(int[][] original) {
		int[][] result = new int[mapRow][mapCol];

		for (int row = 0; row < mapRow; row++) {
			result[row] = Arrays.copyOf(original[row], mapCol);
		}

		return result;
	}

	static boolean nextPermutation(int[] arr) {
		int top = arr.length - 1;
		while (top > 0 && arr[top - 1] >= arr[top])
			--top;

		if (top == 0)
			return false;

		int target = arr.length - 1;
		while (arr[target] <= arr[top - 1])
			--target;

		int temp = arr[target];
		arr[target] = arr[top - 1];
		arr[top - 1] = temp;

		Arrays.sort(arr, top, arr.length);

		return true;
	}
}
