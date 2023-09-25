import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[] cards;
    static int cardCnt;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        cardCnt = Integer.parseInt(br.readLine().trim());
        cards = new int[cardCnt];
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        for (int idx = 0; idx < cardCnt; idx++) {
            cards[idx] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(cards);

        int targetCnt = Integer.parseInt(br.readLine().trim());
        st = new StringTokenizer(br.readLine().trim());

        StringBuilder sb = new StringBuilder();

        for (int idx = 0; idx < targetCnt; idx++) {
            int target = Integer.parseInt(st.nextToken());

            sb.append(Arrays.binarySearch(cards, target) < 0 ? 0 : 1).append(" ");
        }

        System.out.println(sb.toString().trim());
    }
}