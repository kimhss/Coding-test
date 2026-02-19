import java.util.*;
import java.io.*;
public class Main{
    static int N, M;
    static int[] A, C;
    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        N = sc.nextInt();
        M = sc.nextInt();
        A = new int[N];
        C = new int[N];
        
        int[] dp = new int[10000 + 1];
        
        for(int i = 0; i < N; i++) {
            A[i] = sc.nextInt();
        }
        
        int totalCoast = 0;
        for(int i = 0; i < N; i++) {
            C[i] = sc.nextInt();
            totalCoast += C[i];
        }
        
        for(int i = 0; i < N; i++) {
            for(int j = totalCoast; j >= C[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - C[i]] + A[i]);
            }
        }
        
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < dp.length; i++) {
            if (dp[i] >= M) {
                min = Math.min(min, i);
            }
        }
        
        System.out.println(min);
        
        sc.close();
        return;
    }
}