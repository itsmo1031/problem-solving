import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine().trim());

        Queue<String> q = new PriorityQueue<>((s1, s2) -> {
            if (s1.length() < s2.length())
                return -1;
            else if (s1.length() == s2.length())
                return s1.compareTo(s2);
            return 1;
        });

        Set<String> set = new HashSet<>();

        for (int idx = 0; idx < size; idx++) {
            set.add(br.readLine().trim());
        }

        q.addAll(set);

        StringBuilder sb = new StringBuilder();
        while (!q.isEmpty()) {
            sb.append(q.poll()).append("\n");
        }

        System.out.println(sb.toString().trim());
    }
}