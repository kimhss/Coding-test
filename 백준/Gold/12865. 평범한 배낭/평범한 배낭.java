import java.util.*;
import java.io.*;
public class Main{
    static int K, N;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        N = sc.nextInt();
        K = sc.nextInt();
        
        int[] dp = new int[K + 1];
        
        for(int i = 0; i < N; i++) {
            int w = sc.nextInt();
            int v = sc.nextInt();
            
            for(int j = K; j >= w; j--) {
                dp[j] = Math.max(dp[j], dp[j - w] + v);
            }
        }
        
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < dp.length; i++) {
            max = Math.max(dp[i], max);
        }
        
        System.out.println(max);
        sc.close();
        return;
    }
}