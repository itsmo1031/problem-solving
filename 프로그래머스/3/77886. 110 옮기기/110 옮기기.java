import java.util.*;

class Solution {
    public String[] solution(String[] s) {
        String[] answer = new String[s.length];
        
        for (int i = 0; i < s.length; i++) {
            int count = 0;
            
            StringBuilder target = new StringBuilder();
            
            int index = 0;
            while(index <= s[i].length()) {
                if (target.length() >= 3 && (target.charAt(target.length() - 3) == '1' &&
                       target.charAt(target.length() - 2) == '1' && target.charAt(target.length() - 1) == '0')) {
                    target.setLength(target.length() - 3);
                    count += 1;
                } else if (index == s[i].length()) {
                    break;
                } else {
                    target.append(s[i].charAt(index++));
                }
            }
            
            // "110"을 삽입할 위치 찾기
            int insertPos = target.lastIndexOf("0") + 1;
            if (insertPos == 0) {
                insertPos = target.indexOf("1");
                if (insertPos == -1) {
                    insertPos = target.length();
                }
            }
            
            // "110"을 적절한 위치에 삽입
            target.insert(insertPos, "110".repeat(count));
            
            answer[i] = target.toString();
        }
        
        return answer;
    }
}