import java.util.*;

class Solution {
    public String solution(String my_string) {
        String answer = "";
        StringBuilder s = new StringBuilder();
        
        for(char c : my_string.toCharArray()) {
            if(Character.isUpperCase(c)) 
                s.append(Character.toLowerCase(c));
            else
                s.append(Character.toUpperCase(c));
        }
        
        
        return s.toString();
    }
}