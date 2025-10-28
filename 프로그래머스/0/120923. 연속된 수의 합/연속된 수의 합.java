class Solution {
    public int[] solution(int num, int total) {
        int[] answer = new int[num];
        
        // 연속된 num개의 합이 total이 되려면,
        // 첫 번째 수는 (total - (num * (num - 1) / 2)) / num
        // int start = (total - (num * (num - 1) / 2)) / num;
        int start = ((2 * total / num) - num + 1) / 2;
        
        for (int i = 0; i < num; i++) {
            answer[i] = start + i;
        }
        return answer;
    }
}