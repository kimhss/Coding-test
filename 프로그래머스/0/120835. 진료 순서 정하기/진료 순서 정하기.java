import java.util.*;

class Solution {
    public int[] solution(int[] emergency) {
        int[] answer = new int[emergency.length];

        // 숫자 높은게 응급도 높음
        for(int i = 0; i < emergency.length; i++) {
            answer[i] = 1;
            for(int j = 0; j < emergency.length; j++) {
                if(emergency[i] < emergency[j]) {
                    answer[i]++;
                }
            }
        }
        
        return answer;
    }
}