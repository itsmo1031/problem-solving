import java.util.*;

class Solution {
    public int solution(int n, int[] weak, int[] dist) {
        int[] newWeak = Arrays.copyOf(weak, weak.length * 2);
        for (int i = weak.length; i < newWeak.length; i++) {
            newWeak[i] = weak[i - weak.length] + n;
        }
        
        Arrays.sort(dist);
        
        int answer = 9;
        
        do {
            for (int start = 0; start < weak.length; start++) {
                int worker = 1;
                int current = newWeak[start] + dist[worker - 1];
                
                for (int idx = start + 1; idx < start + weak.length; idx++) {
                    if (current >= newWeak[idx]) {
                        continue;
                    }
                    worker += 1;
                    if (worker > dist.length) {
                        break;
                    }
                    current = newWeak[idx] + dist[worker - 1];
                }
                answer = Math.min(answer, worker);
            }
        } while(nextPermutation(dist));
        
        if (answer > dist.length) {
            answer = -1;
        }
        
        return answer;
    }
    
    // next permutation
    boolean nextPermutation(int[] arr) {
        int top = arr.length - 1;
        
        while (top > 0 && arr[top - 1] >= arr[top]) {
            top--;
        }
        
        if (top == 0) {
            return false;
        }
        
        int target = arr.length - 1;
        
        while (arr[top - 1] >= arr[target]) {
            target--;
        }
        
        // switch
        int temp = arr[top - 1];
        arr[top - 1] = arr[target];
        arr[target] = temp;
        
        Arrays.sort(arr, top, arr.length);
        
        return true;
    }
}