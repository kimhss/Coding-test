import java.util.*;
import java.io.*;
public class Main{
    static int T, M, N, K;
    static int[][] arr;
    
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};
    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        T = sc.nextInt();
        
        for(int i = 0; i < T; i++) {
            M = sc.nextInt();
            N = sc.nextInt();
            K = sc.nextInt();
            
            arr = new int[M][N];
            
            for(int j = 0; j < K; j++) {
                int x = sc.nextInt();
                int y = sc.nextInt();
                
                arr[x][y] = 1;
            }
            int count = 0;
            for(int j = 0; j < M; j++) {
                for(int k = 0; k < N; k++) {
                    if (arr[j][k] == 0) continue;
                    count += BFS(j, k);
                }
            }
            
            System.out.println(count);
        }
        
        sc.close();
        return;
    }
    
    public static int BFS(int i, int j) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {i, j});
        arr[i][j] = 0;
        
        while(!q.isEmpty()){
            int[] now = q.poll();
            
            for(int k = 0; k < 4; k++) {
                int x = now[0] + dx[k];
                int y = now[1] + dy[k];
                
                if (x < 0 || x >= M || y < 0 || y >= N) continue;
                if (arr[x][y] != 1) continue;
                
                q.offer(new int[] {x, y});
                arr[x][y] = 0;
            }
        }
        
        return 1;
    }
}