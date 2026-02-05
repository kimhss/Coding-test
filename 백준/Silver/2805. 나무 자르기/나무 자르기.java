import java.util.*;
import java.io.*;
public class Main{
    static int N;
    static int M;
    static int[] arr;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        N = sc.nextInt();
        M = sc.nextInt();
        arr = new int[N];
        
        for(int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }
        Arrays.sort(arr);
        
        int left = 0;
        int right = arr[N - 1];
        int max = 0;
        
        while(left <= right) {
            int mid = (left + right) / 2;
            
            if(greedy(mid)) {
                left = mid + 1;
                max = mid;
            }
            else right = mid - 1;
        }
        
        System.out.println(max);
        sc.close();
        return;
    }
    
    public static boolean greedy(int d) {
        
        long count = 0;
        for(int i = 0; i < N; i++) {
            if (arr[i] > d) count += arr[i] - d;
        }
        
        return count >= M;
    }
}