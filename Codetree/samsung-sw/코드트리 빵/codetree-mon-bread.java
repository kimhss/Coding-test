import java.util.*;
import java.io.*;

class Person {
    int x;
    int y;
    boolean started;
    boolean arrived;

    public Person() {
        x = -1;
        y = -1;
        started = false;
        arrived = false;
    }
}

public class Main {
    static int N, M;
    static int[][] map;

    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};

    static int[][] conbini;
    static Person[] people;

    static boolean[][] blocked;

    public static void main(String[] args) throws Exception {
        // Please write your code here.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        conbini = new int[M][2];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            conbini[i][0] = Integer.parseInt(st.nextToken()) - 1;
            conbini[i][1] = Integer.parseInt(st.nextToken()) - 1;
        }

        people = new Person[M];

        for (int i = 0; i < M; i++) {
            people[i] = new Person();
        }

        blocked = new boolean[N][N];

        int time = 0;
        int arrivedCount = 0;

        while(arrivedCount < M) {

            time++;

            // 1. 격자 안에 있는 사람 편의점으로 한 칸 이동
            for (int i = 0; i < M; i++) {
                if (!people[i].started) continue;
                if (people[i].arrived) continue;

                toConbini(i);
            }

            // 2. 편의점 도착 처리
            for (int i = 0; i < M; i++) {
                if (!people[i].started) continue;
                if (people[i].arrived) continue;

                int x = people[i].x;
                int y = people[i].y;

                int cx = conbini[i][0];
                int cy = conbini[i][1];

                if (x == cx && y == cy) {
                    people[i].arrived = true;

                    blocked[x][y] = true;

                    arrivedCount++;
                }
            }
            
            
            // 3. t == time인 t번째 사람 베이스 캠프로 이동
            if (time <= M) {
                toBaseCamp(time - 1);
            }
        }

        System.out.println(time);
    }

    static int[][] getDist(int targetX, int targetY) {
        int[][] dist = new int[N][N];

        for (int i = 0; i < N; i++) {
            Arrays.fill(dist[i], -1);
        }

        Queue<int[]> q = new LinkedList<>();

        q.add(new int[] {targetX, targetY});

        dist[targetX][targetY] = 0;

        while (!q.isEmpty()) {

            int[] now = q.poll();

            int x = now[0];
            int y = now[1];

            for (int d = 0; d < 4; d++) {

                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx < 0 || ny < 0 ||nx >= N || ny >= N) continue;

                if (blocked[nx][ny]) continue;

                if (dist[nx][ny] != -1) continue;

                dist[nx][ny] = dist[x][y] + 1;

                q.add(new int[] {nx, ny});
            }
        }

        return dist;
    }

    static void toConbini(int p) {
        // p번째 사람은 p번째 편의점에 간다
        int x = people[p].x;
        int y = people[p].y;

        int cx = conbini[p][0];
        int cy = conbini[p][1];

        int minDist = Integer.MAX_VALUE;

        int[][] dist = getDist(cx, cy);

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;

            if (blocked[nx][ny]) continue;

            if (dist[nx][ny] == -1) continue;

            if (dist[nx][ny] < minDist) {
                minDist = dist[nx][ny];

                people[p].x = nx;
                people[p].y = ny;
            }
        }
    }

    static void toBaseCamp(int t) {
        // t번째 편의점이랑 가까운 베이스캠프 선택
        int cx = conbini[t][0];
        int cy = conbini[t][1];

        int[][] dist = getDist(cx, cy);

        int minDist = Integer.MAX_VALUE;

        int bx = -1;
        int by = -1;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // 베이스 캠프가 아닌 경우
                if (map[i][j] != 1) continue;

                if (blocked[i][j]) continue;

                if (dist[i][j] == -1) continue;

                if (dist[i][j] < minDist) {
                    minDist = dist[i][j];

                    bx = i;
                    by = j;
                }
            }
        }

        people[t].x = bx;
        people[t].y = by;

        people[t].started = true;

        blocked[bx][by] = true;
    }
}