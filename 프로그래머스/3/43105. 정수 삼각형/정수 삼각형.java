class Solution {
    
    static int[][] dp;
    
    public int solution(int[][] triangle) {
        int answer = 0;
        
        // 이게 맞는지 모르겠다 !
        dp = new int[triangle.length][];
        for (int i = 0; i < triangle.length; i++) {
            dp[i] = new int[triangle[i].length];
        }
        
        dp[0][0] = triangle[0][0];
        dp[1][0] = dp[0][0] + triangle[1][0];  // 윗 단계 dp + 자기 자신
        dp[1][1] = dp[0][0] + triangle[1][1];
        
        for (int i = 2; i < triangle.length; i++) {
            for (int j = 0; j < triangle[i].length; j++) {
                if (j == 0) {
                    dp[i][j] = dp[i - 1][j] + triangle[i][j];
                    continue;
                }
                
                if (j == triangle[i].length - 1) {
                    dp[i][j] = dp[i - 1][j - 1] + triangle[i][j];
                    continue;
                }
                
                dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]) + triangle[i][j];
            }
        }
        
        int size = triangle.length - 1;
        for (int i = 0; i < triangle[size].length; i++) {
            answer = Math.max(dp[size][i], answer);
        }
        
        return answer;
    }
}