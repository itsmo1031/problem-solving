import java.io.*;
import java.util.*;

public class Main {
	static boolean[] visited;
	static List<Integer>[] graph;
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		sb = new StringBuilder();

		int nodes = Integer.parseInt(st.nextToken());
		int edges = Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(st.nextToken());

		graph = new ArrayList[nodes + 1];
		for (int idx = 0; idx <= nodes; idx++) {
			graph[idx] = new ArrayList<>();
		}

		for (int edge = 0; edge < edges; edge++) {
			st = new StringTokenizer(br.readLine().trim());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			graph[from].add(to);
			graph[to].add(from);
		}

		for (int idx = 1; idx <= nodes; idx++) {
			graph[idx].sort(Comparator.naturalOrder());
		}

		visited = new boolean[nodes + 1];
		dfs(start);
		sb.append("\n");

		Arrays.fill(visited, false);
		bfs(start);
		System.out.println(sb);
	}

	static void dfs(int node) {
		visited[node] = true;
		sb.append(node + " ");

		for (int next : graph[node]) {
			if (!visited[next])
				dfs(next);
		}
	}

	static void bfs(int node) {
		Queue<Integer> q = new ArrayDeque<>();
		visited[node] = true;
		q.offer(node);

		while (!q.isEmpty()) {
			int now = q.poll();
			sb.append(now + " ");

			for (int next : graph[now]) {
				if (!visited[next]) {
					visited[next] = true;
					q.offer(next);
				}
			}
		}
	}
}
