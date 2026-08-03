import java.util.*;
import java.io.*;

class Solution {
    static int T;
    static int N;
    static int[][] map;

    static int[] dr = {1, 1, -1, -1};
    static int[] dc = {-1, 1, 1, -1};

    static Set<Integer> selected;
    static int[] startIdx = new int[2];

    static int max;

    public static void main(String[] args) throws Exception {
        // Please write your code here.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());

        for(int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());  // 도시 크기

            map = new int[N][N];

            for(int r = 0; r < N; r++) {
                st = new StringTokenizer(br.readLine());
                for(int c = 0; c < N; c++) {
                    map[r][c] = Integer.parseInt(st.nextToken());
                }
            }

            max = -1;

            for(int r = 0; r < N; r++) {
                for(int c = 0; c < N; c++) {
                    selected = new HashSet<>();

                    startIdx[0] = r;
                    startIdx[1] = c;

                    selected.add(map[r][c]);

                    selectProcess(r, c, 0, 1);
                }
            }

            System.out.println("#" + (i + 1) + " " + max);

        }
        
    }

    private static void selectProcess(int r, int c, int direction, int count) {
        for (int nextDirection = direction;
             nextDirection <= direction + 1;
             nextDirection++) {

            if (nextDirection >= 4) continue;

            int nr = r + dr[nextDirection];
            int nc = c + dc[nextDirection];

            if (nr < 0 || nc < 0 || nr >= N || nc >= N) continue;

            if (nr == startIdx[0] && nc == startIdx[1]) {
                /*
                 * 마지막 방향으로 돌아왔고,
                 * 최소 4개의 디저트를 먹은 경우에만 완성
                 */
                if (nextDirection == 3 && count >= 4)
                    max = Math.max(max, count);

                continue;
            }

            if (selected.contains(map[nr][nc]))
                continue;

            selected.add(map[nr][nc]);

            selectProcess(nr, nc, nextDirection, count + 1);

            selected.remove(map[nr][nc]);

        }
    }


}
