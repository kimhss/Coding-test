class Solution {
    public int solution(int[] numbers, int k) {
        int answer = 0;
        
        int i = (k - 1) * 2 % numbers.length;
        answer = numbers[i];
        
        return answer;
    }
}