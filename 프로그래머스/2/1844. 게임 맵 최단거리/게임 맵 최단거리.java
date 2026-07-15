import java.util.*;

class Solution {
    static boolean[][] visited;
    static int[][] distances;
    static int N, M;
    
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    
    public int solution(int[][] maps) {
        int answer = 0;
        
        N = maps.length;
        M = maps[0].length;
        
        visited = new boolean[N][M];
        distances = new int[N][M];
    
        BFS(0, 0, maps);
        
        if (distances[N - 1][M - 1] == 0)
            answer = -1;
        else answer = distances[N - 1][M - 1];
        
        return answer;
    }
    
    public static void BFS(int r, int c, int[][] maps) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {r, c});
        visited[r][c] = true;
        distances[r][c] = 1;
        
        while(!q.isEmpty()) {
            int[] now = q.poll();
            
            for(int i = 0; i < 4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];
                
                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;

                if (!visited[nx][ny] && maps[nx][ny] != 0) {
                    q.offer(new int[] {nx, ny});
                    visited[nx][ny] = true;
                    distances[nx][ny] = distances[now[0]][now[1]] + 1;
                }
            }
        }
        
    }
}