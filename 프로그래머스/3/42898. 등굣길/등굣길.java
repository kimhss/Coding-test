class Solution {
    static final int MOD = 1000000007;

    public int solution(int m, int n, int[][] puddles) {

        int[][] map = new int[n][m];
        boolean[][] isPuddle = new boolean[n][m];

        // 웅덩이 표시
        for (int[] puddle : puddles) {
            int x = puddle[0] - 1;
            int y = puddle[1] - 1;

            isPuddle[y][x] = true;
        }

        // 시작점
        map[0][0] = 1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                // 시작점은 이미 1로 설정
                if (i == 0 && j == 0) {
                    continue;
                }

                // 웅덩이면 갈 수 없음
                if (isPuddle[i][j]) {
                    map[i][j] = 0;
                    continue;
                }

                // 위에서 오는 경우
                if (i > 0) {
                    map[i][j] += map[i - 1][j];
                }

                // 왼쪽에서 오는 경우
                if (j > 0) {
                    map[i][j] += map[i][j - 1];
                }

                map[i][j] %= MOD;
            }
        }

        return map[n - 1][m - 1];
    }
}