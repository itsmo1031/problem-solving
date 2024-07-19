import java.util.*;

class Solution {
    static Map<String, Integer> dict;
    static int dictIdx;
    
    public int[] solution(String msg) {
        return compress(msg);
    }
    
    static void init() {
        dictIdx = 1;
        dict = new HashMap<>();
        
        for (char c = 'A'; c <= 'Z'; c++) {
            dict.put(Character.toString(c), dictIdx++);
        }
    }
    
    static int[] compress(String msg) {
        init();
        
        List<Integer> compressed = new ArrayList<>();
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < msg.length(); i++) {
            sb.append(msg.charAt(i));
            if (!dict.containsKey(sb.toString())) {
                dict.put(sb.toString(), dictIdx++);
                compressed.add(dict.get(sb.substring(0, sb.length() - 1)));
                sb.delete(0, sb.length() - 1);
            }
        }
        
        compressed.add(dict.get(sb.toString()));
        
        return compressed.stream()
            .mapToInt(Integer::intValue)
            .toArray();
    }
}