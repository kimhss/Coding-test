import java.util.*;

class Solution {
    
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    
    static boolean[][] visited;
    
    static int N, M;
    
    static int[] oil;
    
    static int[][] land;
    
    public int solution(int[][] land) {
        
        N = land.length;
        M = land[0].length;
        
        this.land = land;
        visited = new boolean[N][M];
        oil = new int[M];
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (land[i][j] == 0) continue;
                if (visited[i][j]) continue;
                BFS(i, j);
            }
        }
        
        int max = 0;
        
        for (int i = 0; i < M; i++) {
            max = Math.max(max, oil[i]);
        }
        
        return max;
    }
    
    static void BFS(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {x, y});
        visited[x][y] = true;
        Set<Integer> set = new HashSet<>();
        int count = 0;
        
        while(!q.isEmpty()) {
            int[] now = q.poll();
            count++;
            set.add(now[1]);
            
            for (int i = 0; i < 4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];
                
                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if (visited[nx][ny]) continue;
                if (land[nx][ny] == 0) continue;
                
                q.offer(new int[] {nx, ny});
                visited[nx][ny] = true;
            }
        }
        
        for (int s : set) {
            oil[s] += count;
        }
        
    }
}