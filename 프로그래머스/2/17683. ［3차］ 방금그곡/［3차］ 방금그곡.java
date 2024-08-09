class Solution {
    String[][] replaced = {{"C#", "c"}, {"D#", "d"}, {"F#", "f"}, {"G#", "g"}, {"A#", "a"}, {"B#", "b"}};

    public String solution(String m, String[] musicinfos) {
        String answer = "(None)";
        int maxTime = 0;
        
        for (String info : musicinfos) {
            String[] infos = info.split(",");
            int time = calcTime(infos[0], infos[1]);
            String name = infos[2];
            String score = replace(infos[3]);
            
            if (time < score.length()) {
                score = score.substring(0, time);
            } else {
                int val = time / score.length();
                int mod = time % score.length();
                
                score = score.repeat(val).concat(score.substring(0, mod));
            }
            
            if (score.contains(replace(m)) && maxTime < time) {
                maxTime = time;
                answer = name;
            }
        }
        
        return answer;
    }
    
    int calcTime(String startTime, String endTime) {
        String[] start = startTime.split(":");
        String[] end = endTime.split(":");
        
        int hour = Integer.parseInt(end[0]) - Integer.parseInt(start[0]);
        int min = Integer.parseInt(end[1]) - Integer.parseInt(start[1]);
        
        return hour * 60 + min;
    }
    
    String replace(String target) {
        String result = target;
        
        for (String[] rep : replaced) {
            result = result.replaceAll(rep[0], rep[1]);
        }
        
        return result;
    }
}