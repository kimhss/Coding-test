import java.util.*;
import java.io.*;

public class Main {

    static int[][] board;
    static int N, M;

    static boolean[][] visited;
    static List<int[]> selected;

    static Set<String> moduleSet = new HashSet<>();

    static List<List<int[]>> modules = new ArrayList<>();

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        
        board = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                board[i][j] = sc.nextInt();
            }
        }
        // Please write your code here.

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited = new boolean[N][M];
                selected = new ArrayList<>();

                visited[i][j] = true;
                selected.add(new int[] {i, j});
                dfs(1);
            }
        }

        int max = Integer.MIN_VALUE;

        // 조합 : 겹치는 module 2개 찾아서 
        for (int i = 0; i < modules.size(); i++) {
            for (int j = i + 1; j < modules.size(); j++) {
                // modules.get(i), modules.get(j)
                // 두 배터리 비교

                boolean overlap = getOverlap(modules.get(i), modules.get(j));
                
                if (!overlap) continue;

                // 계산
                int value = calculation(modules.get(i), modules.get(j));
                max = Math.max(max, value);

            }
        }

        System.out.println(max);

    }

    private static int calculation(List<int[]> a, List<int[]> b) {
        int sum = 0;

        for (int[] arr : a) {
            sum += board[arr[0]][arr[1]];
        }

        for (int[] brr : b) {
            sum += board[brr[0]][brr[1]];
        }

        return sum;
    }

    private static boolean getOverlap(List<int[]> a, List<int[]> b) {
        int count = 0;
        
        for (int[] arr : a) {
            int ax = arr[0];
            int ay = arr[1];

            for (int[] brr : b) {
                int bx = brr[0];
                int by = brr[1];

                if (ax == bx && ay == by) {
                    count++;
                }

            }

        }

        return count == 2;
    }

    private static String makeKey() {
        List<int[]> copy = new ArrayList<>(selected);

        // List<int[]> 정렬
        copy.sort((a, b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];
            }

            return a[0] - b[0];
        });

        StringBuilder sb = new StringBuilder();

        for (int[] p : copy) {
            sb.append(p[0])
            .append(",")
            .append(p[1])
            .append("/");
        }

        return sb.toString();
    }

    // 5개 정하는 함수
    private static void dfs(int count) {
        
        if (count == 5) {

            // 중복은 저장 안 함
            String key = makeKey();

            if (moduleSet.add(key)) {
                modules.add(new ArrayList<>(selected));
            }
            
            return;
        }

        // size 고정 (selected가 for문에서 계속 증가하기 때문)
        int size = selected.size();

        for (int i = 0; i < size; i++) {
            
            int r = selected.get(i)[0];
            int c = selected.get(i)[1];

            for (int j = 0; j < 4; j++) {
            
                int nr = r + dr[j];
                int nc = c + dc[j];

                // 범위 체크
                if (nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
                
                if (visited[nr][nc]) continue;

                // 선택함
                visited[nr][nc] = true;
                selected.add(new int[] {nr, nc});
                dfs(count + 1);

                // 선택 취소
                visited[nr][nc] = false;
                selected.remove(selected.size() - 1);
            }

        }

    }
}