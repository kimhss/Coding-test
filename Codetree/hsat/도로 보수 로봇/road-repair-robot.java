import java.util.*;

public class Main {
    static int N;
    static int K;
    static int[] positions;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        K = scanner.nextInt();
        positions = new int[N]; // 구멍 위치

        for (int i = 0; i < N; i++) {
            positions[i] = scanner.nextInt();
        }

        int left = 1;
        int right = positions[N - 1] - positions[0] + 1;
        int len = 0;
        while (left <= right) {
            int mid = (left + right) / 2;

            if (possible(mid)) {
                right = mid - 1;
                len = mid;
            }

            else {
                left = mid + 1;
            }

        }

        System.out.println(len);

    }

    public static boolean possible(int len) {
        int count = 0;
        
        int num = 0;
        for (int i = 0; i < positions.length; i++) {
            if (positions[i] < num) {
                continue;
            }

            num = positions[i];
            
            // 방문
            num += len;
                        
            count++;
        }

        return count <= K;
    }
}