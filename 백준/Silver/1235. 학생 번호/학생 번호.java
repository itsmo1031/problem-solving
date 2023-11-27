import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    static List<String> list;
    static int count;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        count = Integer.parseInt(br.readLine().trim());

        list = new ArrayList<>();

        for (int idx = 0; idx < count; idx++) {
            list.add(br.readLine().trim());
        }

        int maxSize = list.get(0).length();

        for (int size = 1; size < maxSize; size++) {
            if (isUnique(size)) {
                System.out.println(size);
                return;
            }
        }
        System.out.println(maxSize);
    }

    static boolean isUnique(int size) {
        int result = list.stream().map((elem) -> elem.substring(elem.length() - size))
                .collect(Collectors.toSet()).size();
        if (result == count) {
            return true;
        }
        return false;
    }
}