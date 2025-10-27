import java.util.*;
class Solution {
    
    public int solution(int[][] lines) {
        int answer = 0;
        HashMap<Integer, Integer> line = new HashMap<>();

        for (int[] i : lines) {
            for (int j = i[0]; j < i[1]; j++) {
                line.put(j, line.getOrDefault(j, 0) + 1);
            }
        }
        
        for (int val : line.values()) {
            if (val > 1) {
                answer++;
            }
        }
        
        return answer;
    }
}