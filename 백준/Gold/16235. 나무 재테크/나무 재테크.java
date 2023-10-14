import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main {
    static final int[][] DIR = { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 }, { 1, -1 }, { 1, 0 }, { 1, 1 } };
    static int mapSize, treeCnt;
    static int[][] fertilizer;
    static Soil[][] map;

    static class Tree implements Comparable<Tree> {
        static final int ALIVE = 1, DEAD = 0;
        int age;
        int status;

        public Tree(int age) {
            this.age = age;
            this.status = ALIVE;
        }

        void grow() {
            age += 1;
        }

        @Override
        public int compareTo(Tree o) {
            return Integer.compare(this.age, o.age);
        }
    }

    static class Soil {
        List<Tree> trees;
        int food;

        public Soil(int food) {
            this.trees = new ArrayList<>();
            this.food = food;
        }

        void addTree(int age) {
            trees.add(new Tree(age));
        }

        void sort() {
            Collections.sort(trees);
        }

        void feed() {
            sort();
            for (Tree tree : trees) {
                if (tree.age <= food) {
                    food -= tree.age;
                    tree.grow();
                } else {
                    tree.status = Tree.DEAD;
                }
            }
        }

        void clean() {
            for (Tree tree : trees) {
                if (tree.status == Tree.DEAD) {
                    food += tree.age / 2;
                }
            }

            trees = trees.stream().filter(t -> t.status != Tree.DEAD).collect(Collectors.toList());
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        mapSize = Integer.parseInt(st.nextToken());
        treeCnt = Integer.parseInt(st.nextToken());
        int totalYear = Integer.parseInt(st.nextToken());

        fertilizer = new int[mapSize + 1][mapSize + 1];
        map = new Soil[mapSize + 1][mapSize + 1];

        for (int row = 1; row <= mapSize; row++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int col = 1; col <= mapSize; col++) {
                map[row][col] = new Soil(5);
                fertilizer[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        for (int idx = 0; idx < treeCnt; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            int treeRow = Integer.parseInt(st.nextToken());
            int treeCol = Integer.parseInt(st.nextToken());
            int age = Integer.parseInt(st.nextToken());

            map[treeRow][treeCol].addTree(age);
        }

        for (int year = 0; year < totalYear; year++) {
            spring();
            summer();
            autumn();
            winter();
        }

        int answer = 0;

        for (int row = 1; row <= mapSize; row++) {
            for (int col = 1; col <= mapSize; col++) {
                answer += map[row][col].trees.size();
            }
        }

        System.out.println(answer);
    }

    static void spring() {
        for (int row = 1; row <= mapSize; row++) {
            for (int col = 1; col <= mapSize; col++) {
                map[row][col].feed();
            }
        }
    }

    static void summer() {
        for (int row = 1; row <= mapSize; row++) {
            for (int col = 1; col <= mapSize; col++) {
                map[row][col].clean();
            }
        }
    }

    static void autumn() {
        for (int row = 1; row <= mapSize; row++) {
            for (int col = 1; col <= mapSize; col++) {
                for (Tree tree : map[row][col].trees) {
                    if (tree.age % 5 == 0) {
                        propagation(row, col);
                    }
                }
            }
        }
    }

    static void propagation(int row, int col) {
        for (int[] dir : DIR) {
            int nr = row + dir[0];
            int nc = col + dir[1];

            if (isPossible(nr, nc)) {
                map[nr][nc].addTree(1);
            }
        }
    }

    static boolean isPossible(int row, int col) {
        return 0 < row && row <= mapSize && 0 < col && col <= mapSize;
    }

    static void winter() {
        for (int row = 1; row <= mapSize; row++) {
            for (int col = 1; col <= mapSize; col++) {
                map[row][col].food += fertilizer[row][col];
            }
        }
    }
}