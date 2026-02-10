import java.util.*;
import java.io.*;
public class Main{
    static int N;
    static int[][] arr;
    static boolean[][] visited;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};
    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        arr = new int[N][N];
        visited = new boolean[N][N];
        List<Integer> answer = new ArrayList<>();
        
        for(int i = 0; i < N; i++) {
            String line = sc.next();
            for(int j = 0; j < N; j++) {
                arr[i][j] = line.charAt(j) - '0';
            }
        }

 
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(visited[i][j]) continue;
                if(arr[i][j] == 0) continue;
                answer.add(BFS(i, j));
            }
        }
        
        Collections.sort(answer);
        System.out.println(answer.size());
        for(int n : answer) {
            System.out.println(n);
        }
        
        sc.close();
        return;
    }
    
    public static int BFS(int _x, int _y) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {_x, _y});
        visited[_x][_y] = true;
        int count = 1;
        
        while(!q.isEmpty()) {
            int[] now = q.poll();
            
            for(int i = 0; i < 4; i++) {
                int x = now[0] + dx[i];
                int y = now[1] + dy[i];
                
                // ⭐ 경계 체크만 수정
                if (x < 0 || x >= N || y < 0 || y >= N) continue;
                
                if(arr[x][y] == 0) continue;
                if(visited[x][y]) continue;
                
                q.offer(new int[] {x, y});
                visited[x][y] = true;
                count++;
            }
        }
        
        return count;
    }
}
