import java.util.*;

class Solution {
    public int[] solution(int[] numbers, int num1, int num2) {
        List<Integer> list = new ArrayList<>();
        int[] answer = {};
        for(int i = num1; i <= num2; i++) {
            list.add(numbers[i]);
        }
        answer = list.stream().mapToInt(i -> i).toArray();
        return answer;
    }
}