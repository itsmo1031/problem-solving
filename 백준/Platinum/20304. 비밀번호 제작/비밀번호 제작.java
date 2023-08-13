import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int maxPassLength;
    static int loginPassCnt;
    static Queue<Integer> q = new ArrayDeque<>();
    static int[] safety;

    static int getMaxPassIdx(int max) {
        int idx = 0;

        do {
            max >>= 1;
            idx += 1;
        } while (max != 0);

        return idx;
    }

    static int findMaxSafety() {
        int maxPassIdx = getMaxPassIdx(maxPassLength);
        int answer = 0;

        while (!q.isEmpty()) {
            int now = q.poll();
            for (int idx = 0; idx < maxPassIdx; idx++) {
                int cand = now ^ (1 << idx);
                if (cand > maxPassLength || safety[cand] != -1)
                    continue;
                safety[cand] = safety[now] + 1;
                q.offer(cand);
                answer = safety[cand];
            }
        }

        return answer;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        maxPassLength = Integer.parseInt(br.readLine().trim());
        loginPassCnt = Integer.parseInt(br.readLine().trim());
        safety = new int[maxPassLength + 1];
        Arrays.fill(safety, -1);
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        for (int idx = 0; idx < loginPassCnt; idx++) {
            int data = Integer.parseInt(st.nextToken());
            q.offer(data);
            safety[data] = 0;
        }
        System.out.println(findMaxSafety());
    }
}