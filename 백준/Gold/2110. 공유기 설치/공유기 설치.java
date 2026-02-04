import java.util.*;
import java.io.*;
public class Main{
    static int N;
    static int C;
    static int[] arr;
    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        N = sc.nextInt();
        C = sc.nextInt();
        
        arr = new int[N];
        
        for(int i = 0; i < N; i++) { 
            arr[i] = sc.nextInt(); 
        }
        
        Arrays.sort(arr);
        
        int left = 1;
        int right = arr[N - 1] - arr[0];
        int answer = 0;
        while(left <= right) {
            int mid = (left + right) / 2;
            
            if(greedy(mid)) {
                answer = mid;
                left = mid + 1;
            }
            else right = mid - 1;
        }
        
        
        System.out.println(answer);
        sc.close();
        return;
    }
    
    public static boolean greedy(int d) {
        int count = 1;
        int last = arr[0];
        
        for(int i = 1; i < N; i++) {
            if(arr[i] - last >= d) {
                count++;
                last = arr[i];
            }
        }
        
        if(count >= C) return true;
        else return false;
    }
}