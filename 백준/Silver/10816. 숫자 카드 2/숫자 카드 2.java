import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[] cards;
    static int cardCnt;
    static int[] searchList;
    static int searchCnt;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        cardCnt = Integer.parseInt(br.readLine().trim());
        cards = new int[cardCnt];

        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        for (int idx = 0; idx < cardCnt; idx++) {
            cards[idx] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(cards);

        searchCnt = Integer.parseInt(br.readLine().trim());
        searchList = new int[searchCnt];

        st = new StringTokenizer(br.readLine().trim());

        for (int idx = 0; idx < searchCnt; idx++) {
            searchList[idx] = Integer.parseInt(st.nextToken());
        }

        StringBuilder sb = new StringBuilder();

        for (int res : search()) {
            sb.append(res).append(" ");
        }

        System.out.println(sb.toString().trim());
    }

    static int[] search() {
        int[] result = new int[searchCnt];

        for (int idx = 0; idx < searchCnt; idx++) {
            int last = searchLastIdx(searchList[idx], 0, cardCnt - 1);
            int first = searchFirstIdx(searchList[idx], 0, cardCnt - 1);
            if (first == -1)
                result[idx] = 0;
            else
                result[idx] = last - first + 1;
        }

        return result;
    }

    static int searchFirstIdx(int target, int start, int end) {
        if (start > end)
            return -1;
        int mid = (start + end) / 2;

        if ((mid == 0 || target > cards[mid - 1]) && cards[mid] == target) {
            return mid;
        } else if (cards[mid] >= target) {
            return searchFirstIdx(target, start, mid - 1);
        } else {
            return searchFirstIdx(target, mid + 1, end);
        }
    }

    static int searchLastIdx(int target, int start, int end) {
        if (start > end)
            return -1;
        int mid = (start + end) / 2;

        if ((mid == cardCnt - 1 || target < cards[mid + 1]) && cards[mid] == target) {
            return mid;
        } else if (cards[mid] > target) {
            return searchLastIdx(target, start, mid - 1);
        } else {
            return searchLastIdx(target, mid + 1, end);
        }
    }
}