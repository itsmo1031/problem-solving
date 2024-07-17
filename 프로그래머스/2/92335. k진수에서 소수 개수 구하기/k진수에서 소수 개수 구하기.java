import java.util.*;

class Solution {
    static boolean[] primes;
    
    public int solution(int n, int k) {
        int answer = 0;
        
        StringTokenizer st = new StringTokenizer(Integer.toString(n, k), "0");
        
        while(st.hasMoreTokens()) {
            if (isPrime(Long.parseLong(st.nextToken()))) {
                answer += 1;
            }
         }
        
        return answer;
    }
    
    static boolean isPrime(long num) {
        if (num < 2) {
            return false;
        }
        
        for(long i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        
        return true;
    }
}