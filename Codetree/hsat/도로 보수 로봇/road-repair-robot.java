import java.util.Scanner;
public class Main {

    static int N, K;
    static int[] positions;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        K = scanner.nextInt();
        positions = new int[N];
        for (int i = 0; i < N; i++) {
            positions[i] = scanner.nextInt();
        }
        // Please write your code here.

        int answer = 0;

        int left = 1;
        int right = positions[N - 1];

        while (left <= right) {
            int mid = (left + right) / 2;

            if (possible(mid)) {
                right = mid - 1;
                answer = mid;
            } 
            else {
                left = mid + 1;
            }
        }

        System.out.println(answer);
    }

    static boolean possible(int mid) {
        
        int idx = 0;
        int count = 0;

        int i = 0;

        while(idx < N) {

            if (i >= positions[idx]) {
                idx++;
                continue;
            }

            i = positions[idx];
            i += mid - 1;
            count++;
            idx++;
        }

        return count <= K;
    }
}