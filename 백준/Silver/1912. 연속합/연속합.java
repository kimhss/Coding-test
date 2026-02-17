import java.util.*;
import java.io.*;
public class Main{
    static int n;
    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
   
        n = sc.nextInt();
        int[] arr = new int[n];
        
        for(int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        
        int[] dp = new int[n + 1];
        
        dp[0] = arr[0];
        int max = dp[0];
        
        for(int i = 1; i < n; i++) {
            dp[i] = Math.max(dp[i - 1] + arr[i], arr[i]);
            max = Math.max(dp[i], max);
        }
        
        System.out.println(max);
        
        sc.close();
        return;
    }
}