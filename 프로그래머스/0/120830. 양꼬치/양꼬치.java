class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        int 양꼬치 = 12000 * n;
        int 음료수 = (k - (n / 10)) * 2000;
        answer = 양꼬치 + 음료수;
        return answer;
    }
}