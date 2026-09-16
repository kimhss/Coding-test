import java.util.*;

class Solution {
    
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, 1, -1};
    
    static boolean[][] visited;
    static int[][] distances;
    
    public int solution(int[][] maps) {
        int answer = -1;
        
        int n = maps.length;
        int m = maps[0].length;
        
        visited = new boolean[n][m];
        distances = new int[n][m];
        
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {0, 0});
        visited[0][0] = true;
        distances[0][0] = 1;
        
        while(!q.isEmpty()) {
            int[] now = q.poll();
            
            for (int i = 0; i < 4; i++) {
                int nr = now[0] + dr[i];
                int nc = now[1] + dc[i];
                
                // 범위 벗어나는 경우
                if (nr < 0 || nc < 0 || nr >= n || nc >= m) continue;
                
                // 이미 방문한 경우
                if (visited[nr][nc]) continue;
                
                // 벽인 경우
                if (maps[nr][nc] == 0) continue;
                
                if (nr == n - 1 && nc == m - 1) return distances[now[0]][now[1]] + 1;
                
                q.offer(new int[] {nr, nc});
                visited[nr][nc] = true;
                distances[nr][nc] = distances[now[0]][now[1]] + 1;
            }
        }
                
        return answer;
    }
}