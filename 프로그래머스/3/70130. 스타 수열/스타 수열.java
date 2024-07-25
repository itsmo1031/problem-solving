import java.util.*;

class Solution {
    public int solution(int[] a) {
        int answer = -1;
        
        if (a.length < 2) {
            return 0;
        }
        
        Map<Integer, Integer> map = new HashMap<>();
        
        int[] check = new int[a.length];
        Arrays.fill(check, -1);
        
        for (int i = 0; i < a.length; i++) {
            if (i > 0 && a[i - 1] != a[i] && check[i - 1] != a[i]) {
                check[i - 1] = a[i];
                map.put(a[i], map.getOrDefault(a[i], 0) + 1);
            } else if (i < a.length - 1 && a[i] != a[i + 1] && check[i + 1] != a[i]) {
                check[i + 1] = a[i];
                map.put(a[i], map.getOrDefault(a[i], 0) + 1);
            }
            
            answer = Math.max(answer, map.getOrDefault(a[i], 0) * 2);
        }
        
        return answer;
    }
}