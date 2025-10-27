class Solution {
    public String solution(String my_string, int num1, int num2) {
        String answer = "";
        char[] str = my_string.toCharArray();
        
        str[num1] = my_string.charAt(num2);
        str[num2] = my_string.charAt(num1);
        
        answer = String.valueOf(str);
        return answer;
    }
}