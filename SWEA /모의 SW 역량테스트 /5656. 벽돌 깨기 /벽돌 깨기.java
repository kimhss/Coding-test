import java.util.*;
import java.io.*;

class Main {
    static int N, W, H;
    static int[][] map;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, 1, -1};

    static List<int[]> dropped;
    static int[][] copyMap;

    static int min_count;

    public static void main(String args[]) throws Exception {

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++) {
            N = sc.nextInt();
            W = sc.nextInt();
            H = sc.nextInt();

            map = new int[H][W];
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    map[i][j] = sc.nextInt();
                }
            }

            min_count = Integer.MAX_VALUE;

            dropped = new ArrayList<>();
            dfs(0);

            System.out.println("#" + test_case + " " + min_count);

        }
    }

    private static void dfs(int count) {
        // N개 중복 순열 선택
        if (count == N) {
            copyMap = new int[H][W];

            // 시뮬레이션 용 맵 복사
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    copyMap[i][j] = map[i][j];
                }
            }

            for(int[] drop : dropped) {
                drop(drop[0], drop[1]);
                move();
            }

            min_count = Math.min(min_count, count());

            return;
        }

        for (int i = 0; i < W; i++) {
            // 선택
            dropped.add(new int[] {0, i});
            dfs(count + 1);

            // 선택 취소
            dropped.remove(dropped.size() - 1);
        }
    }

    // 구슬 떨어트리기
    public static void drop(int r, int c) {

        while (r < H && copyMap[r][c] <= 0) {
            r++;
        }

        if (r == H) return;

        int num = copyMap[r][c];
        copyMap[r][c] = 0;

        // 4방향 제거
        for (int i = 0; i < 4; i++) {

            for (int j = 1; j < num; j++) {
                int nr = r + dr[i] * j;
                int nc = c + dc[i] * j;

                // 범위 벗어나면 pass
                if (nr < 0 || nc < 0 || nr >= H || nc >= W) {
                    continue;
                }

                if (copyMap[nr][nc] > 1) {
                    // q나 stack에 넣기
                    drop(nr, nc);
                }

                else if (copyMap[nr][nc] == 1) {
                    copyMap[nr][nc] = 0;
                }
            }
        }
    }

    // 블럭 아래로 내리기
    private static void move() {

        for (int c = 0; c < W; c++) {
            int empty = 0;

            for (int r = H - 1; r >= 0; r--) {

                // 0이면
                if (copyMap[r][c] == 0) {
                    empty++;
                }

                // empty 수만큼 내리기
                else if (copyMap[r][c] != 0 && empty != 0) {
                    int tmp = copyMap[r][c];

                    copyMap[r + empty][c] = tmp;
                    copyMap[r][c] = 0;
                }

            }
        }
    }

    private static int count() {
        int count = 0;

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (copyMap[i][j] != 0) count++;
            }
        }

        return count;
    }
}
