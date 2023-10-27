import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Map.Entry;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine().trim());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        List<String> list = new ArrayList<>();

        for (int tc = 0; tc < testCase; tc++) {
            st = new StringTokenizer(br.readLine().trim());
            while (st.hasMoreTokens()) {
                String next = st.nextToken();
                list.add(next);
            }
            Stream<String> stream = list.stream();
            while (true) {
                String str = br.readLine().trim();

                if (str.equals("what does the fox say?"))
                    break;

                String say = str.split(" ")[2];

                stream = stream.filter(sound -> !say.equals(sound));
            }

            for (String str : stream.collect(Collectors.toList())) {
                sb.append(str).append(" ");
            }

            System.out.println(sb.toString().trim());
            sb.setLength(0);

            list.clear();
        }

    }
}