import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main {
	static int[][] arr;
	static int arrRow, arrCol;

	static void rowInvert() {
		int[][] newArr = new int[arrRow][arrCol];

		for (int row = 0; row < arrRow; row++) {
			newArr[row] = Arrays.copyOf(arr[arrRow - 1 - row], arrCol);
		}

		arr = newArr;
	}

	static void colInvert() {
		int[][] newArr = new int[arrRow][arrCol];

		for (int row = 0; row < arrRow; row++) {
			for (int col = 0; col < arrCol; col++) {
				newArr[row][col] = arr[row][arrCol - col - 1];
			}
		}

		arr = newArr;
	}

	static void rot90() {
		int[][] newArr = new int[arrCol][arrRow];

		for (int row = 0; row < arrCol; row++) {
			for (int col = 0; col < arrRow; col++) {
				newArr[row][col] = arr[arrRow - col - 1][row];
			}
		}

		int temp = arrRow;
		arrRow = arrCol;
		arrCol = temp;

		arr = newArr;
	}

	static void rot270() {
		int[][] newArr = new int[arrCol][arrRow];

		for (int row = 0; row < arrCol; row++) {
			for (int col = 0; col < arrRow; col++) {
				newArr[row][col] = arr[col][arrCol - row - 1];
			}
		}

		int temp = arrRow;
		arrRow = arrCol;
		arrCol = temp;

		arr = newArr;
	}

	static void groupRotate() {
		int[][][] splitted = split();

		combine(splitted[3], splitted[0], splitted[1], splitted[2]);
	}

	static void groupSwitch() {
		int[][][] splitted = split();

		combine(splitted[1], splitted[2], splitted[3], splitted[0]);
	}

	static int[][][] split() {
		int[][] leftTop = new int[arrRow / 2][arrCol / 2];
		int[][] rightTop = new int[arrRow / 2][arrCol / 2];
		int[][] leftBottom = new int[arrRow / 2][arrCol / 2];
		int[][] rightBottom = new int[arrRow / 2][arrCol / 2];

		for (int row = 0; row < arrRow / 2; row++) {
			for (int col = 0; col < arrCol / 2; col++) {
				leftTop[row][col] = arr[row][col];
			}
			for (int col = arrCol / 2; col < arrCol; col++) {
				rightTop[row][col - arrCol / 2] = arr[row][col];
			}
		}
		for (int row = arrRow / 2; row < arrRow; row++) {
			for (int col = 0; col < arrCol / 2; col++) {
				leftBottom[row - arrRow / 2][col] = arr[row][col];
			}
			for (int col = arrCol / 2; col < arrCol; col++) {
				rightBottom[row - arrRow / 2][col - arrCol / 2] = arr[row][col];
			}
		}

		return new int[][][] { leftTop, rightTop, rightBottom, leftBottom };
	}

	static void combine(int[][] leftTop, int[][] rightTop, int[][] rightBottom, int[][] leftBottom) {
		int[][] newArr = new int[arrRow][arrCol];

		for (int row = 0; row < arrRow / 2; row++) {
			for (int col = 0; col < arrCol / 2; col++) {
				newArr[row][col] = leftTop[row][col];
			}
			for (int col = arrCol / 2; col < arrCol; col++) {
				newArr[row][col] = rightTop[row][col - arrCol / 2];
			}
		}
		for (int row = arrRow / 2; row < arrRow; row++) {
			for (int col = 0; col < arrCol / 2; col++) {
				newArr[row][col] = leftBottom[row - arrRow / 2][col];
			}
			for (int col = arrCol / 2; col < arrCol; col++) {
				newArr[row][col] = rightBottom[row - arrRow / 2][col - arrCol / 2];
			}
		}

		arr = newArr;
	}

	static void print() {
		StringBuilder sb = new StringBuilder();

		for (int[] line : arr) {
			sb.append(Arrays.stream(line).mapToObj(String::valueOf).collect(Collectors.joining(" "))).append("\n");
		}

		System.out.print(sb);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());

		arrRow = Integer.parseInt(st.nextToken());
		arrCol = Integer.parseInt(st.nextToken());
		int cmdCnt = Integer.parseInt(st.nextToken());
		arr = new int[arrRow][arrCol];

		for (int row = 0; row < arrRow; row++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int col = 0; col < arrCol; col++) {
				arr[row][col] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine().trim());
		for (int cnt = 0; cnt < cmdCnt; cnt++) {
			int cmd = Integer.parseInt(st.nextToken());

			switch (cmd) {
			case 1:
				rowInvert();
				break;
			case 2:
				colInvert();
				break;
			case 3:
				rot90();
				break;
			case 4:
				rot270();
				break;
			case 5:
				groupRotate();
				break;
			case 6:
				groupSwitch();
				break;
			}
		}

		print();
	}
}
