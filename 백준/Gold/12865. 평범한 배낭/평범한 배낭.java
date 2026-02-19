import java.util.*;
import java.io.*;
public class Main{
    static int N, K;
    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        N = sc.nextInt();
        K = sc.nextInt();
        int[][] arr = new int[N][2];
        int[] dp = new int[K+1]; // dp[w] = 무게가 w일때 용량 v
        
        for(int i = 0; i < N; i++) {
            int W = sc.nextInt();  // 무게
            int V = sc.nextInt();  // 가치
            
            arr[i][0] = W;
            arr[i][1] = V;
            
            for (int w = K; w >= W; w--) {
                dp[w] = Math.max(dp[w], dp[w - W] + V);
            }
        }
        
        
        System.out.println(dp[K]);
        
        sc.close();
        return;
    }
}