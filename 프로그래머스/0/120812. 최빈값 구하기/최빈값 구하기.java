import java.util.*;

class Solution {
    public int solution(int[] array) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

        int maxcount = 0;
        int answer = 0;
        for(int number : array) {
            int count = map.getOrDefault(number, 0) + 1;
            
            if (count > maxcount) {
                maxcount = count;
                answer = number;
            }
            else if (count == maxcount) {
                answer = -1;
            }
            
            map.put(number, count);
        }
        
        return answer;
    }
}