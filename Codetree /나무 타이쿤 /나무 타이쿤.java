import java.util.*;
import java.io.*;

public class Main {
    static int N;  // 격자의 크기
    static int M;  // 리브로수를 키우는 총 년 수

    static int[][] tree;

    static int[] dr = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dc = {0, 1, 1, 0, -1, -1, -1, 0, 1};

    public static void main(String[] args) {
        // Please write your code here.

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        tree = new int[N][N];

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                tree[i][j] = sc.nextInt();
            }
        }


        // 방법 1. 리스트
        List<int[]> tonics = new ArrayList<>();
        tonics.add(new int[] {N - 1, 0});
        tonics.add(new int[] {N - 1, 1});
        tonics.add(new int[] {N - 2, 0});
        tonics.add(new int[] {N - 2, 1});

        // 방법 2. boolean 배열
        // boolean[][] tonics2 = new boolean[N][N];

        for(int i = 0; i < M; i++) {
            boolean[][] tonics2 = new boolean[N][N];

            int d = sc.nextInt();  // 이동 방향
            int p = sc.nextInt();  // 이동 칸 수

            for(int[] tonic : tonics) {
                tonic[0] = (tonic[0] + dr[d] * p + N) % N;
                tonic[1] = (tonic[1] + dc[d] * p + N) % N;
            }

            for(int[] tonic : tonics) {
                int r = tonic[0];
                int c = tonic[1]; 

                tree[r][c]++;
            }

            for(int[] tonic : tonics) {
                int r = tonic[0];
                int c = tonic[1];

                tonics2[r][c] = true;

                for (int j = 2; j <= 8; j += 2) {
                    int nr = r + dr[j];
                    int nc = c + dc[j];

                    if (nr < 0 || nc < 0 || nr >= N || nc >= N) continue;

                    if (tree[nr][nc] >= 1) tree[r][c]++;
                }
            }

            tonics.clear();

            for(int j = 0; j < N; j++) {
                for(int k = 0; k < N; k++) {
                    if (tree[j][k] >= 2 && !tonics2[j][k]) {
                        tonics.add(new int[] {j, k});
                        tree[j][k] -= 2;
                    }
                }
            }


        }

        int sum = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                sum += tree[i][j];
            }
        }

        System.out.println(sum);



    }
}
