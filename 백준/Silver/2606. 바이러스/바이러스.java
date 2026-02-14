import java.util.*;

class Main {
    static int N; // 컴퓨터의 수
    static int M; // 연결된 컴퓨터 쌍의 수
    static ArrayList<Integer>[] A;
    static boolean[] visited;
    static int count = 0;
    
    static public void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        A = new ArrayList[N + 1];
        visited = new boolean[N + 1];
        
        for(int i = 1; i <=N; i++) {
            A[i] = new ArrayList<>();
        }
        
        for(int i = 1; i <= M; i++) {
            int s = sc.nextInt();
            int v = sc.nextInt();
            
            A[s].add(v);
            A[v].add(s);
        }
        
        
        BFS(1);
        System.out.println(count);
    }
    
    static void BFS(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = true;
        
        while(!q.isEmpty()) {
            int now = q.poll();
            
            for(int i : A[now]) {
                if(!visited[i]) {
                    visited[i] = true;
                    count++;
                    q.add(i);
                }
            }
        }
    }
}