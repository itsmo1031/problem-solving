import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int players;
    static boolean[] info;
    static int[] cards, results;
    static int maxNum;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        players = Integer.parseInt(br.readLine().trim());

        info = new boolean[1_000_001];
        cards = new int[players];
        results = new int[1_000_001];
        maxNum = 0;

        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        for (int idx = 0; idx < players; idx++) {
            int next = Integer.parseInt(st.nextToken());
            info[next] = true;
            cards[idx] = next;
            maxNum = Math.max(maxNum, next);
        }

        battle();

        StringBuilder sb = new StringBuilder();

        for (int card : cards) {
            sb.append(results[card]).append(" ");
        }

        System.out.println(sb.toString().trim());
    }

    static void battle() {
        for (int idx = 0; idx < players; idx++) {
            int no = cards[idx];
            for (int mul = no * 2; mul <= maxNum; mul += no) {
                if (info[mul]) {
                    results[no] += 1;
                    results[mul] -= 1;
                }
            }
        }
    }
}