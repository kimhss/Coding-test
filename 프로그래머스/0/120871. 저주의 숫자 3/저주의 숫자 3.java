import java.util.*;

class Solution {
    public int solution(int n) {
        int answer = 0;

        // 3의 배수이거나 3을 포함하는 숫자
        for(int i = 1; i <= n; i++) {
            answer++;
            
            while(answer % 3 == 0 || Integer.toString(answer).contains("3")) {
                answer++;
            }
            
        }
        return answer;
    }
}