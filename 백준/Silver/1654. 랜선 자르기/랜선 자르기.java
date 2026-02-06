import java.io.*;
import java.util.*;

public class Main {
    static int K;
    static int N;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        arr = new int[K];

        for (int i = 0; i < K; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        long left = 1;
        long right = arr[K - 1];
        long answer = 0;

        while (left <= right) {
            long mid = left + (right - left) / 2;

            if (greedy(mid)) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.println(answer);
    }

    public static boolean greedy(long d) {
        long count = 0;

        for (int i = 0; i < K; i++) {
            count += arr[i] / d;
        }

        return count >= N;
    }
}
