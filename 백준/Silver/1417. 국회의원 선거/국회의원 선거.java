import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    static int candidateCnt;
    static int currentVote;
    static PriorityQueue<Integer> vote;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        candidateCnt = Integer.parseInt(br.readLine().trim());
        vote = new PriorityQueue<>(Comparator.reverseOrder());
        currentVote = Integer.parseInt(br.readLine().trim());

        for (int idx = 0; idx < candidateCnt - 1; idx++) {
            vote.offer(Integer.parseInt(br.readLine().trim()));
        }

        System.out.println(buy());
    }

    static int buy() {
        int cnt = 0;
        if (candidateCnt == 1)
            return 0;

        while (true) {
            int now = vote.poll();
            if (currentVote > now)
                break;

            now -= 1;
            currentVote += 1;
            cnt += 1;

            vote.offer(now);
        }

        return cnt;
    }

}