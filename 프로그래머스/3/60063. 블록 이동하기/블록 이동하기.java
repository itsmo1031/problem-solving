import java.util.*;

class Solution {
    static final int[][] DIR = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static final int[] ROT = {1, -1};
    static final int WALL = 1;
    static int[][] map;
    static int mapSize;
    
    static class Drone {
        final Pos left;
        final Pos right;
        final int move;
        
        public Drone(Pos left, Pos right, int move) {
            this.left = left;
            this.right = right;
            this.move = move;
        }
        
        public boolean isArrived() {
            return (left.r == mapSize && left.c == mapSize) || (right.r == mapSize && right.c == mapSize);
        }
        
        public List<int[][]> getPossibleMoves() {
            List<int[][]> list = new ArrayList<>();
            
            // 상, 하, 좌, 우 이동
            for (int[] dir : DIR) {
                int nlr = left.r + dir[0];
                int nlc = left.c + dir[1];
                int nrr = right.r + dir[0];
                int nrc = right.c + dir[1];
                
                if (map[nlr][nlc] == WALL || map[nrr][nrc] == WALL) {
                    continue;
                }
                
                list.add(new int[][]{{nlr, nlc}, {nrr, nrc}});
            }
            
            // 가로일 때 90도 회전
            if (left.r == right.r) {
                for (int rot : ROT) {
                    if (map[left.r + rot][left.c] == WALL || map[right.r + rot][right.c] == WALL) {
                        continue;
                    }
                    
                    list.add(new int[][]{{left.r, left.c}, {left.r + rot, left.c}});
                    list.add(new int[][]{{right.r, right.c}, {right.r + rot, right.c}});
                }
            }
            
            // 세로일 때 90도 회전
            if (left.c == right.c) {
                for (int rot : ROT) {
                    if (map[left.r][left.c + rot] == WALL || map[right.r][right.c + rot] == WALL) {
                        continue;
                    }
                    
                    list.add(new int[][]{{left.r, left.c}, {left.r, left.c + rot}});
                    list.add(new int[][]{{right.r, right.c}, {right.r, right.c + rot}});
                }
            }
            
            return list;
        }
        
        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Drone)) {
                return false;
            }
            Drone d = (Drone) o;
            return (this.left.equals(d.left) && this.right.equals(d.right)) || (this.left.equals(d.right) && this.right.equals(d.left));
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(left, right);
        }
    }
    
    static class Pos {
        final int r, c;
        
        public Pos (int r, int c) {
            this.r = r;
            this.c = c;
        }
        
        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Pos)) {
                return false;
            }
            Pos p = (Pos) o;
            
            return this.r == p.r && this.c == p.c;
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(this.r, this.c);
        }
    }
    
    public int solution(int[][] board) {
        mapSize = board.length;
        map = new int[mapSize + 2][mapSize + 2];
        
        Arrays.fill(map[0], WALL);
        Arrays.fill(map[mapSize + 1], WALL);
        
        for (int r = 1; r <= board.length; r++) {
            map[r][0] = WALL;
            map[r][mapSize + 1] = WALL;
            for (int c = 1; c <= board.length; c++) {
                map[r][c] = board[r - 1][c - 1];
            }
        }
        
        return bfs();
    }
    
    static int bfs() {
        Queue<Drone> q = new ArrayDeque<>();
        Set<Drone> visited = new HashSet<>();
        
        Drone drone = new Drone(new Pos(1, 1), new Pos(1, 2), 0);
        q.offer(drone);
        visited.add(drone);
        
        while(!q.isEmpty()) {
            Drone now = q.poll();
            
            if (now.isArrived()) {
                return now.move;
            }
            
            for (int[][] np : now.getPossibleMoves()) {
                Drone next = new Drone(new Pos(np[0][0], np[0][1]), new Pos(np[1][0], np[1][1]), now.move + 1);
                
                if (visited.contains(next)) {
                    continue;
                }
                
                visited.add(next);
                q.offer(next);
            }
        }
        
        return -1;
    }
}