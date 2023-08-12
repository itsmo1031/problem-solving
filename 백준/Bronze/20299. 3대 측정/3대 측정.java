import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        StringBuilder sb = new StringBuilder();
        int teamCnt = Integer.parseInt(st.nextToken());
        int teamSum = Integer.parseInt(st.nextToken());
        int minimum = Integer.parseInt(st.nextToken());
        List<Integer> team = new ArrayList<>();
        int total = 0;
        for (int idx = 0; idx < teamCnt; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            team.clear();
            for (int score = 0; score < 3; score++) {
                int now = Integer.parseInt(st.nextToken());
                if (now >= minimum)
                    team.add(now);
            }
            if (team.size() == 3 && teamSum <= team.stream().reduce(0, Integer::sum)) {
                for (int score : team) {
                    sb.append(score).append(" ");
                }
                total += 1;
            }
        }
        System.out.println(total);
        System.out.println(sb);
    }
}