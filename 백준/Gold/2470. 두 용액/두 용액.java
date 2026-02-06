import java.util.*;
import java.io.*;

public class Main {
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

        int start = 0;
        int end = N - 1;

        long min = Math.abs(arr[start] + arr[end]);
        int left = start;
        int right = end;

        while (end > start) {
            long sum = arr[start] + arr[end];

            if (Math.abs(sum) < min) {
                min = Math.abs(sum);
                left = start;
                right = end;
            }

            if (sum < 0) {
                start++;
            } else {
                end--;
            }
        }

        System.out.println(arr[left] + " " + arr[right]);
    }
}
