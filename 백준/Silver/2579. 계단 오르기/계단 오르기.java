import java.util.*;
import java.io.*;
public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        int N = sc.nextInt();
        int[] arr = new int[N];
        
        for(int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }
        
        int[] dp = new int[N + 1];
        
        dp[1] = arr[0];
        
        for(int i = 2; i <= N; i++) {
            if (i == 2) 
                dp[2] = arr[0] + arr[1];
            else if (i == 3)
                dp[3] = Math.max(arr[0] + arr[2], arr[1] + arr[2]);
            else
                dp[i] = Math.max(dp[i - 2], dp[i - 3] + arr[i - 2]) + arr[i - 1];
        }
        
        System.out.println(dp[N]);
        sc.close();
        return;
    }
}