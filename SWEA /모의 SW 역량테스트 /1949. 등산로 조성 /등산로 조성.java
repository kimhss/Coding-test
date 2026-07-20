import java.util.*;
import java.io.*;

public class Main {
    static int T;
    static int N;
    static int K;

    static int[][] map;
    static boolean[][] visited;
    static boolean minusK;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    static int MAX_VALUE;

    public static void main(String[] args) throws Exception {
        // Please write your code here.
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st;
        Scanner sc = new Scanner(System.in);

        T = sc.nextInt();

        for(int i = 0; i < T; i++) {
            N = sc.nextInt();
            K = sc.nextInt();
            map = new int[N][N];
            int max = 0;

            for(int j = 0; j < N; j++) {
                for(int k = 0; k < N; k++) {
                    map[j][k] = sc.nextInt();

                    max = Math.max(max, map[j][k]);
                }
            }

            MAX_VALUE = Integer.MIN_VALUE;

            for(int j = 0; j < N; j++) {
                for(int k = 0; k < N; k++) {
                    if (map[j][k] == max) {
                        visited = new boolean[N][N];
                        visited[j][k] = true;
                        dfs(j, k, 1);
                    }
                }
            }

            System.out.println("#" + (i + 1) + " " + MAX_VALUE);
        }
    }

    public static void dfs(int j, int k, int depth) {
        for (int i = 0; i < 4; i++) {
            int nx = j + dx[i];
            int ny = k + dy[i];

            if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
            if (visited[nx][ny]) continue;

            if (map[nx][ny] >= map[j][k]) {
                // 값을 k만큼 깎는다.
                if(map[nx][ny] - K < map[j][k] && !minusK) {
                    // 한 번 깎지 않았다면
                    int tmp = map[nx][ny];
                    map[nx][ny] = map[j][k] - 1;
                    visited[nx][ny] = true;
                    minusK = true;

                    dfs(nx, ny, depth + 1);

                    map[nx][ny] = tmp;
                    visited[nx][ny] = false;
                    minusK = false;
                }

                else continue;
            }
            else {
                visited[nx][ny] = true;
                dfs(nx, ny, depth + 1);
                visited[nx][ny] = false;
            }
        }

        MAX_VALUE = Math.max(MAX_VALUE, depth);
    }
}
