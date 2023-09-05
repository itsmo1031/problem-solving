import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int stone = Integer.parseInt(br.readLine().trim());

        System.out.println(stone % 2 == 0 ? "SK" : "CY");
    }
}