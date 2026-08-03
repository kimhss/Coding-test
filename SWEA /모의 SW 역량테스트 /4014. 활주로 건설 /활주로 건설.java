import java.util.*;
import java.io.*;

class Main {
    static int T;
    static int N, X;
    static int[][] map;


    public static void main(String[] args) throws Exception {
        // Please write your code here.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());  // MAP 크기
            X = Integer.parseInt(st.nextToken());  // 경사로 길이 (높이는 항상 1)

            map = new int[N][N];

            for (int r = 0; r < N; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < N; c++) {
                    map[r][c] = Integer.parseInt(st.nextToken());
                }
            }

            int answer = 0;

            for (int r = 0; r < N; r++) {
                // 가로
                if (checkRow(r))
                    answer++;
                // 세로
                if (checkCol(r)) {
                    answer++;
                }
            }

            System.out.println("#" + (i + 1) + " " + answer);
        }

    }

    private static boolean checkCol(int c) {
        boolean[] used = new boolean[N];

        for(int r = 1; r < N; r++) {
            int diff = map[r][c] - map[r - 1][c];

            if (diff == 0) continue;

            // 높이 차이가 2 이상이면 활주로 건설 불가능
            if (Math.abs(diff) > 1) return false;

            // 오르막길 : 현재 칸이 이전 칸보다 1 높음
            if (diff == 1) {
                int lowHeight = map[r - 1][c];

                // 현재 위치 이전으로 X칸 검사
                for(int k = 1; k <= X; k++) {
                    int nr = r - k;

                    // 범위를 벗어나거나
                    // 높이가 다르거나
                    // 이미 경사로가 설치된 경우
                    if (nr < 0 ||
                            map[nr][c] != lowHeight ||
                            used[nr]) {
                        return false;
                    }

                    used[nr] = true;
                }
            }

            // 내리막: 현재 칸이 이전 칸보다 1 낮음
            if (diff == -1) {
                int lowHeight = map[r][c];

                // 현재 위치부터 앞으로 X칸 검사
                for(int k = 0; k < X; k++) {
                    int nr = r + k;

                    if (nr >= N ||
                            map[nr][c] != lowHeight ||
                            used[nr]) {
                        return false;
                    }

                    used[nr] = true;
                }
            }
        }

        return true;
    }

    private static boolean checkRow(int r) {
        boolean[] used = new boolean[N];

        for(int c = 1; c < N; c++) {
            int diff = map[r][c] - map[r][c - 1];

            if (diff == 0) continue;

            if (Math.abs(diff) > 1) return false;

            // 오르막길
            if (diff == 1) {
                int lowHeight = map[r][c - 1];

                for(int k = 1; k <= X; k++) {
                    int nc = c - k;

                    if (nc < 0 ||
                    map[r][nc] != lowHeight ||
                    used[nc]) {
                        return false;
                    }

                    used[nc] = true;
                }
            }

            // 내리막길
            if (diff == -1) {
                int lowHeight = map[r][c];

                for(int k = 0; k < X; k++) {
                    int nc = c + k;

                    if (nc >= N ||
                            map[r][nc] != lowHeight ||
                    used[nc]) {
                        return false;
                    }

                    used[nc] = true;
                }
            }
        }

        return true;
    }



}
