import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static int result = Integer.MAX_VALUE;
    static List<int[]> home = new ArrayList<>();
    static List<int[]> chicken = new ArrayList<>();
    static List<int[]> selected = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        // 0은 빈 칸, 1은 집, 2는 치킨집
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int n = sc.nextInt();

                if (n == 1) home.add(new int[]{i, j});
                else if (n == 2) chicken.add(new int[]{i, j});
            }
        }

        DFS(0, 0);

        System.out.println(result);
        sc.close();
    }

    public static void DFS(int start, int depth) {
        if (depth == M) {
            calculate();
            return;
        }

        for (int i = start; i < chicken.size(); i++) {
            selected.add(chicken.get(i));
            DFS(i + 1, depth + 1);
            selected.remove(selected.size() - 1);
        }
    }

    public static void calculate() {
        int total = 0;

        for (int[] h : home) {
            int min = Integer.MAX_VALUE;

            int hx = h[0];
            int hy = h[1];

            for (int[] c : selected) {
                int cx = c[0];
                int cy = c[1];

                int d = Math.abs(hx - cx) + Math.abs(hy - cy);
                min = Math.min(d, min);
            }

            total += min;
        }

        result = Math.min(total, result);
    }
}