import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main {
    static Map<String, Team> ranking;
    static int teamCnt, finalCnt;

    static class Team implements Comparable<Team> {
        String name;
        int solved;
        int penalty;

        public Team(String name, int solved, int penalty) {
            this.name = name;
            this.solved = solved;
            this.penalty = penalty;
        }

        @Override
        public int compareTo(Team o) {
            int compare = -Integer.compare(this.solved, o.solved);
            return compare != 0 ? compare : Integer.compare(this.penalty, o.penalty);
        }

        static Team max(Team t1, Team t2) {
            return t1.compareTo(t2) < 0 ? t1 : t2;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        teamCnt = Integer.parseInt(st.nextToken());
        finalCnt = Integer.parseInt(st.nextToken());

        ranking = new HashMap<>();

        for (int idx = 0; idx < teamCnt; idx++) {
            st = new StringTokenizer(br.readLine().trim());

            String univ = st.nextToken();

            String teamName = st.nextToken();
            int solved = Integer.parseInt(st.nextToken());
            int penalty = Integer.parseInt(st.nextToken());

            Team team = new Team(teamName, solved, penalty);

            if (ranking.containsKey(univ)) {
                ranking.put(univ, Team.max(ranking.get(univ), team));
            } else {
                ranking.put(univ, team);
            }
        }

        Queue<Team> results = ranking.values().stream().collect(Collectors.toCollection(PriorityQueue::new));

        StringBuilder sb = new StringBuilder();
        for (int idx = 0; idx < finalCnt; idx++) {
            sb.append(results.poll().name).append('\n');
        }
        System.out.print(sb);
    }
}