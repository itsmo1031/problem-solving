import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	static Deque<String> password, DNA;
	static HashMap<String, Integer> DNAMap, passMap;
	static final String[] SEQ = { "A", "C", "G", "T" };

	static int getAvailablePassword() {
		int result = 0;

		do {
			boolean available = true;
			for (String key : SEQ) {
				if (passMap.getOrDefault(key, 0) < DNAMap.getOrDefault(key, 0)) {
					available = false;
					break;
				}
			}
			if (available)
				result += 1;
		} while (getNext());

		return result;
	}

	static boolean getNext() {
		if (DNA.isEmpty())
			return false;
		String next = DNA.poll();
		passMap.put(next, passMap.getOrDefault(next, 0) + 1);
		password.offer(next);
		String remove = password.poll();
		passMap.put(remove, passMap.get(remove) - 1);
		return true;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());

		int DNALength = Integer.parseInt(st.nextToken());
		int passLength = Integer.parseInt(st.nextToken());
		password = new ArrayDeque<>();
		passMap = new HashMap<>();
		DNA = new ArrayDeque<>();

		st = new StringTokenizer(br.readLine().trim());
		for (char ch : st.nextToken().trim().toCharArray()) {
			String current = String.valueOf(ch);
			if (password.size() < passLength) {
				password.add(current);
				passMap.put(current, passMap.getOrDefault(current, 0) + 1);
			} else {
				DNA.add(current);
			}
		}

		DNAMap = new HashMap<>();
		st = new StringTokenizer(br.readLine().trim());
		for (String dna : SEQ) {
			DNAMap.put(dna, Integer.parseInt(st.nextToken()));
		}

		System.out.println(getAvailablePassword());
	}
}
