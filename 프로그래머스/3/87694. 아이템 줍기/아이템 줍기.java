import java.util.*;

class Solution {
    static int[][] map;
    static int itemX, itemY;
    
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, 1, -1};
    
    static boolean[][] visited;
    static int[][] distance;
    
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        
        map = new int[101][101];
        
        this.itemX = itemX * 2;
        this.itemY = itemY * 2;
        
        visited = new boolean[101][101];
        distance = new int[101][101];
        
        for (int[] rect : rectangle) {
            int x1 = rect[0] * 2;
            int y1 = rect[1] * 2;
            int x2 = rect[2] * 2;
            int y2 = rect[3] * 2;
            
            // 1. 모든 직사각형 영역 칠하기
            for (int x = x1; x <= x2; x++) {
                for (int y = y1; y <= y2; y++) {
                    map[x][y] = 1;
                }
            }
        }
        
        for (int[] rect : rectangle) {
            int x1 = rect[0] * 2;
            int y1 = rect[1] * 2;
            int x2 = rect[2] * 2;
            int y2 = rect[3] * 2;
            
            // 2. 내부만 지우기
            for (int x = x1 + 1; x < x2; x++) {
                for (int y = y1 + 1; y < y2; y++) {
                    map[x][y] = 0;
                }
            }
        }
        
        characterX *= 2;
        characterY *= 2;   
        
        bfs(characterX, characterY);
        
        return distance[this.itemX][this.itemY] / 2;
    }
    
    private static void bfs(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {x, y});
        visited[x][y] = true;
        
        while(!q.isEmpty()) {
            int[] now = q.poll();
            
            if (now[0] == itemX && now[1] == itemY) break;
            
            for (int i = 0; i < 4; i++) {
                int nr = now[0] + dr[i];
                int nc = now[1] + dc[i];
                
                
                if (nr < 0 || nc < 0 || nr >= 101 || nc >= 101) continue;
                
                if (visited[nr][nc]) continue;
                
                // 1만 길이라서 1이 아니면 pass
                if (map[nr][nc] != 1) continue;
                
                q.add(new int[] {nr, nc});
                visited[nr][nc] = true;
                distance[nr][nc] = distance[now[0]][now[1]] + 1;
            }
        }
        
        
    }
}