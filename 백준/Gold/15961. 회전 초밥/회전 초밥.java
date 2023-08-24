import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static int[] rail;
	static int dishCnt, sushiCnt, eatCnt, coupon;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		dishCnt = Integer.parseInt(st.nextToken());
		sushiCnt = Integer.parseInt(st.nextToken());
		eatCnt = Integer.parseInt(st.nextToken());
		coupon = Integer.parseInt(st.nextToken());

		rail = new int[dishCnt];

		for (int idx = 0; idx < dishCnt; idx++) {
			int sushi = Integer.parseInt(br.readLine().trim());
			rail[idx] = sushi;
		}

		int answer = calc();
		System.out.println(answer);
	}

	static int calc() {
		Map<Integer, Integer> select = new HashMap<>();

		int start = 0;
		int temp = 0;

		for (int idx = 0; idx < eatCnt; idx++) {
			int now = select.getOrDefault(rail[idx], 0);

			if (now == 0)
				temp += 1;
			select.put(rail[idx], now + 1);
		}

		int result = temp;

		while (start < dishCnt - 1) {
			int delete = rail[start];
			int insert = rail[(start + eatCnt) % dishCnt];
			if (select.getOrDefault(delete, 0) == 1) {
				temp -= 1;
			}
			select.put(delete, select.getOrDefault(delete, 0) - 1);
			if (select.getOrDefault(insert, 0) == 0) {
				temp += 1;
			}
			select.put(insert, select.getOrDefault(insert, 0) + 1);
			result = select.getOrDefault(coupon, 0) == 0 ? Math.max(result, temp + 1) : Math.max(result, temp);
			start += 1;
		}

		return result;
	}
}
