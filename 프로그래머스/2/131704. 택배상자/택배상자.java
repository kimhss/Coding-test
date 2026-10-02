import java.util.*;

class Solution {
    public int solution(int[] order) {
        int answer = 0;
        
        int n = 0;
        for (int i = 0; i < order.length; i++) {
            n = Math.max(n, order[i]);
        }
        
        Stack<Integer> stack = new Stack<>();
        
        int orderIdx = 0;
        for (int i = 1; i <= n; i++) {
            stack.push(i);
            
            while (!stack.isEmpty() && stack.peek() == order[orderIdx]) {
                stack.pop();
                answer++;
                orderIdx++;
            }
        }
        
        return answer;
    }
}