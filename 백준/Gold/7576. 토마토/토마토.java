import java.io.*;
import java.util.*;

public class Main {
    static int M, N;
    static int[][] arr;

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};

    static Queue<int[]> q = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // M = 열, N = 행
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        // 행(N) x 열(M)
        arr = new int[N][M];

        // 입력 + 초기 익은 토마토 큐에 삽입
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 1) {
                    q.offer(new int[]{i, j});
                }
            }
        }

        BFS();

        int max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] == 0) {
                    System.out.println(-1);
                    return;
                }
                max = Math.max(max, arr[i][j]);
            }
        }

        System.out.println(max - 1);
    }

    public static void BFS() {
        while (!q.isEmpty()) {
            int[] now = q.poll();

            for (int i = 0; i < 4; i++) {
                int x = now[0] + dx[i];
                int y = now[1] + dy[i];

                if (x < 0 || x >= N || y < 0 || y >= M) continue;
                if (arr[x][y] != 0) continue;

                arr[x][y] = arr[now[0]][now[1]] + 1;
                q.offer(new int[]{x, y});
            }
        }
    }
}
