import java.util.*;

class Solution {
    public int solution(int a, int b) {
        int answer = 0;
        int n1 = Integer.parseInt(String.valueOf(a) + String.valueOf(b));
        int n2 = Integer.parseInt(String.valueOf(b) + String.valueOf(a));
        answer = Math.max(n1, n2);
        return answer;
    }
}