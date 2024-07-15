import java.util.*;

class Solution {
    public String solution(int n, int t, int m, int p) {
        int maxLength = t * m;
        StringBuilder sb = new StringBuilder();
        int number = 0;
        
        while (sb.length() < maxLength) {
            sb.append(Integer.toString(number++, n));
        }
        
        StringBuilder answer = new StringBuilder();
        
        for (int i = p - 1; i < t * m; i += m) {
            answer.append(sb.charAt(i));
        }
        
        return answer.toString().toUpperCase();
    }
}