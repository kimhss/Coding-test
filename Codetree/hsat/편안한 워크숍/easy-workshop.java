import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int K;
    static int[][] map;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    static int[][] dp;

    public static void main(String[] args) throws Exception {
        // Please write your code here.
        // 길이가 K 이상이면서 높이 차의 최댓값이 최소인 길
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        

        int minHeight = Integer.MAX_VALUE;
        int maxHeight = Integer.MIN_VALUE;


        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                minHeight = Math.min(minHeight, map[i][j]);
                maxHeight = Math.max(maxHeight, map[i][j]);
            }
        }

        // 길이가 1이면 이동할 필요가 없으므로 높이 차는 0
        if (K == 1) {
            System.out.println(0);
            return;
        }

        int left = 0;
        int right = maxHeight - minHeight;

        int answer = -1;

        while(left <= right) {
            int mid = (left + right) / 2;

            if (possible(mid)) {
                answer = mid;
                right = mid - 1;
            }

            else {
                left = mid + 1;
            }
        }

        System.out.println(answer);

    }

    public static boolean possible(int limit) {
        dp = new int[N][N];

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if (dfs(i, j, limit) >= K) {
                    return true;
                }
            }
        }

        return false;
    }
    
    public static int dfs(int r, int c, int limit) {
        
        // 이미 계산한 값이면 그대로 사용
        if (dp[r][c] != 0) {
            return dp[r][c];
        }

        // 자기 자신만 포함해도 길이는 1
        dp[r][c] = 1;

        for(int i = 0; i < 4; i++) {
            int nx = r + dx[i];
            int ny = c + dy[i];

            if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;

            if (map[nx][ny] <= map[r][c]) continue;

            int diff = map[nx][ny] - map[r][c];

            // 현재 허용한 높이 차를 넘어가면 이동 불가능
            if (diff > limit) continue;

            dp[r][c] = Math.max(dp[r][c], dfs(nx, ny, limit) + 1);
        }

        return dp[r][c];
    }
}