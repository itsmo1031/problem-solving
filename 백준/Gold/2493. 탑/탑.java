import java.io.*;
import java.util.*;

public class Main {
    static class Tower {
        int height;
        int index;

        public Tower(int height, int index) {
            this.height = height;
            this.index = index;
        }
    }

    static List<Tower> tower;
    static Deque<Tower> target;
    static int[] result;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int towerCnt = Integer.parseInt(br.readLine().trim());
        tower = new ArrayList<>();
        result = new int[towerCnt];
        target = new ArrayDeque<>();
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        for (int idx = 0; idx < towerCnt; idx++) {
            tower.add(new Tower(Integer.parseInt(st.nextToken()), idx));
        }

        int idx = towerCnt - 1;
        while (idx >= 0) {
            Tower currentTower = tower.get(idx);
            if (target.isEmpty()) {
                target.offer(currentTower);
                idx -= 1;
                continue;
            }
            while (!target.isEmpty()) {
                Tower now = target.peekLast();
                if (currentTower.height <= now.height) {
                    target.offer(currentTower);
                    idx -= 1;
                    break;
                }
                result[now.index] = idx + 1;
                target.pollLast();
            }
        }

        while (target.isEmpty()) {
            result[target.poll().index] = 0;
        }

        StringBuilder sb = new StringBuilder();

        for (int res : result) {
            sb.append(res).append(" ");
        }

        System.out.println(sb);
    }
}