import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] num;
	static int limit;
	static int[] permArr;
	static boolean[] visited;
	static StringBuilder sb = new StringBuilder();

	static void permutate(int count) {
		if (count == limit) {
			for (int elem : permArr) {
				sb.append(elem).append(" ");
			}
			sb.append("\n");
			return;
		}
		for (int index = 0; index < num.length; index++) {
			if (visited[index])
				continue;
			permArr[count] = num[index];
			visited[index] = true;
			permutate(count + 1);
			visited[index] = false;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int numCnt = Integer.parseInt(st.nextToken());
		num = new int[numCnt];
		for (int i = 0; i < numCnt; i++) {
			num[i] = i + 1;
		}
		visited = new boolean[numCnt];
		limit = Integer.parseInt(st.nextToken());
		permArr = new int[limit];

		permutate(0);
		System.out.print(sb);
	}

}