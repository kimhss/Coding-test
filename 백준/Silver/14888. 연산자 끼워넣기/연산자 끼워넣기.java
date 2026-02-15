import java.util.*;
import java.io.*;
public class Main{
    static int N, count;
    static int[] arr;
    static int[] calc;
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;
    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        N = sc.nextInt();
        arr = new int[N];
        
        for(int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }
        
        calc = new int[4];
        for(int i = 0; i < 4; i++) {
            calc[i] = sc.nextInt();
        }
        
        dfs(0, arr[0]);
        System.out.println(max);
        System.out.println(min);
        
        sc.close();
        return;
    }
    
    public static void dfs(int depth, int sum) {
        if (depth == N - 1) {
            max = Math.max(max, sum);
            min = Math.min(min, sum);
            return;
        }
        
        for(int j = 0; j < 4; j++) {
            if(calc[j] == 0) continue;
            
            int total = 0;
            switch(j){
                case 0:
                    total = sum + arr[depth + 1];
                    break;
                case 1:
                    total = sum - arr[depth + 1];
                    break;
                case 2:
                    total = sum * arr[depth + 1];
                    break;
                case 3:
                    total = sum / arr[depth + 1];
                    break;
            }
            //
            calc[j]--;
            dfs(depth + 1, total);
            calc[j]++; // 복구
        }
    }
}