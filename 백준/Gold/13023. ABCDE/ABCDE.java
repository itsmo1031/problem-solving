import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static List<List<Integer>> friend;
	static boolean[] visited;
	static int find;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		int peopleCnt = Integer.parseInt(st.nextToken());
		int relationCnt = Integer.parseInt(st.nextToken());
		friend = new ArrayList<>();

		for (int idx = 0; idx < peopleCnt; idx++) {
			friend.add(new ArrayList<>());
		}

		for (int idx = 0; idx < relationCnt; idx++) {
			st = new StringTokenizer(br.readLine().trim());
			int friend1 = Integer.parseInt(st.nextToken());
			int friend2 = Integer.parseInt(st.nextToken());

			friend.get(friend1).add(friend2);
			friend.get(friend2).add(friend1);
		}

		visited = new boolean[peopleCnt];

		for (int idx = 0; idx < peopleCnt; idx++) {
			if (find == 1)
				break;
			findRelationship(idx, 1);
		}

		System.out.println(find);
	}

	static void findRelationship(int no, int depth) {
		if (depth == 5) {
			find = 1;
			return;
		}

		for (int next : friend.get(no)) {
			if (!visited[next] && find != 1) {
				visited[no] = true;
				findRelationship(next, depth + 1);
				visited[no] = false;
			}
		}
	}

}
