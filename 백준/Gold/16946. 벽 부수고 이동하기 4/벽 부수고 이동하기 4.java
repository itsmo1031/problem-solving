import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static int mapRow, mapCol;
    static final int WALL = 1, EMPTY = 0;
    static final int[][] DIR = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
    static int[][] map;
    static boolean[][] visited;
    static Room[][] memo;

    static class Pos {
        int r, c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static class Room {
        int no;
        int count;

        public Room(int no, int count) {
            this.no = no;
            this.count = count;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        mapRow = Integer.parseInt(st.nextToken());
        mapCol = Integer.parseInt(st.nextToken());

        map = new int[mapRow][mapCol];
        memo = new Room[mapRow][mapCol];

        for (int row = 0; row < mapRow; row++) {
            char[] line = br.readLine().toCharArray();
            for (int col = 0; col < mapCol; col++) {
                int data = line[col] - '0';
                map[row][col] = data;
            }
        }

        int roomNo = 1;
        visited = new boolean[mapRow][mapCol];

        for (int row = 0; row < mapRow; row++) {
            for (int col = 0; col < mapCol; col++) {
                if (map[row][col] == EMPTY && !visited[row][col]) {
                    countRoom(new Pos(row, col), roomNo++);
                }
            }
        }

        for (int row = 0; row < mapRow; row++) {
            for (int col = 0; col < mapCol; col++) {
                if (map[row][col] == WALL) {
                    map[row][col] = (map[row][col] + addArea(new Pos(row, col))) % 10;
                }
            }
        }

        printMap();
    }

    static void printMap() {
        StringBuilder sb = new StringBuilder();

        for (int row = 0; row < mapRow; row++) {
            for (int col = 0; col < mapCol; col++) {
                sb.append(map[row][col]);
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }

    static int addArea(Pos pos) {
        Set<Room> visited = new HashSet<>();

        for (int[] dir : DIR) {
            int nr = pos.r + dir[0];
            int nc = pos.c + dir[1];

            if (isOutside(nr, nc) || memo[nr][nc] == null) {
                continue;
            }

            visited.add(memo[nr][nc]);
        }

        return visited.stream().mapToInt(room -> room.count).sum() % 10;
    }

    static void countRoom(Pos start, int roomNo) {
        visited[start.r][start.c] = true;

        Queue<Pos> q = new ArrayDeque<>();
        q.offer(start);

        List<Pos> result = new ArrayList<>();

        while (!q.isEmpty()) {
            Pos now = q.poll();

            result.add(now);

            for (int[] dir : DIR) {
                int nr = now.r + dir[0];
                int nc = now.c + dir[1];

                if (isOutside(nr, nc) || visited[nr][nc] || map[nr][nc] == WALL)
                    continue;

                visited[nr][nc] = true;
                q.offer(new Pos(nr, nc));
            }
        }

        Room room = new Room(roomNo, result.size());
        result.stream().forEach((pos) -> memo[pos.r][pos.c] = room);
    }

    static boolean isOutside(int nr, int nc) {
        return nr < 0 || nr >= mapRow || nc < 0 || nc >= mapCol;
    }
}