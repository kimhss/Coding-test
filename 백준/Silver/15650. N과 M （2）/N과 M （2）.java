import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static int[] arr;   // 선택한 숫자 저장

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        arr = new int[M];

        DFS(0, 1);

        sc.close();
    }

    public static void DFS(int depth, int start) {

        // M개 다 고르면 출력
        if (depth == M) {
            for (int i = 0; i < M; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
            return;
        }

        // start부터 N까지 탐색
        for (int i = start; i <= N; i++) {
            arr[depth] = i;         // 선택
            DFS(depth + 1, i + 1);  // 다음 단계 (i+1 중요!)
        }
    }
}
