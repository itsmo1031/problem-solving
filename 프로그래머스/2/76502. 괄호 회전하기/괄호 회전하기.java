import java.util.*;

class Solution {
    static char[] arr;
    
    public int solution(String s) {
        int len = s.length();
        s += s;
        arr = s.toCharArray();
        int answer = 0;
        
        for(int i = 0; i < len; i++) {
            if (check(i, len)) {
                i += 1;
                answer += 1;
            }
        }
        
        return answer;
    }
    
    static boolean check(int idx, int length) {
        Deque<Character> q = new ArrayDeque<>();
        
        for (int i = idx; i < idx + length; i++) {
            if (q.isEmpty()) {
                if (isClose(arr[i])) {
                    return false;
                }
                q.offerLast(arr[i]);
                continue;
            }
            if (isSame(q.peekLast(), arr[i])) {
                q.pollLast();
                continue;
            }
            if (isClose(arr[i])) {
                return false;
            }
            q.offerLast(arr[i]);
        }
        
        if (!q.isEmpty()) {
            return false;
        }
        
        return true;
    }
    
    static boolean isClose(char c) {
        return c == '}' || c == ')' || c == ']';
    }
    
    static boolean isSame(char a, char b) {
        if (a == '[' && b == ']') return true;
        if (a == '{' && b == '}') return true;
        if (a == '(' && b == ')') return true;
        return false;
    }
}