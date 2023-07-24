import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static class Egg {
        int durability;
        int weight;

        public Egg(int durability, int weight) {
            this.durability = durability;
            this.weight = weight;
        }

        boolean isBroken() {
            return this.durability <= 0;
        }
    }

    static void bump(int index) {
        if (index == N) {
            renew();
            return;
        }
        if (egg[index].isBroken()) {
            bump(index + 1);
            return;
        }
        for (int target = 0; target < N; target++) {
            if (target == index || egg[target].isBroken())
                continue;
            egg[index].durability -= egg[target].weight;
            egg[target].durability -= egg[index].weight;
            bump(index + 1);
            egg[index].durability += egg[target].weight;
            egg[target].durability += egg[index].weight;
        }
        renew();
    }

    static void renew() {
        int cnt = 0;
        for (Egg e : egg) {
            if (e.isBroken()) {
                cnt += 1;
            }
        }
        answer = Integer.max(answer, cnt);
    }

    static int answer;
    static int N;
    static Egg[] egg;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        egg = new Egg[N];

        for (int index = 0; index < N; index++) {
            st = new StringTokenizer(br.readLine());
            int durability = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            egg[index] = new Egg(durability, weight);
        }

        bump(0);

        System.out.println(answer);
    }
}