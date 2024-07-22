import java.util.*;

class Solution {
    static class File implements Comparable<File> {
        String name;
        private String head;
        private Integer number;
        
        public File(String name) {
            this.name = name;
            
            classify();
        }
        
        private void classify() {
            String target = this.name.toLowerCase();
            StringBuilder sb = new StringBuilder();
            int i;
            for(i = 0; i < target.length(); i++) {
                char ch = target.charAt(i);
                if (Character.isDigit(ch)) {
                    break;
                }
                sb.append(ch);
            }
            
            this.head = sb.toString();
            
            sb.setLength(0);
            
            for(int j = i; j < target.length(); j++) {
                char ch = target.charAt(j);
                if (!Character.isDigit(ch)) {
                    break;
                }
                sb.append(ch);
            }
            
            this.number = Integer.parseInt(sb.toString());
        }
        
        @Override
        public int compareTo(File f) {
            if (!this.head.equals(f.head)) {
                return this.head.compareTo(f.head);
            } 
            
            if (this.number != f.number) {
                return Integer.compare(this.number, f.number);
            }
            
            return 0;
        }
    }
    
    public String[] solution(String[] files) {
        String[] answer = new String[files.length];
        File[] fileArr = new File[files.length];
        
        for (int i = 0; i < files.length; i++) {
            fileArr[i] = new File(files[i]);
        }
        
        Arrays.sort(fileArr);
    
        for (int i = 0; i < files.length; i++) {
            answer[i] = fileArr[i].name;
        }
        
        return answer;
    }
}