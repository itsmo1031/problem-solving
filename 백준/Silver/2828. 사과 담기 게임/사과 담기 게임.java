import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static class Basket {
        int pos;
        int length;
        int move;

        public Basket(int length) {
            this.pos = 1;
            this.length = length;
        }

        public void moveTo(int target) {
            int right = pos + length - 1;
            if (pos <= target && target <= right) {
                return;
            }
            int moveCnt = 0;
            if (target < pos) {
                moveCnt = pos - target;
                pos -= moveCnt;
            }
            if (right < target) {
                moveCnt = target - right;
                pos += moveCnt;
            }
            move += moveCnt;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        st.nextToken();

        int length = Integer.parseInt(st.nextToken());
        Basket basket = new Basket(length);

        int appleCnt = Integer.parseInt(br.readLine().trim());
        for (int idx = 0; idx < appleCnt; idx++) {
            int pos = Integer.parseInt(br.readLine().trim());
            basket.moveTo(pos);
        }

        System.out.println(basket.move);
    }
}