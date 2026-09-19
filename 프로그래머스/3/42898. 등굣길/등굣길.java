class Solution {
    static final int MOD = 1000000007;
    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        
        int[][] dp = new int[n][m];
        boolean[][] puddle = new boolean[n][m];
        
        for (int i = 0; i < puddles.length; i++) {
            int x = puddles[i][1] - 1;
            int y = puddles[i][0] - 1;
            
            puddle[x][y] = true;
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 && j == 0) {
                    dp[0][0] = 1;
                    continue;
                }
                
                if (puddle[i][j]) {
                    dp[i][j] = 0;
                    continue;
                }
                
                
                if (j > 0) {
                    dp[i][j] += dp[i][j - 1];
                }
                
                if (i > 0) {
                    dp[i][j] += dp[i - 1][j];
                }
                
                dp[i][j] %= MOD;
            }
        }
        
        answer = dp[n - 1][m - 1];
        
        return answer;
    }
}