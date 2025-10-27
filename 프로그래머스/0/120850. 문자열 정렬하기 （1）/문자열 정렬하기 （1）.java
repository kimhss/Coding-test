import java.util.*;

class Solution {
    public int[] solution(String my_string) {
        int[] answer = {};
        String[] arr = my_string.replaceAll("[a-z]", "").split("");
        answer = new int[arr.length];
        
        for(int i = 0; i < arr.length; i++) {
            answer[i] = Integer.parseInt(arr[i]);
        }
        Arrays.sort(answer);
        
        return answer;
    }
}