import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        int answer = 0;
        
        Map<String, Integer> map = new HashMap<>();
        
        for (String[] cloth : clothes) {
            map.put(cloth[1], map.getOrDefault(cloth[1], 0) + 1);
        }
        
        answer = map.values().stream()
                    .reduce(1, (acc, val) -> (acc * (val + 1))) - 1;
        
        return answer;
    }
}