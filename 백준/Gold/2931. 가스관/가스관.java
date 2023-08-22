import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int mapRow;
	static int mapCol;
	static char[][] map;
	static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;
	static final HashMap<Integer, int[]> direction = new HashMap<>();
	static StringBuilder sb;

	static class Pos {
		int x, y;
		int direction;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public Pos(int x, int y, int direction) {
			this.x = x;
			this.y = y;
			this.direction = direction;
		}

		@Override
		public String toString() {
			return "Pos [x=" + x + ", y=" + y + ", direction=" + direction + "]";
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		sb = new StringBuilder();
		direction.put(UP, new int[] { -1, 0 });
		direction.put(DOWN, new int[] { 1, 0 });
		direction.put(LEFT, new int[] { 0, -1 });
		direction.put(RIGHT, new int[] { 0, 1 });

		st = new StringTokenizer(br.readLine().trim());
		mapRow = Integer.parseInt(st.nextToken());
		mapCol = Integer.parseInt(st.nextToken());
		map = new char[mapRow + 1][mapCol + 1];
		Pos startPos = null;

		for (int row = 1; row <= mapRow; row++) {
			char[] line = br.readLine().trim().toCharArray();
			for (int col = 1; col <= mapCol; col++) {
				if (line[col - 1] == 'M') {
					startPos = new Pos(row, col);
				}
				map[row][col] = line[col - 1];
			}
		}
		startPos.direction = getDirection(startPos);
		connect(startPos);

		System.out.print(sb);
	}

	static int getDirection(Pos pos) {
		List<Integer> cand = new ArrayList<>();

		for (int key : direction.keySet()) {
			int[] dir = direction.get(key);
			int nx = pos.x + dir[0];
			int ny = pos.y + dir[1];
			if (nx < 1 || nx > mapRow || ny < 1 || ny > mapCol) {
				continue;
			}
			if (map[nx][ny] != '.') {
				cand.add(key);
			}

			if (key == UP) {
				if (map[nx][ny] == '|' || map[nx][ny] == '+' || map[nx][ny] == '1' || map[nx][ny] == '4')
					return key;
			} else if (key == DOWN) {
				if (map[nx][ny] == '|' || map[nx][ny] == '+' || map[nx][ny] == '2' || map[nx][ny] == '3')
					return key;
			} else if (key == LEFT) {
				if (map[nx][ny] == '-' || map[nx][ny] == '+' || map[nx][ny] == '1' || map[nx][ny] == '2')
					return key;
			} else {
				return key;
			}
		}

		return -1;
	}

	static void connect(Pos pos) {
		int[] dir = direction.get(pos.direction);
		int nx = pos.x + dir[0];
		int ny = pos.y + dir[1];

		if (nx < 1 || nx > mapRow || ny < 1 || ny > mapCol) {
			return;
		}

		if (map[nx][ny] == '.') {
			char road = findCorrectRoad(new Pos(nx, ny));
			sb.append(String.format("%d %d %c\n", nx, ny, road));

			return;
		}

		if (pos.direction == UP) {
			if (map[nx][ny] == '|' || map[nx][ny] == '+')
				connect(new Pos(nx, ny, UP));
			else if (map[nx][ny] == '1')
				connect(new Pos(nx, ny, RIGHT));
			else if (map[nx][ny] == '4')
				connect(new Pos(nx, ny, LEFT));
		} else if (pos.direction == DOWN) {
			if (map[nx][ny] == '|' || map[nx][ny] == '+')
				connect(new Pos(nx, ny, DOWN));
			else if (map[nx][ny] == '2')
				connect(new Pos(nx, ny, RIGHT));
			else if (map[nx][ny] == '3')
				connect(new Pos(nx, ny, LEFT));
		} else if (pos.direction == LEFT) {
			if (map[nx][ny] == '-' || map[nx][ny] == '+')
				connect(new Pos(nx, ny, LEFT));
			else if (map[nx][ny] == '1')
				connect(new Pos(nx, ny, DOWN));
			else if (map[nx][ny] == '2')
				connect(new Pos(nx, ny, UP));
		} else if (pos.direction == RIGHT) {
			if (map[nx][ny] == '-' || map[nx][ny] == '+')
				connect(new Pos(nx, ny, RIGHT));
			else if (map[nx][ny] == '3')
				connect(new Pos(nx, ny, UP));
			else if (map[nx][ny] == '4')
				connect(new Pos(nx, ny, DOWN));
		}
	}

	static boolean canConnect(char road, int direction) {
		if (direction == UP || direction == DOWN) {
			if (road == '|' || road == '+')
				return true;
			if (direction == UP) {
				if (road == '1' || road == '4')
					return true;
			} else {
				if (road == '2' || road == '3')
					return true;
			}
		} else {
			if (road == '-' || road == '+')
				return true;
			if (direction == LEFT) {
				if (road == '1' || road == '2')
					return true;
			} else {
				if (road == '3' || road == '4')
					return true;
			}
		}

		return false;
	}

	static char findCorrectRoad(Pos pos) {
		char result = ' ';
		List<Integer> connected = new ArrayList<>();

		for (int key : direction.keySet()) {
			int[] dir = direction.get(key);
			int nx = pos.x + dir[0];
			int ny = pos.y + dir[1];
			if (nx < 1 || nx > mapRow || ny < 1 || ny > mapCol) {
				continue;
			}
			if (canConnect(map[nx][ny], key)) {
				connected.add(key);
			}
		}

		if (connected.size() == 4)
			result = '+';
		else {
			int first = connected.get(0);
			int second = connected.get(1);

			if (first == UP || second == UP) {
				if (first == DOWN || second == DOWN)
					result = '|';
				else if (first == LEFT || second == LEFT)
					result = '3';
				else if (first == RIGHT || second == RIGHT)
					result = '2';
			} else if (first == DOWN || second == DOWN) {
				if (first == LEFT || second == LEFT)
					result = '4';
				else if (first == RIGHT || second == RIGHT)
					result = '1';
			} else
				result = '-';
		}

		return result;
	}
}
