import java.util.*;

class Solution {
    static final int INF = (int) 1e9;
    static Set<Integer> gateSet;
    static Set<Integer> summitSet;
    static List<Edge>[] graph;
    static int edgeCnt;
    
    static class Edge implements Comparable<Edge> {
        int num, weight;
        
        public Edge(int num, int weight) {
            this.num = num;
            this.weight = weight;
        }
        
        @Override
        public int compareTo(Edge e) {
            return Integer.compare(this.weight, e.weight);
        }
    }
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        int[] answer = {INF, INF};
        
        init(n, paths, gates, summits);
        Arrays.sort(summits);
        
        int[] result = dijkstra(gates);

        for (int i = 0; i < summits.length; i++) {
            if (result[summits[i]] < answer[1]) {
                answer[0] = summits[i];
                answer[1] = result[summits[i]];
            }
        }
        
        return answer;
    }
    
    static void init(int n, int[][] paths, int[] gates, int[] summits) {
        edgeCnt = n;
        gateSet = new HashSet<>();
        summitSet = new HashSet<>();
        graph = new ArrayList[n + 1];
        
        for (int gate : gates) {
            gateSet.add(gate);
        }
        
        for (int summit : summits) {
            summitSet.add(summit);
        }
        
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int[] path : paths) {
            int from = path[0];
            int to = path[1];
            int weight = path[2];
            
            graph[from].add(new Edge(to, weight));
            graph[to].add(new Edge(from, weight));
        }
    }
    
    static int[] dijkstra(int[] starts) {
        Queue<Edge> q = new PriorityQueue<>();
        
        int[] intensity = new int[edgeCnt + 1];
        Arrays.fill(intensity, INF);
        
        for(int start: starts) {
            intensity[start] = 0;
            q.offer(new Edge(start, 0));
        }
        
        while(!q.isEmpty()) {
            Edge now = q.poll();
            
            if (summitSet.contains(now.num)) {
                continue;
            }
            
            if (now.weight > intensity[now.num]) {
                continue;
            }
            
            for (Edge next : graph[now.num]) {
                if (gateSet.contains(next.num)) {
                    continue;
                }
                
                int nw = Math.max(intensity[now.num], next.weight);
                if (nw < intensity[next.num]) {
                    q.offer(new Edge(next.num, nw));
                    intensity[next.num] = nw;
                }
            }
        }
        
        return intensity;
    }
}