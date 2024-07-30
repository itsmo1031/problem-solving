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
        
        int[] memo = new int[n + 1];
        Arrays.fill(memo, INF);
        
        for (int gate : gates) {
            int[] result = dijkstra(gate);
            
            for (int i = 0; i < summits.length; i++) {
                memo[i] = Math.min(memo[i], result[summits[i]]);
            }
        }
        
        for (int i = 0; i < summits.length; i++) {
            if (memo[i] < answer[1]) {
                answer[0] = summits[i];
                answer[1] = memo[i];
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
    
    static int[] dijkstra(int start) {
        Queue<Edge> q = new PriorityQueue<>();
        
        int[] dist = new int[edgeCnt + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;
        
        int[] intensity = new int[edgeCnt + 1];
        Arrays.fill(intensity, INF);
        intensity[start] = 0;
        
        q.offer(new Edge(start, 0));
        
        while(!q.isEmpty()) {
            Edge now = q.poll();
            
            if (summitSet.contains(now.num)) {
                break;
            }
            
            for (Edge next : graph[now.num]) {
                if (gateSet.contains(next.num)) {
                    continue;
                }
                
                if (next.weight < dist[next.num]) {
                    dist[next.num] = next.weight;
                    q.offer(new Edge(next.num, next.weight));
                    intensity[next.num] = Math.max(intensity[now.num], next.weight);
                }
            }
        }
        
        return intensity;
    }
}