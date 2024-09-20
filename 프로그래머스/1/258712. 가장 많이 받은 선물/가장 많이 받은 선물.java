import java.util.*;

class Solution {
    int[][] graph;
    int[] giftIdx;
    
    public int solution(String[] friends, String[] gifts) {
        Map<String, Integer> map = new HashMap<>();
        graph = new int[friends.length][friends.length];
        giftIdx = new int[friends.length];
        
        for (int i = 0 ; i < friends.length; i++) {
            map.put(friends[i], i);
        }
        
        for (String gift : gifts) {
            String[] info = gift.split(" ");
            String from = info[0];
            String to = info[1];
            
            graph[map.get(from)][map.get(to)] += 1;
        }
        
        for (int i = 0; i < friends.length; i++) {
            int sent = 0;
            int received = 0;
            
            for (int j = 0; j < friends.length; j++) {
                sent += graph[i][j];
                received += graph[j][i];
            }
            
            giftIdx[i] = sent - received;
        }
        
        int[] answer = new int[friends.length];
        
        for (int i = 0; i < friends.length; i++) {
            for (int j = i + 1; j < friends.length; j++) {
                if (graph[i][j] != graph[j][i]) { // 주고받은 기록이 있다면
                    if (graph[i][j] > graph[j][i]) {
                        answer[i] += 1;
                    } else {
                        answer[j] += 1;
                    }
                } else { // 주고받은 기록이 없다면
                    if (giftIdx[i] > giftIdx[j]) {
                        answer[i] += 1;
                    } else if (giftIdx[i] < giftIdx[j]) {
                        answer[j] += 1;
                    }
                }
            }
        }
        
        Arrays.sort(answer);
        
        return answer[friends.length - 1];
    }
}