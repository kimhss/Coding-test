import java.util.*;
import java.io.*;

public class Main {

    static int N, M, K, C;
    static int[][] map;
    static int[][] herbicide;

    // 상하좌우
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    // 대각선
    static int[] dx = {-1, -1, 1, 1};
    static int[] dy = {-1, 1, 1, -1};

    static int totalCount = 0;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());  // 격자 크기
        M = Integer.parseInt(st.nextToken());  // 진행 년 수
        K = Integer.parseInt(st.nextToken());  // 제초제 확산 범위
        C = Integer.parseInt(st.nextToken());  // 제초제 유지 기간

        map = new int[N][N];
        herbicide = new int[N][N];

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());

            for (int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }


        // M년 동안 진행
        for (int year = 0; year < M; year++) {

            // 0. 기존 제초제 기간 감소
            decreaseHerbicide();

            // 1. 나무 성장
            grow();

            // 2. 나무 번식
            reproduce();

            // 3. 가장 많이 박멸할 수 있는 위치 찾기
            int[] target = findTarget();

            // 나무가 하나라도 있을 경우
            if (target[0] != -1) {
                // 4. 실제 제초제 살포
                spray(target[0], target[1], target[2]);
            }
        }

        System.out.println(totalCount);
    }


    // ========================================
    // 0. 제초제 기간 감소
    // ========================================
    static void decreaseHerbicide() {

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {

                if (herbicide[r][c] > 0) {
                    herbicide[r][c]--;
                }
            }
        }
    }


    // ========================================
    // 1. 나무 성장
    // 인접한 상하좌우 나무 개수만큼 성장
    // ========================================
    static void grow() {

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {

                // 나무가 아니면 pass
                if (map[r][c] <= 0) continue;

                int count = 0;

                for (int d = 0; d < 4; d++) {

                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    if (nr < 0 || nc < 0 || nr >= N || nc >= N) {
                        continue;
                    }

                    // 주변에 나무가 있으면
                    if (map[nr][nc] > 0) {
                        count++;
                    }
                }

                map[r][c] += count;
            }
        }
    }


    // ========================================
    // 2. 나무 번식
    // 동시에 번식해야 하므로 add 배열 사용
    // ========================================
    static void reproduce() {

        int[][] add = new int[N][N];

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {

                // 나무가 아니면 pass
                if (map[r][c] <= 0) continue;

                int possibleCount = 0;

                // 우선 번식 가능한 칸 개수 구하기
                for (int d = 0; d < 4; d++) {

                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    if (nr < 0 || nc < 0 || nr >= N || nc >= N) {
                        continue;
                    }

                    // 빈 칸이 아니면 번식 불가능
                    if (map[nr][nc] != 0) {
                        continue;
                    }

                    // 제초제가 있으면 번식 불가능
                    if (herbicide[nr][nc] > 0) {
                        continue;
                    }

                    possibleCount++;
                }


                // 번식 가능한 칸이 없으면
                if (possibleCount == 0) continue;

                int amount = map[r][c] / possibleCount;


                // 실제 번식량 기록
                for (int d = 0; d < 4; d++) {

                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    if (nr < 0 || nc < 0 || nr >= N || nc >= N) {
                        continue;
                    }

                    if (map[nr][nc] != 0) {
                        continue;
                    }

                    if (herbicide[nr][nc] > 0) {
                        continue;
                    }

                    add[nr][nc] += amount;
                }
            }
        }


        // 번식 결과 한 번에 적용
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {

                map[r][c] += add[r][c];
            }
        }
    }


    // ========================================
    // 3. 제초제를 뿌릴 위치 찾기
    //
    // return
    // [0] 행
    // [1] 열
    // [2] 박멸 가능한 나무 수
    // ========================================
    static int[] findTarget() {

        int maxCount = 0;
        int maxR = -1;
        int maxC = -1;

        // 행 → 열 순서로 탐색하므로
        // 같은 값일 때 갱신하지 않으면
        // 자동으로 행/열이 작은 위치 선택
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {

                // 나무가 있는 칸에서만 제초제 후보 계산
                if (map[r][c] <= 0) continue;

                // 자기 자신도 박멸되므로 포함
                int count = map[r][c];

                // 대각선 4방향
                for (int d = 0; d < 4; d++) {

                    for (int dist = 1; dist <= K; dist++) {

                        int nr = r + dx[d] * dist;
                        int nc = c + dy[d] * dist;

                        // 범위 밖
                        if (nr < 0 || nc < 0 || nr >= N || nc >= N) {
                            break;
                        }

                        // 벽이면 확산 종료
                        if (map[nr][nc] == -1) {
                            break;
                        }

                        // 빈칸이면 그 이후로 확산되지 않음
                        if (map[nr][nc] == 0) {
                            break;
                        }

                        // 나무가 있으면 박멸 수 추가
                        count += map[nr][nc];
                    }
                }


                // 더 많이 박멸할 수 있다면 갱신
                if (count > maxCount) {

                    maxCount = count;
                    maxR = r;
                    maxC = c;
                }
            }
        }

        return new int[]{maxR, maxC, maxCount};
    }


    // ========================================
    // 4. 실제 제초제 살포
    // ========================================
    static void spray(int r, int c, int count) {

        // 이번 년도 박멸 수
        totalCount += count;


        // 중심 위치
        map[r][c] = 0;

        // 매년 시작할 때 -- 하기 때문에
        // C + 1로 저장
        herbicide[r][c] = C + 1;


        // 대각선 4방향
        for (int d = 0; d < 4; d++) {

            for (int dist = 1; dist <= K; dist++) {

                int nr = r + dx[d] * dist;
                int nc = c + dy[d] * dist;

                // 범위 밖이면 종료
                if (nr < 0 || nc < 0 || nr >= N || nc >= N) {
                    break;
                }

                // 벽을 만나면 벽에는 뿌리지 않고 종료
                if (map[nr][nc] == -1) {
                    break;
                }


                // 현재 칸에는 제초제가 뿌려짐
                herbicide[nr][nc] = C + 1;


                // 빈칸을 만나면
                // 빈칸에는 제초제가 남지만
                // 그 뒤로는 더 퍼지지 않음
                if (map[nr][nc] == 0) {
                    break;
                }


                // 나무 박멸
                map[nr][nc] = 0;
            }
        }
    }
}