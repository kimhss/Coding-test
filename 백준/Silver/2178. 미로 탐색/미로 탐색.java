import java.util.*;
import java.io.*;
public class Main{
    static int N;
    static int M;
    static int[][] arr;
    
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        String s = sc.nextLine();
        N = Integer.parseInt(s.split(" ")[0]);
        M = Integer.parseInt(s.split(" ")[1]);
        
        arr = new int[N][M];
        
        for(int i = 0; i < N; i++) {
            s = sc.nextLine();
            for(int j = 0; j < M; j++) {
                arr[i][j] = s.charAt(j) - '0';
            }
        }
        
        //BFS 
        // target x = N - 1; y = M - 1;
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {0, 0});
        
        while(!q.isEmpty()) {
            int[] now = q.poll();
            
            if (now[0] == N - 1 && now[1] == M - 1) break;
            
            for(int i = 0; i < 4; i++) {
                int x = now[0] + dx[i];
                int y = now[1] + dy[i];
                
                if(x >= N || x < 0 || y >= M || y < 0) continue;
                if(arr[x][y] != 1) continue;
                
                q.offer(new int[] {x, y});
                arr[x][y] = arr[now[0]][now[1]] + 1;
            }
        }
        
        System.out.println(arr[N - 1][M - 1]);
        
        sc.close();
        return;
    }
}
