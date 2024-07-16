import java.util.*;

class Solution {
    public int solution(int n, int[] weak, int[] dist) {
        int[] weakPoint = Arrays.copyOf(weak, weak.length * 2);
        for (int i = weak.length; i < weakPoint.length; i++) {
            weakPoint[i] = weak[i - weak.length] + n;
        }
        
        
        int answer = 9;
        
        do {
            for (int start = 0; start < weak.length; start++) {
                int worker = 1;
                int current = weakPoint[start] + dist[worker - 1];

                for (int idx = start; idx < start + weak.length; idx++) {
                    if (current >= weakPoint[idx]) {
                        continue;
                    }
                    if (++worker > dist.length) {
                        break;
                    }
                    current = weakPoint[idx] + dist[worker - 1];
                }
                answer = Math.min(answer, worker);
                if (answer == 1) {
                    return answer;
                }
            }
        } while (nextPermutation(dist));
        
        if (answer > dist.length) {
            answer = -1;
        }
        
        return answer;
    }
        
    static boolean nextPermutation(int[] arr) {
        int top = arr.length - 1;
        
        while (top > 0 && arr[top - 1] >= arr[top]) {
            top--;
        }
        
        if (top == 0) {
            return false;
        }
        
        int target = arr.length - 1;
        while (arr[target] <= arr[top - 1]) {
            target--;
        }
        
        int temp = arr[top - 1];
        arr[top - 1] = arr[target];
        arr[target] = temp;
        
        Arrays.sort(arr, top, arr.length);
        
        return true;
    }
}