import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        
        Stack<Integer> stack = new Stack<>();
        
        for (int i = 0; i < numbers.length; i++) {
            while(!stack.isEmpty() && numbers[i] > numbers[stack.peek()]) {
                answer[stack.peek()] = numbers[i];
                stack.pop();
            }
            
            stack.push(i);
        }
        
        while(!stack.isEmpty()) {
            answer[stack.peek()] = -1;
            stack.pop();
        }
        
        return answer;
    }
}