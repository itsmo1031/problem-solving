import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(br.readLine().trim());

        Set<String> set = new HashSet<>();
        StringTokenizer st;

        for (int idx = 0; idx < count; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            String name = st.nextToken();
            String status = st.nextToken();
            if (status.equals("enter")) {
                set.add(name);
            } else {
                set.remove(name);
            }
        }

        set.stream()
                .sorted(Comparator.reverseOrder())
                .forEach(System.out::println);
    }
}