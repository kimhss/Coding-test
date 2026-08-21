import java.util.*;
import java.io.*;

public class Main {

    static int L, N, Q;

    static int[][] map;
    static int[][] member;
    static int[][] order;

    static int[] damage;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {

        BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        map = new int[L][L];

        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < L; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        member = new int[N + 1][5];
        damage = new int[N + 1];

        for (int i = 1; i <= N; i++) {

            st = new StringTokenizer(br.readLine());

            // 문제 좌표는 1-based
            member[i][0] = Integer.parseInt(st.nextToken()) - 1;
            member[i][1] = Integer.parseInt(st.nextToken()) - 1;

            member[i][2] = Integer.parseInt(st.nextToken());
            member[i][3] = Integer.parseInt(st.nextToken());
            member[i][4] = Integer.parseInt(st.nextToken());
        }

        order = new int[Q][2];

        for (int i = 0; i < Q; i++) {

            st = new StringTokenizer(br.readLine());

            order[i][0] = Integer.parseInt(st.nextToken());
            order[i][1] = Integer.parseInt(st.nextToken());
        }

        // 명령 실행
        for (int i = 0; i < Q; i++) {
            playOrder(i);
        }

        // 살아남은 기사들의 데미지만 합산
        int sum = 0;

        for (int i = 1; i <= N; i++) {

            if (member[i][4] > 0) {
                sum += damage[i];
            }
        }

        System.out.println(sum);
    }


    private static void playOrder(int idx) {

        int num = order[idx][0];
        int d = order[idx][1];

        // 이미 죽은 기사면 무시
        if (member[num][4] <= 0) {
            return;
        }

        // 같이 밀려야 하는 기사 찾기
        boolean[] pushed = findMembersToPush(num, d);

        // 한 명이라도 벽에 막혔다면 전체 이동 X
        if (pushed == null) {
            return;
        }

        // 모두 이동
        for (int i = 1; i <= N; i++) {

            if (!pushed[i]) continue;

            member[i][0] += dx[d];
            member[i][1] += dy[d];
        }

        // 밀려난 기사만 함정 데미지
        for (int i = 1; i <= N; i++) {

            if (!pushed[i]) continue;

            // 명령 받은 기사는 데미지를 받지 않음
            if (i == num) continue;

            takeDamage(i);
        }
    }


    private static void takeDamage(int num) {

        int r = member[num][0];
        int c = member[num][1];
        int h = member[num][2];
        int w = member[num][3];

        int trapCount = 0;

        for (int x = r; x < r + h; x++) {

            for (int y = c; y < c + w; y++) {

                if (map[x][y] == 1) {
                    trapCount++;
                }
            }
        }

        // 함정 수만큼 체력 감소
        member[num][4] -= trapCount;

        // 받은 데미지 기록
        damage[num] += trapCount;
    }


    private static boolean[] findMembersToPush(int start, int d) {

        boolean[] pushed = new boolean[N + 1];

        Queue<Integer> q = new ArrayDeque<>();

        pushed[start] = true;
        q.offer(start);

        while (!q.isEmpty()) {

            int now = q.poll();

            int r = member[now][0];
            int c = member[now][1];
            int h = member[now][2];
            int w = member[now][3];

            // now가 이동하게 될 위치
            int nr = r + dx[d];
            int nc = c + dy[d];

            // 이동 위치에 벽 또는 격자 밖
            if (!canMoveTo(nr, nc, h, w)) {
                return null;
            }

            // 이동했을 때 겹치는 다른 기사 검사
            for (int next = 1; next <= N; next++) {

                // 이미 같이 밀기로 한 기사
                if (pushed[next]) continue;

                // 죽은 기사
                if (member[next][4] <= 0) continue;

                if (overlap(now, next, nr, nc)) {

                    pushed[next] = true;
                    q.offer(next);
                }
            }
        }

        return pushed;
    }


    private static boolean canMoveTo(
            int r, int c, int h, int w) {

        for (int x = r; x < r + h; x++) {

            for (int y = c; y < c + w; y++) {

                // 격자 밖
                if (x < 0 || x >= L ||
                    y < 0 || y >= L) {

                    return false;
                }

                // 벽
                if (map[x][y] == 2) {
                    return false;
                }
            }
        }

        return true;
    }


    private static boolean overlap(
            int a,
            int b,
            int nr,
            int nc) {

        // a 기사가 이동한 위치
        int ar = nr;
        int ac = nc;
        int ah = member[a][2];
        int aw = member[a][3];

        // b 기사의 현재 위치
        int br = member[b][0];
        int bc = member[b][1];
        int bh = member[b][2];
        int bw = member[b][3];

        // a가 b보다 완전히 위
        if (ar + ah - 1 < br) return false;

        // b가 a보다 완전히 위
        if (br + bh - 1 < ar) return false;

        // a가 b보다 완전히 왼쪽
        if (ac + aw - 1 < bc) return false;

        // b가 a보다 완전히 왼쪽
        if (bc + bw - 1 < ac) return false;

        return true;
    }
}