import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int studentCnt, countTime;
	static List<Integer>[] student;
	static int[] indegree;
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		sb = new StringBuilder();

		studentCnt = Integer.parseInt(st.nextToken());
		countTime = Integer.parseInt(st.nextToken());

		student = new ArrayList[studentCnt + 1];
		indegree = new int[studentCnt + 1];

		for (int idx = 1; idx <= studentCnt; idx++) {
			student[idx] = new ArrayList<>();
		}

		for (int cnt = 0; cnt < countTime; cnt++) {
			st = new StringTokenizer(br.readLine().trim());
			int tiny = Integer.parseInt(st.nextToken());
			int tall = Integer.parseInt(st.nextToken());
			student[tiny].add(tall);
			indegree[tall] += 1;
		}

		sort();

		System.out.println(sb);
	}

	static void sort() {
		boolean[] visited = new boolean[studentCnt + 1];
		Queue<Integer> q = new ArrayDeque<>();

		for (int idx = 1; idx <= studentCnt; idx++) {
			if (indegree[idx] == 0) {
				visited[idx] = true;
				q.offer(idx);
			}
		}

		while (!q.isEmpty()) {
			int now = q.poll();
			sb.append(now + " ");
			for (int target : student[now]) {
				if (--indegree[target] == 0 && !visited[target]) {
					visited[target] = true;
					q.offer(target);
				}
			}
		}
	}
}
