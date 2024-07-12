import java.util.*;

class Solution {
    public int solution(String str1, String str2) {
        int answer = 0;
        
        double jac = jaccard(str1, str2);
        
        System.out.println(jac);
        
        return (int) (jac * 65536);
    }
    
    static List<String> split(String str) {
        str = str.toLowerCase();
        List<String> list = new ArrayList<>();
        
        for (int i = 0; i < str.length() - 1; i++) {
            char[] ch = {str.charAt(i), str.charAt(i + 1)};
            if (ch[0] < 'a' || ch[0] > 'z' || ch[1] < 'a' || ch[1] > 'z') {
                continue;
            }
            list.add(new String(ch));
        }
        
        return list;
    }
    
    static List<String> union(List<String> str1, List<String> str2) {
        List<String> copied = deepCopy(str1);
        copied.addAll(str2);
        
        List<String> inter = intersection(str1, str2);
        
        for (String s : inter) {
            copied.remove(s);
        }
        
        System.out.println(copied);
        
        return copied;
    }
    
    static List<String> intersection(List<String> str1, List<String> str2) {
        List<String> result = new ArrayList<>();
        List<String> copied = deepCopy(str2);
        
        for (String s : str1) {
            if (copied.contains(s)) {
                result.add(s);
                copied.remove(s);
            }
        }
        
        return result;
    }
    
    static List<String> deepCopy(List<String> str) {
        List<String> res = new ArrayList<>();
        
        for (String s : str) {
            res.add(s);
        }
        
        return res;
    }
    
    static double jaccard(String str1, String str2) {
        List<String> s1 = split(str1);
        List<String> s2 = split(str2);
        
        if (s1.size() == 0 && s2.size() == 0) {
            return 1;
        }
        
        return (double) intersection(s1, s2).size() / union(s1, s2).size();
    }
}