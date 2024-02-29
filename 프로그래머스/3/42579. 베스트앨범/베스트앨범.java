import java.util.*;

class Solution {
    class Song implements Comparable<Song> {
        int index;
        int play;
        
        public Song(int index, int play) {
            this.index = index;
            this.play = play;
        }
        
        @Override
        public int compareTo(Song o) {
            if (this.play == o.play) {
                return this.index - o.index;
            }
            return o.play - this.play;
        }
    }
    
    public int[] solution(String[] genres, int[] plays) {
        Map<String, Queue<Song>> map = new HashMap<>();
        Map<String, Integer> playCount = new HashMap<>();
        
        for (int i = 0; i < genres.length; i++) {
            if (!map.containsKey(genres[i])) {
                map.put(genres[i], new PriorityQueue<>());
            }
            
            Queue<Song> current = map.get(genres[i]);
            current.offer(new Song(i, plays[i]));
            
            playCount.put(genres[i], playCount.getOrDefault(genres[i], 0) + plays[i]);
        }

        List<String> keySet = new ArrayList<>(playCount.keySet());
        
        keySet.sort((o1, o2) -> playCount.get(o2).compareTo(playCount.get(o1)));
        
        List<Integer> answer = new ArrayList<>();
        
        for (int i = 0; i < keySet.size(); i++) {
            String key = keySet.get(i);
            Queue<Song> q = map.get(key);
            
            for (int j = 0; j < 2; j++) {
                if (!q.isEmpty()) {
                    answer.add(q.poll().index);
                }
            }
        }
        
        int[] result = new int[answer.size()];
        
        for (int i = 0; i < result.length; i++) {
            result[i] = answer.get(i);
        }
        
        return result;
    }
}