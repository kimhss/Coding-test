class Solution {
    public int solution(int balls, int share) {
        // 머쓱이가 갖고 있는 구슬의 개수 balls
        // 친구들에게 나누어 줄 구슬 개수 share
        // balls 개의 구슬 중 share개의 구슬을 고르는 가능한 모든 경우의 수 
        long answer = 1;

        for(int i = 0; i < share; i++) {
            answer *= balls - i;
            answer /= i + 1;
        }
        
        return (int)answer;
    }
}