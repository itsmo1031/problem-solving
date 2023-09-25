import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int mapRow, mapCol;
    static int[][] map, original;
    static List<Pos> candList, virusList;
    static final int[][] DIR = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

    static class Pos {
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        mapRow = Integer.parseInt(st.nextToken());
        mapCol = Integer.parseInt(st.nextToken());

        original = new int[mapRow][mapCol];
        candList = new ArrayList<>();
        virusList = new ArrayList<>();

        for (int row = 0; row < mapRow; row++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int col = 0; col < mapCol; col++) {
                int data = Integer.parseInt(st.nextToken());
                if (data == 0) {
                    candList.add(new Pos(row, col));
                } else if (data == 2) {
                    virusList.add(new Pos(row, col));
                }
                original[row][col] = data;
            }
        }

        int[] flag = new int[candList.size()];

        for (int idx = flag.length - 1; idx > flag.length - 4; idx--) {
            flag[idx] = 1;
        }

        int result = 0;

        do {
            map = setWall(flag);
            result = Math.max(getSafeArea(), result);
        } while (nextPermutation(flag));

        System.out.println(result);
    }

    static int getSafeArea() {
        spread();

        int cnt = 0;

        for (int row = 0; row < mapRow; row++) {
            for (int col = 0; col < mapCol; col++) {
                if (map[row][col] == 0)
                    cnt += 1;
            }
        }

        return cnt;
    }

    static void spread() {
        Queue<Pos> q = new ArrayDeque<>(virusList);

        while (!q.isEmpty()) {
            Pos now = q.poll();

            for (int[] dir : DIR) {
                int nx = now.x + dir[0];
                int ny = now.y + dir[1];

                if (nx < 0 || nx >= mapRow || ny < 0 || ny >= mapCol || map[nx][ny] != 0)
                    continue;

                map[nx][ny] = 1;
                q.offer(new Pos(nx, ny));
            }

        }
    }

    static int[][] setWall(int[] flag) {
        int[][] result = deepcopy(original);

        for (int idx = 0; idx < flag.length; idx++) {
            if (flag[idx] == 1) {
                Pos pos = candList.get(idx);
                result[pos.x][pos.y] = 1;
            }
        }

        return result;
    }

    static boolean nextPermutation(int[] arr) {
        int top = arr.length - 1;
        while (top > 0 && arr[top - 1] >= arr[top])
            top--;

        if (top == 0)
            return false;

        int target = arr.length - 1;
        while (arr[top - 1] >= arr[target])
            target--;

        int temp = arr[top - 1];
        arr[top - 1] = arr[target];
        arr[target] = temp;

        Arrays.sort(arr, top, arr.length);

        return true;
    }

    static int[][] deepcopy(int[][] original) {
        int[][] result = new int[mapRow][mapCol];

        for (int row = 0; row < mapRow; row++) {
            result[row] = Arrays.copyOf(original[row], mapCol);
        }

        return result;
    }

}
