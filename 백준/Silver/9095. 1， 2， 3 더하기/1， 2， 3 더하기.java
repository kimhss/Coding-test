import java.util.*;
import java.io.*;
public class Main{
    static int T;
    static int[] arr;
    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        T = sc.nextInt();
        arr = new int[T];
        
        for(int i = 0; i < T; i++) {
            arr[i] = sc.nextInt();
        }
        
        int[] dp = new int[11];
        
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;
        
        for(int i = 4; i <= 10; i++){
            dp[i] = dp[i-1] + dp[i-2] + dp[i-3];
        }
        
        for(int i = 0; i < T; i++) {
            System.out.println(dp[arr[i]]);
        }
        
        sc.close();
        return;
    }
}
