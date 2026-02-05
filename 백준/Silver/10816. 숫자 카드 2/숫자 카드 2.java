import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int M;
    static int[] nArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        nArr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nArr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(nArr);

        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            int x = Integer.parseInt(st.nextToken());
            int count = upperBound(x) - lowerBound(x);
            sb.append(count).append(" ");
        }

        System.out.println(sb.toString());
    }

    // x 초과가 처음 등장하는 위치
    public static int upperBound(int target) {
        int l = 0, r = N;
        while (l < r) {
            int mid = (l + r) >>> 1;
            if (nArr[mid] > target) r = mid;
            else l = mid + 1;
        }
        return l;
    }

    // x 이상이 처음 등장하는 위치
    public static int lowerBound(int target) {
        int l = 0, r = N;
        while (l < r) {
            int mid = (l + r) >>> 1;
            if (nArr[mid] >= target) r = mid;
            else l = mid + 1;
        }
        return l;
    }
}
