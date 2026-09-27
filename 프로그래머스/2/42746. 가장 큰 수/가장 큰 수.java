import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        String str[] = new String[numbers.length];
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < numbers.length; i++) {
            str[i] = String.valueOf(numbers[i]);
        }
        
        Arrays.sort(str, (a, b) -> (b + a).compareTo(a + b));
        
        if (str[0].equals("0")) {
            return "0";
        }
        
        for (int i = 0; i < numbers.length; i++) {
            sb.append(str[i]);
        }
        
        String answer = sb.toString();
        return answer;
    }
}