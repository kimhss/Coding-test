import java.util.*;
import java.io.*;

public class Main {

    static int N, M, K;

    static int[][] map;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, 1, -1};

    static int[][] member;

    static int resultR, resultC;

    static int sum = 0;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        member = new int[M][2];

        for (int i = 0; i < N; i++) {

            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < M; i++) {

            st = new StringTokenizer(br.readLine());

            member[i][0] = Integer.parseInt(st.nextToken()) - 1;
            member[i][1] = Integer.parseInt(st.nextToken()) - 1;
        }

        st = new StringTokenizer(br.readLine());

        resultR = Integer.parseInt(st.nextToken()) - 1;
        resultC = Integer.parseInt(st.nextToken()) - 1;


        // K초 동안 반복
        for (int time = 0; time < K; time++) {

            // 참가자 이동
            moveMember();

            // 전부 탈출했으면 종료
            if (allEscaped()) {
                break;
            }

            // 미로 회전
            rotateMaze();
        }

        System.out.println(sum);
        System.out.println((resultR + 1) + " " + (resultC + 1));
    }


    private static boolean allEscaped() {

        for (int i = 0; i < M; i++) {

            if (member[i][0] != -1) {
                return false;
            }
        }

        return true;
    }


    private static void rotateMaze() {

        // 작은 정사각형부터
        for (int size = 2; size <= N; size++) {

            // 좌상단 행이 작은 것부터
            for (int r = 0; r + size <= N; r++) {

                // 좌상단 열이 작은 것부터
                for (int c = 0; c + size <= N; c++) {

                    // 출구가 정사각형 안에 없으면 제외
                    if (!(resultR >= r && resultR < r + size
                            && resultC >= c && resultC < c + size)) {
                        continue;
                    }

                    // 참가자가 한 명이라도 있는지
                    boolean hasMember = false;

                    for (int i = 0; i < M; i++) {

                        int mr = member[i][0];
                        int mc = member[i][1];

                        // 이미 탈출한 참가자
                        if (mr == -1) {
                            continue;
                        }

                        if (mr >= r && mr < r + size
                                && mc >= c && mc < c + size) {

                            hasMember = true;
                            break;
                        }
                    }

                    // 가장 먼저 발견한 정사각형이
                    // 문제 조건에 맞는 정사각형
                    if (hasMember) {

                        rotate90(r, c, size);
                        return;
                    }
                }
            }
        }
    }


    private static void rotate90(int r, int c, int size) {

        int[][] copyMap = new int[size][size];

        // 미로 회전
        for (int i = 0; i < size; i++) {

            for (int j = 0; j < size; j++) {

                int value = map[r + i][c + j];

                // 회전되는 벽의 내구도 감소
                if (value > 0) {
                    value--;
                }

                // 시계 방향 90도 회전
                copyMap[j][size - 1 - i] = value;
            }
        }

        // 원본 map에 다시 복사
        for (int i = 0; i < size; i++) {

            for (int j = 0; j < size; j++) {

                map[r + i][c + j] = copyMap[i][j];
            }
        }


        // 정사각형 안 참가자 회전
        for (int i = 0; i < M; i++) {

            int mr = member[i][0];
            int mc = member[i][1];

            // 이미 탈출
            if (mr == -1) {
                continue;
            }

            if (mr >= r && mr < r + size
                    && mc >= c && mc < c + size) {

                int newR = r + (mc - c);
                int newC = c + (size - 1 - (mr - r));

                member[i][0] = newR;
                member[i][1] = newC;
            }
        }


        // 출구 회전
        int oldR = resultR;
        int oldC = resultC;

        resultR = r + (oldC - c);
        resultC = c + (size - 1 - (oldR - r));
    }


    private static void moveMember() {

        for (int i = 0; i < M; i++) {

            int r = member[i][0];
            int c = member[i][1];

            // 이미 탈출
            if (r == -1) {
                continue;
            }

            // 현재 출구까지 거리
            int dist =
                    Math.abs(r - resultR)
                    + Math.abs(c - resultC);

            // 상 → 하 → 우 → 좌
            for (int j = 0; j < 4; j++) {

                int nr = r + dr[j];
                int nc = c + dc[j];

                // 범위 밖
                if (nr < 0 || nc < 0 || nr >= N || nc >= N) {
                    continue;
                }

                // 벽
                if (map[nr][nc] != 0) {
                    continue;
                }

                // 이동했을 때 출구까지 거리
                int nextDist =
                        Math.abs(nr - resultR)
                        + Math.abs(nc - resultC);

                // 거리가 줄어드는 경우에만 이동
                if (nextDist >= dist) {
                    continue;
                }

                // 한 칸 이동
                sum++;

                // 출구 도착
                if (nr == resultR && nc == resultC) {

                    member[i][0] = -1;
                    member[i][1] = -1;

                } else {

                    member[i][0] = nr;
                    member[i][1] = nc;
                }

                break;
            }
        }
    }
}