import java.util.*;
import java.io.*;
public class Main{
    static int N, M;
    static List<Integer>[] A;
    static boolean[] visited;
    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        N = sc.nextInt();
        M = sc.nextInt();
        
        A = new ArrayList[N + 1];
        visited = new boolean[N + 1];
        
        for(int i = 0; i <= N; i++) {
            A[i] = new ArrayList<Integer>();
        }
        
        for(int i = 0; i < M; i++) {
            int n = sc.nextInt();
            int v = sc.nextInt();
            
            A[n].add(v);
            A[v].add(n);
        }
        
        //BFS
        int count = BFS();
        
        System.out.println(count);
        
        
        sc.close();
        return;
    }
    
    public static int BFS() {
        Queue<Integer> q = new LinkedList<>();
        q.offer(1);
        visited[1] = true;
        int count = 0;
        
        while(!q.isEmpty()) {
            int now = q.poll();
            
            for(int n : A[now]) {
                if(visited[n]) continue;
                q.offer(n);
                visited[n] = true;
                count++;
            }
        }
        
        return count;
    }
}