import java.util.*;
import java.io.*;
public class Main{
    static int N;
    static int[] arr;
    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        N = sc.nextInt();
        arr = new int[N];
        
        for(int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }
        
        int[] dp = new int[N];
        
        dp[0] = 1;
        int max = 1;
        
        for(int i = 1; i < N; i++) {
            int count = 0;
            for(int j = 0; j < i; j++) {
                if(arr[i] > arr[j] && count < dp[j]) {
                    count = dp[j];
                }
            }
            dp[i] = count + 1;
            max = Math.max(dp[i], max);
        }
        
        System.out.println(max);
        
        sc.close();
        return;
    }
}