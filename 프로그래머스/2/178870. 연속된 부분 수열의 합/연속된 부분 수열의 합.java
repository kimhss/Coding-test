class Solution {
    static int[] sequence;
    static int K;
    
    public int[] solution(int[] sequence, int k) {
        int[] answer = {};
        
        this.sequence = sequence;
        this.K = k;
        
        int left = 0;
        int sum = 0;
        
        int bestLeft = 0;
        int bestRight = sequence.length - 1;
        int minLength = Integer.MAX_VALUE;
        
        for (int right = 0; right < sequence.length; right++) {
            // 1. 오른쪽 원소 추가
            sum += sequence[right];
            
            while (sum > K) {
                sum -= sequence[left++];
            } 
            
            if (sum == K) {
                int length = right - left + 1;
                
                if (length < minLength) {
                    minLength = length;
                    bestLeft = left;
                    bestRight = right;
                }
                
            }
        }
        
        return new int[] {bestLeft, bestRight};
    }
}