import java.util.*;
import java.io.*;
public class Main{
    static int N;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};
    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        char[][] A = new char[N][N];
        char[][] B = new char[N][N];
        boolean[][] aVisited = new boolean[N][N];
        boolean[][] bVisited = new boolean[N][N];
        
        
        for(int i = 0; i < N; i++) {
            String line = sc.next();
            String _line = line.replaceAll("R", "G");
            for(int j = 0; j < N; j++) {
                A[i][j] = line.charAt(j);
                B[i][j] = _line.charAt(j);
            }
        }

 
        int normal = 0;
        int yes = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(aVisited[i][j]) continue;
                normal += BFS(A, i, j, aVisited);
            }
        }
        
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(bVisited[i][j]) continue;
                yes += BFS(B, i, j, bVisited);
            }
        }
        
        System.out.println(normal + " " + yes);
        
        sc.close();
        return;
    }
    
    public static int BFS(char[][] arr, int _x, int _y, boolean[][] visited) {
        Queue<int[]> q = new LinkedList<>();
        char target = arr[_x][_y];
        q.offer(new int[] {_x, _y});
        visited[_x][_y] = true;
        
        while(!q.isEmpty()) {
            int[] now = q.poll();
            
            for(int i = 0; i < 4; i++) {
                int x = now[0] + dx[i];
                int y = now[1] + dy[i];
                
                // ⭐ 경계 체크만 수정
                if (x < 0 || x >= N || y < 0 || y >= N) continue;
                
                if(arr[x][y] != target) continue;
                if(visited[x][y]) continue;
                
                q.offer(new int[] {x, y});
                visited[x][y] = true;
            }
        }
        
        return 1;
    }
}
