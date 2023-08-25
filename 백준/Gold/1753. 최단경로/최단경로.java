import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static final int INF = Integer.MAX_VALUE;
	static int vertexCnt, edgeCnt;
	static int startIdx;
	static int[] distance;
	static List<List<Node>> nodeList;

	static class Node implements Comparable<Node> {
		int num, weight;

		public Node(int num, int weight) {
			this.num = num;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.weight, o.weight);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		vertexCnt = Integer.parseInt(st.nextToken());
		edgeCnt = Integer.parseInt(st.nextToken());

		startIdx = Integer.parseInt(br.readLine().trim());

		nodeList = new ArrayList<>();
		for (int idx = 0; idx <= vertexCnt; idx++) {
			nodeList.add(new ArrayList<>());
		}

		for (int idx = 0; idx < edgeCnt; idx++) {
			st = new StringTokenizer(br.readLine().trim());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			nodeList.get(from).add(new Node(to, weight));
		}

		distance = new int[vertexCnt + 1];
		Arrays.fill(distance, INF);

		dijkstra(startIdx);

		for (int idx = 1; idx <= vertexCnt; idx++) {
			System.out.println(distance[idx] == INF ? "INF" : distance[idx]);
		}
	}

	static void dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();

		distance[start] = 0;
		pq.add(new Node(start, 0));

		while (!pq.isEmpty()) {
			Node currentNode = pq.poll();
			int now = currentNode.num;
			int dist = currentNode.weight;

			if (distance[now] < dist)
				continue;

			for (Node next : nodeList.get(now)) {
				int cost = dist + next.weight;
				if (cost < distance[next.num]) {
					distance[next.num] = cost;
					pq.add(new Node(next.num, cost));
				}
			}
		}
	}

}
