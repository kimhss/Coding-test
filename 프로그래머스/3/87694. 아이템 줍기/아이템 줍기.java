import java.util.*;

class Solution {
    static int[][] map;
    
    static int characterX;
    static int characterY;
    
    static int itemX;
    static int itemY;
    
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {-1, 0, 1, 0};
    
    static boolean[][] visited;
    static int[][] distances;
    
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int answer = 0;
        
        map = new int[101][101];
        visited = new boolean[101][101];
        distances = new int[101][101];
        
        this.characterX = characterX * 2;
        this.characterY = characterY * 2;
        
        this.itemX = itemX * 2;
        this.itemY = itemY * 2;
        
        // 직사각형 1로 채우기
        for (int i = 0; i < rectangle.length; i++) {
            int x1 = rectangle[i][0] * 2;
            int y1 = rectangle[i][1] * 2;
            int x2 = rectangle[i][2] * 2;
            int y2 = rectangle[i][3] * 2;
            
            for (int r = x1; r <= x2; r++) {
                for (int c = y1; c <= y2; c++) {
                    map[r][c] = 1;
                }
            }
        }
        
        // 내부 0 으로 채우기
        for (int i = 0; i < rectangle.length; i++) {
            int x1 = rectangle[i][0] * 2;
            int y1 = rectangle[i][1] * 2;
            int x2 = rectangle[i][2] * 2;
            int y2 = rectangle[i][3] * 2;
            
            for (int r = x1 + 1; r < x2; r++) {
                for (int c = y1 + 1; c < y2; c++) {
                    map[r][c] = 0;
                }
            }
        }
        
        BFS(this.characterX, this.characterY);
        
        
        return distances[this.itemX][this.itemY] / 2;
    }
    
    private static void BFS(int r, int c) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {r, c});
        visited[r][c] = true;
        
        while(!q.isEmpty()) {
            int[] now = q.poll();
            
            if (now[0] == itemX && now[1] == itemY) 
                break;
            
            for (int i = 0; i < 4; i++) {
                int nx = now[0] + dr[i];
                int ny = now[1] + dc[i];
                
                if (nx < 0 || ny < 0 || nx >= 101 || ny >= 101) continue;
                if (visited[nx][ny]) continue;
                if (map[nx][ny] != 1) continue;
                
                q.add(new int[] {nx, ny});
                visited[nx][ny] = true;
                distances[nx][ny] = distances[now[0]][now[1]] + 1;
            }
        }
        
    }
}