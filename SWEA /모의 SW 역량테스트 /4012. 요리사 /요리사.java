import java.util.*;
import java.io.*;

public class Main {
    static int T;
    static int N;
    static int[][] arr;

    static boolean[] selected;
    static int MIN_VALUE;

    public static void main(String[] args) throws Exception {
        // Please write your code here.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        for(int i = 0; i < T; i++) {
            N = Integer.parseInt(br.readLine());

            arr = new int[N][N];
            selected = new boolean[N + 1];
            MIN_VALUE = Integer.MAX_VALUE;

            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < N; k++) {
                    arr[j][k] = Integer.parseInt(st.nextToken());
                }
            }

            DFS(0, 0);

            System.out.println("#" + (i + 1) + " " + MIN_VALUE);
        }
    }

    public static void DFS(int start, int depth) {
        if (depth == N / 2) {
            // 계산
            MIN_VALUE = Math.min(MIN_VALUE, calculate());
            return;
        }

        for (int i = start; i < N; i++) {
            selected[i] = true;
            DFS(i + 1, depth + 1);
            selected[i] = false;
        }
    }

    public static int calculate() {

        int A = 0;
        int B = 0;

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (selected[i] && selected[j]) {
                    A += arr[i][j] + arr[j][i];
                }
                else if (!selected[i] && !selected[j]) {
                    B += arr[i][j] + arr[j][i];
                }
            }
        }

        return Math.abs(A - B);
    }
}
