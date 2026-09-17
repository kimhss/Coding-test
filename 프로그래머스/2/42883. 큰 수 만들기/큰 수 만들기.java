import java.util.*;

class Solution {
    public String solution(String number, int k) {
        String answer = "";
        StringBuilder sb = new StringBuilder();
        
        Stack<Character> stack = new Stack<>();
        
        // N자리 선택
        int N = number.length() - k;
        
        char[] numberArr = number.toCharArray();
        
        for (char num : numberArr) {
            while (!stack.isEmpty() 
                  && k > 0
                  && stack.peek() < num) {
                stack.pop();
                k--;
            }
            
            stack.push(num);
        }
        
        while (k > 0) {
            stack.pop();
            k--;
        }
        
        for(char num : stack) {
            sb.append(num);
        }
        
        answer = sb.toString();
        
        return answer;
    }
    
    
}