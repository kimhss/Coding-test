class Solution {
    public int solution(int n) {
        int answer = 0;
        // answer = n % 7 == 0 ? n / 7 : n / 7 + 1;
        answer = (n + 6) / 7;
        return answer;
    }
}