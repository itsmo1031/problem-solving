class Solution {
    public int solution(int n, int[] cores) {
        int answer = 0;
        
        int time = binarySearch(n, cores);
        
        if (time == 0) {
            return n;
        }
        
        // time - 1시간까지의 작업량 구하기
        int count = cores.length;
        for (int core : cores) {
            count += (time - 1) / core;
        }
        
        // 각 코어를 돌며 시각 t에 n번째 작업이 추가되는 번호 구하기
        for (int i = 0; i < cores.length; i++) {
            if (time % cores[i] == 0) {
                if (++count == n) {
                    answer = i + 1;
                    break;
                }
            }
        }
        
        return answer;
    }
    
    static int binarySearch(int n, int[] cores) {
        int min = 0;
        int max = cores[0] * n;
        
        int time = 0;
        
        while (min <= max) {
            int mid = (min + max) / 2;
            
            if (check(n, mid, cores)) {
                max = mid - 1;
                time = mid;
            } else {
                min = mid + 1;
            }
        }
        
        return time;
    }
    
    static boolean check(int n, int time, int[] cores) {
        int count = cores.length; // 처음 n개의 작업만큼 들어감
        
        for (int core : cores) {
            count += time / core;
        }
        
        return count >= n;
    }
}