import java.util.*;
import java.io.*;

class Solution {
    public long solution(int n, int[] times) {
        long answer = Long.MAX_VALUE;
        long max = 0;
        for(int time : times) {
            max = Math.max(time, max);
        }
        
        long left = 1;
        long right = max * n;
        
        while(left <= right) {
            long mid = (left +  right) / 2;
            
            long count = 0;
            for(int time : times) {
                count += mid / time;
            }
            
            if (count >= n) {
                answer = Math.min(answer, mid);
                right = mid - 1;
            }
            
            else left = mid + 1;
        }
        
        
        return answer;
    }
}