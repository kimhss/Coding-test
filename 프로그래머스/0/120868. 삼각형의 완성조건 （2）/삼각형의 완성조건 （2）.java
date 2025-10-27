import java.util.*;

class Solution {
    public int solution(int[] sides) {
        int answer = 0;
        // sides 중 한 변이 제일 긴 경우
        int other = 1;
        Arrays.sort(sides);
        while (other < sides[1]) {
            if (other + sides[0] > sides[1]) {
                answer++;
                other++;
            }
            else other++;
        }
        
        // sides 제외 나머지 한 변이 가장 긴 경우
        other = sides[1];
        while (sides[0] + sides[1] > other) {
            answer++;
            other++;
        }
        
        
        return answer;
    }
}