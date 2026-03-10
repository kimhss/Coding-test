import java.util.*;
import java.io.*;
public class Main{
    static int N;
    static long[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new long[N];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }
        
        Arrays.sort(arr);
        
        int count = 0;
        for (int i = 0; i < N; i++) {
            int start = 0;
            int end = N - 1;
            
            while(start < end) {                
                if (arr[i] == arr[start] + arr[end]) {
                    if (start != i && end != i) {
                        count++;
                        break;
                    }
                    
                    if (start == i) {
                        start++;
                    }
                    
                    else if (end == i) {
                        end--;
                    }
                }
                 
                else if (arr[start] + arr[end] > arr[i]) {
                    end--;
                }
                else start++;
            }
        }
        
        System.out.println(count);
	    br.close();
        return;
    }
}