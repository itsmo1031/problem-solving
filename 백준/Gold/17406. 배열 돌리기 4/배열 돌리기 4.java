import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[][] arr, temp;
	static int N, M, K;
	static int startX, startY, endX, endY;
	static boolean[][] visited;
	static final int[][] DIR = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

	static int getMin() {
		int min = Integer.MAX_VALUE;

		for (int row = 0; row < N; row++) {
			int sum = 0;
			for (int col = 0; col < M; col++) {
				sum += temp[row][col];
			}
			min = Math.min(min, sum);
		}

		return min;
	}

	static void rotate(int row, int col, int size) {
		startX = row - size;
		startY = col - size;
		endX = row + size;
		endY = col + size;
		int maxDepth = Math.min(endX - startX + 1, endY - startY + 1) / 2;
		visited = new boolean[N][M];

		for (int depth = 0; depth < maxDepth; depth++) {
			change(startX + depth, startY + depth, temp[startX + depth][startY + depth], 0); // 값 변경(회전)
		}
	}

	static void change(int x, int y, int data, int direction) {
		if (direction == 4) {
			return;
		}

		int nx = x + DIR[direction][0];
		int ny = y + DIR[direction][1];

		if (nx < startX || nx > endX || ny < startY || ny > endY || visited[nx][ny]) {
			direction += 1;
			change(x, y, data, direction);
			return;
		}
		visited[nx][ny] = true;
		int nextData = temp[nx][ny];
		temp[nx][ny] = data;
		change(nx, ny, nextData, direction);
	}

	static int[][] deepcopy(int[][] arr) {
		int[][] result = new int[N][M];

		for (int row = 0; row < N; row++) {
			result[row] = Arrays.copyOf(arr[row], M);
		}

		return result;
	}

	static boolean nextPermutation(int[] array) {
		int N = array.length;
		int top = N - 1;

		while (top > 0 && array[top - 1] >= array[top])
			--top;

		if (top == 0)
			return false;

		int bigger = N - 1;
		while (array[bigger] <= array[top - 1])
			--bigger;

		int temp = array[top - 1];
		array[top - 1] = array[bigger];
		array[bigger] = temp;

		Arrays.sort(array, top, N);

		return true;
	}

	static class Command {
		int r;
		int c;
		int s;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		arr = new int[N][M];

		for (int row = 0; row < N; row++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int col = 0; col < M; col++) {
				arr[row][col] = Integer.parseInt(st.nextToken());
			}
		}

		int[] seq = new int[K];
		Command[] cmd = new Command[K];

		for (int idx = 0; idx < K; idx++) {
			st = new StringTokenizer(br.readLine().trim());
			Command newCmd = new Command();
			newCmd.r = Integer.parseInt(st.nextToken()) - 1;
			newCmd.c = Integer.parseInt(st.nextToken()) - 1;
			newCmd.s = Integer.parseInt(st.nextToken());
			cmd[idx] = newCmd;
			seq[idx] = idx;
		}

		int answer = Integer.MAX_VALUE;

		do {
			temp = deepcopy(arr);
			for (int sequence : seq) {
				rotate(cmd[sequence].r, cmd[sequence].c, cmd[sequence].s);
			}
			answer = Math.min(answer, getMin());
		} while (nextPermutation(seq));

		System.out.println(answer);
	}
}
