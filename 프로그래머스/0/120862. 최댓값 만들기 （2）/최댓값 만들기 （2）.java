import java.util.*;

class Solution {
    public int solution(int[] numbers) {
        int len = numbers.length - 1;
        int answer = 0;
        Arrays.sort(numbers);
        answer = Math.max(numbers[0] * numbers[1], numbers[len - 1] * numbers[len]);
        return answer;
    }
}