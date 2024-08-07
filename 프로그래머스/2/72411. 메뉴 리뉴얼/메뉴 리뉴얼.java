import java.util.*;
import java.util.stream.*;

class Solution {
    static Map<String, Integer> map;
    
    public String[] solution(String[] orders, int[] course) {
        List<String> list = new ArrayList<>();
        
        for (int cnt : course) {
            map = new HashMap<>();
            
            for (String order : orders) {
                char[] orderArr = order.toCharArray();
                Arrays.sort(orderArr);
                addCourse(orderArr, cnt);
            }
            
            if (map.isEmpty()) {
                continue;
            }
            
            int maxVal = map.values()
                .stream()
                .max(Integer::compareTo)
                .get();
            
            list.addAll(map.entrySet()
                          .stream()
                          .filter(entry -> entry.getValue() >= 2 && entry.getValue() == maxVal)
                          .map(Map.Entry::getKey)
                          .collect(Collectors.toList()));
        }
        
        String[] answer = list.toArray(String[]::new);
        Arrays.sort(answer);
        
        return answer;
    }
    
    static void addCourse(char[] order, int cnt) {
        if (order.length < cnt) {
            return;
        }
        
        boolean[] visited = new boolean[order.length];
        
        combi(order, visited, 0, cnt);
    }
    
    static void combi(char[] arr, boolean[] visited, int start, int cnt) {
        if (cnt == 0) {
            StringBuilder sb = new StringBuilder();
            
            for (int i = 0; i < visited.length; i++) {
                if (visited[i]) {
                    sb.append(arr[i]);
                }
            }
            
            map.merge(sb.toString(), 1, Integer::sum);
        }
        
        for (int i = start; i < arr.length; i++) {
            visited[i] = true;
            combi(arr, visited, i + 1, cnt - 1);
            visited[i] = false;
        }
    }
}
