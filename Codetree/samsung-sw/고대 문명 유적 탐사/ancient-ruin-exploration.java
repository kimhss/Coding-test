import java.io.*;
import java.util.*;

public class Main {

    static int K, M;

    static int[][] map = new int[5][5];

    static int[] wall;
    static int wallIdx = 0;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in)
        );

        StringTokenizer st = new StringTokenizer(br.readLine());

        K = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int r = 0; r < 5; r++) {

            st = new StringTokenizer(br.readLine());

            for (int c = 0; c < 5; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        wall = new int[M];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < M; i++) {
            wall[i] = Integer.parseInt(st.nextToken());
        }

        for (int turn = 0; turn < K; turn++) {

            int bestScore = 0;
            int bestR = -1;
            int bestC = -1;
            int bestAngle = -1;

            // 작은 각도 -> 작은 열 -> 작은 행
            for (int angle = 1; angle <= 3; angle++) {

                for (int c = 1; c <= 3; c++) {

                    for (int r = 1; r <= 3; r++) {

                        int[][] copy = copyMap(map);

                        rotate(copy, r, c, angle);

                        int score = getScore(copy);

                        // 동점이면 기존 후보 유지
                        if (score > bestScore) {

                            bestScore = score;
                            bestR = r;
                            bestC = c;
                            bestAngle = angle;
                        }
                    }
                }
            }

            // 획득 가능한 유물이 없으면 탐사 종료
            if (bestScore == 0)
                break;

            // 실제 회전
            rotate(map, bestR, bestC, bestAngle);

            int total = 0;

            // 연쇄 획득
            while (true) {

                int score = remove(map);

                if (score == 0)
                    break;

                total += score;

                fill(map);
            }

            System.out.print(total + " ");
        }
    }

    static int[][] copyMap(int[][] original) {

        int[][] copy = new int[5][5];

        for (int r = 0; r < 5; r++) {
            copy[r] = original[r].clone();
        }

        return copy;
    }

    static void rotate(int[][] map, int cr, int cc, int angle) {

        for (int i = 0; i < angle; i++) {
            rotate90(map, cr, cc);
        }
    }

    static void rotate90(int[][] map, int cr, int cc) {

        int[][] temp = new int[3][3];

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {

                temp[c][2 - r]
                        = map[cr - 1 + r][cc - 1 + c];
            }
        }

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {

                map[cr - 1 + r][cc - 1 + c]
                        = temp[r][c];
            }
        }
    }

    // 실제 map을 변경하지 않고 획득 가능한 개수만 계산
    static int getScore(int[][] map) {

        boolean[][] visited = new boolean[5][5];

        int total = 0;

        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 5; c++) {

                if (visited[r][c])
                    continue;

                Queue<int[]> q = new LinkedList<>();

                q.add(new int[]{r, c});
                visited[r][c] = true;

                int value = map[r][c];
                int count = 0;

                while (!q.isEmpty()) {

                    int[] now = q.poll();
                    count++;

                    for (int d = 0; d < 4; d++) {

                        int nr = now[0] + dr[d];
                        int nc = now[1] + dc[d];

                        if (nr < 0 || nr >= 5 ||
                            nc < 0 || nc >= 5)
                            continue;

                        if (visited[nr][nc])
                            continue;

                        if (map[nr][nc] != value)
                            continue;

                        visited[nr][nc] = true;
                        q.add(new int[]{nr, nc});
                    }
                }

                if (count >= 3) {
                    total += count;
                }
            }
        }

        return total;
    }

    // 실제 제거
    static int remove(int[][] map) {

        boolean[][] visited = new boolean[5][5];
        boolean[][] deleted = new boolean[5][5];

        int total = 0;

        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 5; c++) {

                if (visited[r][c])
                    continue;

                Queue<int[]> q = new LinkedList<>();
                List<int[]> group = new ArrayList<>();

                q.add(new int[]{r, c});
                visited[r][c] = true;

                int value = map[r][c];

                while (!q.isEmpty()) {

                    int[] now = q.poll();

                    group.add(now);

                    for (int d = 0; d < 4; d++) {

                        int nr = now[0] + dr[d];
                        int nc = now[1] + dc[d];

                        if (nr < 0 || nr >= 5 ||
                            nc < 0 || nc >= 5)
                            continue;

                        if (visited[nr][nc])
                            continue;

                        if (map[nr][nc] != value)
                            continue;

                        visited[nr][nc] = true;
                        q.add(new int[]{nr, nc});
                    }
                }

                if (group.size() >= 3) {

                    total += group.size();

                    for (int[] pos : group) {
                        deleted[pos[0]][pos[1]] = true;
                    }
                }
            }
        }

        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 5; c++) {

                if (deleted[r][c]) {
                    map[r][c] = 0;
                }
            }
        }

        return total;
    }

    static void fill(int[][] map) {

        // 작은 열 -> 큰 행
        for (int c = 0; c < 5; c++) {

            for (int r = 4; r >= 0; r--) {

                if (map[r][c] == 0) {
                    map[r][c] = wall[wallIdx++];
                }
            }
        }
    }
}