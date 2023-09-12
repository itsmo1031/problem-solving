import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int maxDate, chapterCnt;
    static Chapter[] book;
    static int[] dp;

    static class Chapter {
        int day, page;

        public Chapter(int day, int page) {
            this.day = day;
            this.page = page;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        maxDate = Integer.parseInt(st.nextToken());
        chapterCnt = Integer.parseInt(st.nextToken());

        book = new Chapter[chapterCnt];
        dp = new int[maxDate + 1];

        for (int idx = 0; idx < chapterCnt; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            int day = Integer.parseInt(st.nextToken());
            int page = Integer.parseInt(st.nextToken());
            book[idx] = new Chapter(day, page);
        }

        for (Chapter chap : book) {
            for (int idx = maxDate; idx >= chap.day; idx--) {
                dp[idx] = Math.max(dp[idx - chap.day] + chap.page, dp[idx]);
            }
        }

        System.out.println(dp[maxDate]);
    }
}