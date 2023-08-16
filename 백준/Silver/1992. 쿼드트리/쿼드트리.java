import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	static int size;
	static int[][] movie;
	static List<Character> compressed;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		size = Integer.parseInt(br.readLine().trim());
		compressed = new ArrayList<>();
		movie = new int[size][size];
		for (int row = 0; row < size; row++) {
			String data = br.readLine().trim();
			for (int col = 0; col < size; col++) {
				movie[row][col] = data.charAt(col) - '0';
			}
		}

		quadtree(0, 0, size);

		StringBuilder sb = new StringBuilder();

		for (char ch : compressed) {
			sb.append(ch);
		}
		System.out.println(sb);
	}

	static void quadtree(int x, int y, int size) {
		int result = check(x, y, size);
		if (result == 0) {
			compressed.add('0');
			return;
		} else if (result == 1) {
			compressed.add('1');
			return;
		}

		int newSize = size >> 1;
		compressed.add('(');
		quadtree(x, y, newSize);
		quadtree(x, y + newSize, newSize);
		quadtree(x + newSize, y, newSize);
		quadtree(x + newSize, y + newSize, newSize);
		compressed.add(')');
	}

	static int check(int x, int y, int size) {
		int zero = 0;
		int one = 0;
		for (int row = x; row < x + size; row++) {
			for (int col = y; col < y + size; col++) {
				if (movie[row][col] == 0)
					zero += 1;
				else
					one += 1;
				if (zero != 0 && one != 0)
					return -1;
			}
		}

		return zero > one ? 0 : 1;
	}
}
