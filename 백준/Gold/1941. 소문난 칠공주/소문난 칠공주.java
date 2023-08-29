import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main {
    static final int TOTAL = 7, LENGTH = 5;
    static final int[][] DIR = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
    static char[][] classroom;

    static boolean nextPermutation(int[] arr) {
        int top = arr.length - 1;
        while (top > 0 && arr[top - 1] >= arr[top])
            --top;

        if (top == 0)
            return false;

        int target = arr.length - 1;

        while (arr[top - 1] >= arr[target])
            --target;

        int temp = arr[top - 1];
        arr[top - 1] = arr[target];
        arr[target] = temp;

        Arrays.sort(arr, top, arr.length);

        return true;
    }

    static int[] getSelectedIdx(int[] flag) {
        int[] result = new int[TOTAL];
        int index = 0;
        for (int idx = 0; idx < flag.length; idx++) {
            if (flag[idx] == 1)
                result[index++] = idx;
        }
        return result;
    }

    static boolean isPossible(int[] target) {
        boolean[][] visited = new boolean[LENGTH][LENGTH];
        visited[target[0] / LENGTH][target[0] % LENGTH] = true;
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(target[0]);
        int yGroupCnt = 0, count = 0;

        while (!q.isEmpty()) {
            int now = q.poll();
            int nowX = now / LENGTH;
            int nowY = now % LENGTH;

            count += 1;
            if (classroom[nowX][nowY] == 'Y')
                yGroupCnt += 1;

            if (yGroupCnt > 3)
                return false;

            for (int[] dir : DIR) {
                int nx = nowX + dir[0];
                int ny = nowY + dir[1];
                int next = nx * LENGTH + ny;

                if (nx < 0 || nx >= LENGTH || ny < 0 || ny >= LENGTH || visited[nx][ny])
                    continue;

                for (int num : target) {
                    if (num == next) {
                        visited[nx][ny] = true;
                        q.offer(next);
                    }
                }
            }
        }

        return count == 7 ? true : false;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        classroom = new char[LENGTH][LENGTH];

        for (int row = 0; row < LENGTH; row++) {
            classroom[row] = br.readLine().trim().toCharArray();
        }

        int[] flag = new int[LENGTH * LENGTH];
        for (int idx = 1; idx <= TOTAL; idx++) {
            flag[LENGTH * LENGTH - idx] = 1;
        }

        int answer = 0;

        do {
            int[] target = getSelectedIdx(flag);
            if (isPossible(target))
                answer += 1;
        } while (nextPermutation(flag));

        System.out.println(answer);
    }

}