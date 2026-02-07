import java.util.*;
import java.io.*;
public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        long[] A = new long[N];
        for (int i = 0; i < N; i++) {
            A[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(A);
        
        int count = 0;
        for(int k = 0; k < N; k++) {
            long find = A[k];
            int i = 0;
            int j = N - 1;
            while (i < j) {
               if(A[i] + A[j] == find) {
                   // find는 서로 다른 두 수의 합이어야 함을 체크
                   if(i != k && j != k) {
                       count++;
                       break;
                   }
                   else if (i == k) {
                       i++;
                   }
                   else if (j == k) {
                       j--;
                   }
               }
                else if (A[i] + A[j] > find) {
                    j--;
                }
                else
                    i++;
            }
        }
        
        System.out.println(count);
	    br.close();
        return;
    }
}