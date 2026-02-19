import java.util.*;
import java.io.*;
public class Main{
    static int N;
    static int[] L, J;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        N = sc.nextInt();
        L = new int[N];
        J = new int[N];
        
        int[] dp = new int[100 + 1];
        
        for(int i = 0; i < N; i++) {
            L[i] = sc.nextInt();
        }
        
        for(int i = 0; i < N; i++) {
            J[i] = sc.nextInt();
            
            for(int j = 99; j >= L[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - L[i]] + J[i]);
            }
        }
        
        int max = 0;
        for(int i = 1; i < 100; i++) {
            max = Math.max(dp[i], max);
        }
        
        System.out.println(max);
        sc.close();
        return;
    }
}