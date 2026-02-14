import java.util.*;
import java.io.*;
public class Main{
    static int N, M;
    static int[][] arr;
    static List<int[]> empty;
    static List<int[]> virus;
    
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        N = sc.nextInt();
        M = sc.nextInt();
        arr = new int[N][M];
        empty = new ArrayList<>();
        virus = new ArrayList<>();
        
        
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                arr[i][j] = sc.nextInt();
                
                if(arr[i][j] == 2) {
                    virus.add(new int[] {i, j});
                }
                
                else if(arr[i][j] == 0) {
                    empty.add(new int[] {i, j});
                }
            }
        }
        
        // 벽 세개 세우고 -> BFS로 바이러스 퍼짐 관찰 -> 안전 영역 개수 세기
        int size = empty.size();
        int max = 0;
        
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                for (int k = j + 1; k < size; k++) {
                    
                    int[][] map = new int[N][M];
                    for (int a = 0; a < N; a++) {
                        map[a] = arr[a].clone();
                    }
                    
                    int[] wall1 = empty.get(i);
                    int[] wall2 = empty.get(j);
                    int[] wall3 = empty.get(k);
                    
                    map[wall1[0]][wall1[1]] = 1;
                    map[wall2[0]][wall2[1]] = 1;
                    map[wall3[0]][wall3[1]] = 1;
                    
                    // BFS -> 바이러스 확산
                    BFS(map);
                    // count -> 확산 후 남은 영역 계산
                    max = Math.max(max, safe(map));
                }
            }
        }
        
        System.out.println(max);
        
        sc.close();
        return;
    }
    
    public static void BFS(int[][] map) {
        Queue<int[]> q = new LinkedList<>();
        
        for(int[] v : virus) {
            q.offer(v);
        }
        
        while(!q.isEmpty()) {
            int[] now = q.poll();
            
            for(int i = 0; i < 4; i++) {
                int x = now[0] + dx[i];
                int y = now[1] + dy[i];
                
                if(x < 0 || x >= N || y < 0 || y >= M) continue;
                if(map[x][y] != 0) continue;
                
                q.offer(new int[] {x, y});
                map[x][y] = 2;
            }
        }
    }
    
    public static int safe(int[][] map) {
        int count = 0;
        
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] == 0) count++;
            }
        }
        
        return count;
    }
    
}