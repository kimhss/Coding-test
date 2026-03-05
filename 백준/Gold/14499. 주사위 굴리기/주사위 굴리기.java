import java.util.*;
import java.io.*;
public class Main{
    static int N, M, x, y, K;
    static int[][] map;
    static int[] dice;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        N = sc.nextInt();
        M = sc.nextInt();
        
        x = sc.nextInt();
        y = sc.nextInt();
        
        K = sc.nextInt();
        
        map = new int[N][M];
        dice = new int[6];
        // dice[0] 윗면
        // dice[1] 아랫면
        // dice[2] 동
        // dice[3] 서
        // dice[4] 남
        // dice[5] 북
        
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                map[i][j] = sc.nextInt();
            }
        }
        
        String[] str = sc.nextLine().split(" ");
       
        for(int i = 0; i < K; i++) {
            int n = sc.nextInt();
            move(n);
        }
        
        sc.close();
        return;
    }
    
    public static void move(int n) {
        int nx = dx[n - 1] + x;
        int ny = dy[n - 1] + y;
        
        if(nx < 0 || nx >= N || ny < 0 || ny >= M) {
            return;
        }
        
        roll(n, nx, ny);
    }
    
    public static void roll(int n, int nx, int ny) {
        x = nx;
        y = ny;
        int tmp = dice[0];
        
        switch(n) {
            case 4:
            dice[0] = dice[5];
            dice[5] = dice[1];
            dice[1] = dice[4];
            dice[4] = tmp;
            break;
            
            case 3:
            dice[0] = dice[4];
            dice[4] = dice[1];
            dice[1] = dice[5];
            dice[5] = tmp;
            break;
            
            case 2:
            dice[0] = dice[2];
            dice[2] = dice[1];
            dice[1] = dice[3];
            dice[3] = tmp;
            break;
            
            case 1:
            dice[0] = dice[3];
            dice[3] = dice[1];
            dice[1] = dice[2];
            dice[2] = tmp;
            break;
        } 
        
        if (map[x][y] != 0) {
            dice[1] = map[x][y];
            map[x][y] = 0;
        }
        else {
            map[x][y] = dice[1];
        }
        
        System.out.println(dice[0]);
    }
}
