import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static List<List<Integer>> district;
	static int[] population;
	static int distCnt;
	static int[] select;
	static int answer;

	static void init() {
		population = new int[distCnt + 1];
		district = new ArrayList<>();
		for (int cnt = 0; cnt <= distCnt; cnt++) {
			district.add(new ArrayList<>());
		}
	}

	static int gerrymandering() {
		answer = Integer.MAX_VALUE;

		for (int cnt = 1; cnt <= distCnt / 2; cnt++) {
			select = new int[cnt];
			combination(0, 1, cnt);
		}

		return answer == Integer.MAX_VALUE ? -1 : answer;
	}

	static void combination(int cnt, int start, int targetCnt) {
		if (cnt == targetCnt) {
			if (isPossible()) {
				int cntA = 0, cntB = 0;
				int selectCnt = 0;
				for (int idx = 1; idx <= distCnt; idx++) {
					if (selectCnt < select.length && select[selectCnt] == idx) {
						cntA += population[idx];
						selectCnt += 1;
					} else
						cntB += population[idx];
				}
				answer = Math.min(answer, Math.abs(cntA - cntB));
			}
			return;
		}

		for (int idx = start; idx <= distCnt; idx++) {
			select[cnt] = idx;
			combination(cnt + 1, idx + 1, targetCnt);
		}
	}

	static boolean isPossible() {
		List<Integer> distA = new ArrayList<>();
		List<Integer> distB = new ArrayList<>();
		int selectCnt = 0;
		for (int idx = 1; idx <= distCnt; idx++) {
			if (selectCnt < select.length && select[selectCnt] == idx) {
				distA.add(idx);
				selectCnt += 1;
			} else
				distB.add(idx);
		}
		return bfs(distA) && bfs(distB);
	}

	static boolean bfs(List<Integer> dist) {
		boolean[] visited = new boolean[distCnt + 1];
		int visitCnt = 0;
		int start = dist.get(0);
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(start);
		visited[start] = true;

		while (!q.isEmpty()) {
			int now = q.poll();

			visitCnt += 1;

			for (int next : district.get(now)) {
				if (dist.contains(next) && !visited[next]) {
					visited[next] = true;
					q.offer(next);
				}
			}
		}

		return visitCnt == dist.size() ? true : false;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		distCnt = Integer.parseInt(br.readLine().trim());
		init();

		StringTokenizer st = new StringTokenizer(br.readLine().trim());

		for (int idx = 1; idx <= distCnt; idx++) {
			population[idx] = Integer.parseInt(st.nextToken());
		}

		for (int dist = 1; dist <= distCnt; dist++) {
			st = new StringTokenizer(br.readLine().trim());
			int nearCnt = Integer.parseInt(st.nextToken());
			for (int cnt = 0; cnt < nearCnt; cnt++) {
				int near = Integer.parseInt(st.nextToken());
				district.get(dist).add(near);
			}
		}

		System.out.println(gerrymandering());
	}

}
