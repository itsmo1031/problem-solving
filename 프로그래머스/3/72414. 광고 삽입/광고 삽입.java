class Solution {
    static long[] timeline;
    
    public String solution(String play_time, String adv_time, String[] logs) {
        // 최대 99시간 59분 59초
        timeline = new long[100 * 3600];
        
        int playTime = toSec(play_time);
        int advTime = toSec(adv_time);
        
        for (String log : logs) {
            String[] info = log.split("-");
            
            int start = toSec(info[0]);
            int end = toSec(info[1]);
            
            for (int i = start; i < end; i++) {
                timeline[i] += 1;
            }
        }
        
        long acc = 0;
        for (int i = 0; i < advTime; i++) {
            acc += timeline[i];
        }
        long max = acc;
        int res = 0;
        
        for(int i = advTime; i < playTime; i++) {
            acc = acc - timeline[i - advTime] + timeline[i];
            if (acc > max) {
                max = acc;
                res = i - advTime + 1;
            }
        }
        
        return formatTime(res);
    }
    
    static int toSec(String time) {
        String[] times = time.split(":");
        return Integer.parseInt(times[0]) * 3600 +
            Integer.parseInt(times[1]) * 60 +
            Integer.parseInt(times[2]);
    }
    
    static String formatTime(int sec) {
        int hour = sec / 3600;
        sec %= 3600;
        int min = sec / 60;
        sec %= 60;
        
        return String.format("%02d:%02d:%02d", hour, min, sec);
    }
}