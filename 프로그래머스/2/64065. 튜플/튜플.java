import java.util.*;

class Solution {
    public int[] solution(String s) {
        String[] arr = s.split("\\},\\{");
        arr[0] = arr[0].replace("{{", "");
        arr[arr.length - 1] = arr[arr.length - 1].replace("}}", "");
        
        Arrays.sort(arr, (s1, s2) -> s1.length() - s2.length());
        
        int[] answer = new int[arr[arr.length - 1].split(",").length];
        Set<Integer> set = new HashSet<>();
        int idx = 0;
        
        for (String str : arr) {
            String[] splitted = str.split(",");
            for (String elem : splitted) {
                int e = Integer.parseInt(elem);
                if (set.add(e)) {
                    answer[idx++] = e;
                }
            }
        }
        
        return answer;
    }
}