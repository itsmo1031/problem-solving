import java.util.*;

class Solution {
    int[][] graph;
    int[] answer;
    
    public int[] solution(int[][] edges) {
        answer = new int[4];
        
        // 생성한 정점 번호 찾기
        Map<Integer, Integer> starts = new HashMap<>();
        Map<Integer, Integer> ends = new HashMap<>();

        for (int[] edge : edges) {
            starts.put(edge[0], starts.getOrDefault(edge[0], 0) + 1);
            ends.put(edge[1], ends.getOrDefault(edge[1], 0) + 1);
        }
        
        // 출발 정점에만 쓰이면서 2개 이상의 간선을 보유한 번호가 생성한 정점 번호
        Set<Integer> startsKey = starts.keySet();
        startsKey.removeAll(ends.keySet());
        
        for (int key : startsKey) {
            if (starts.get(key) >= 2) {
                answer[0] = key;
            }
        }
        
        
        // 각 그래프의 시작 정점을 담을 리스트 선언
        List<Integer> graphEntries = new ArrayList<>();
        
        graph = new int[1_000_001][2];
        
        for (int[] edge : edges) {
            // 만약 시작 정점이 생성 정점일 경우 도착점이 하나의 그래프의 정점
            if (edge[0] == answer[0]) {
                graphEntries.add(edge[1]);
            } else {
                // 생성 정점과 관계 없는 간선 정보만 담기
                if (graph[edge[0]][0] == 0) {
                    graph[edge[0]][0] = edge[1];
                } else {
                    graph[edge[0]][1] = edge[1];
                }
                
            }
        }
        
        for (int start : graphEntries) {
            checkGraphType(start);
        }
        
        return answer;
    }
    
    // 그래프 타입 확인
    public void checkGraphType(int start) {
        int current = start;
        int count = 0;
        while(true) {
            if (graph[current][0] == 0) { // 다음 간선 정보가 없으면 막대
                answer[2] += 1;
                return;
            }
            if (graph[current][1] != 0) { // 갈 수 있는 길이 2개면 8자
                answer[3] += 1;
                return;
            }
            if (graph[current][0] == start) {
                if (++count == 2) { // 현 위치로 돌아왔으면 도넛
                    answer[1] += 1;
                    return;
                }
            }
            current = graph[current][0];
        }
    }
}