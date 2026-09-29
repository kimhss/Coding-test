import java.util.*;
import java.io.*;

class Person {
    int x;
    int y;
    int d;
    int s;
    int gun;

    public Person (int x, int y, int d, int s) {
        this.x = x;
        this.y = y;
        this.d = d;
        this.s = s;
        this.gun = 0;
    }
}

public class Main {
    static int N, M, K;

    static PriorityQueue<Integer>[][] guns;

    static int[][] player;

    static Person[] people;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    static int[] point;

    public static void main(String[] args) throws Exception {
        // Please write your code here.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());  // 격자 크기
        M = Integer.parseInt(st.nextToken());  // 플레이어 수
        K = Integer.parseInt(st.nextToken());  // 라운드 수

        guns = new PriorityQueue[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                guns[i][j] = new PriorityQueue<>(Collections.reverseOrder());
            }
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int gun = Integer.parseInt(st.nextToken());

                if (gun > 0) {
                    guns[i][j].add(gun);
                }
            }
        }

        people = new Person[M];
        player = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(player[i], -1);
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken()); // 방향
            int s = Integer.parseInt(st.nextToken()); // 초기 능력치

            people[i] = new Person(x, y, d, s);

            player[x][y] = i;
        }

        point = new int[M];

        // ============= 세팅 ==================

        for (int round = 0; round < K; round++) {
            
            for (int p = 0; p < M; p++) {
                // 1. 이동
                move(p);

                Person now = people[p];

                int x = now.x;
                int y = now.y;

                // 2-1. 이동한 칸에 사람이 없디
                if (player[x][y] == -1) {

                    // 플레이어 배치
                    player[x][y] = p;

                    // 총 획득
                    getGun(p);
                }

                // 이동한 칸에 사람이 있다
                else {
                    int other = player[x][y];

                    fight(p, other);
                }
            }
        }

        for (int i = 0; i < M; i++) {
            System.out.print(point[i] + " ");
        }
    }


    static void fight(int a, int b) {
        Person p1 = people[a];
        Person p2 = people[b];

        int x = p1.x;
        int y = p1.y;

        int power1 = p1.s + p1.gun;
        int power2 = p2.s + p2.gun;

        int winIdx;
        int loseIdx;

        if (power1 > power2) {

            winIdx = a;
            loseIdx = b;

        } else if (power1 < power2) {

            winIdx = b;
            loseIdx = a;

        } else {

            // 공격력 합이 같으면
            // 초기 능력치가 높은 사람이 승리

            if (p1.s > p2.s) {
                winIdx = a;
                loseIdx = b;
            } else {
                winIdx = b;
                loseIdx = a;
            }
        }

        // 승자 점수 획득

        int diff = Math.abs(power1 - power2);
        point[winIdx] += diff;

        // 패자 처리
        Person loser = people[loseIdx];

        // 패자가 들고 있던 총 내려놓기
        if (loser.gun > 0) {
            guns[x][y].add(loser.gun);
            loser.gun = 0;
        }

        player[x][y] = winIdx;

        // 패자 이동
        moveLoser(loseIdx);

        // 승자 총 획득
        getGun(winIdx);
        
        
    }

    static void moveLoser(int idx) {
        Person p = people[idx];

        int x = p.x;
        int y = p.y;

        for (int i = 0; i < 4; i++) {

            int nd = (p.d + i) % 4;

            int nx = x + dx[nd];
            int ny = y + dy[nd];

            // 격자 밖
            if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
                continue;
            }

            // 다른 플레이어 있음
            if (player[nx][ny] != -1) {
                continue;
            }

            // 이동 가능

            p.d = nd;
            p.x = nx;
            p.y = ny;

            player[nx][ny] = idx;

            // 이동한 위치에서 총 획득
            getGun(idx);

            break;
        }

    }

    static void getGun(int idx) {
        Person p = people[idx];

        PriorityQueue<Integer> pq = guns[p.x][p.y];

        if (pq.isEmpty()) return;

        int bestGun = pq.peek();

        if (bestGun > p.gun) {
            pq.poll();

            if (p.gun > 0) {
                pq.add(p.gun);
            }

            p.gun = bestGun;
        }
    }

    static void move(int i) {
        Person p = people[i];

        // 기존 위치에서 플레이어 제거
        player[p.x][p.y] = -1;

        int nx = p.x + dx[p.d];
        int ny = p.y + dy[p.d];

        // 격자 밖을 벗어나면 반대 방향으로 이동
        if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
            int d = (p.d + 2) % 4;
            
            nx = p.x + dx[d];
            ny = p.y + dy[d];

            p.d = d;
        }

        p.x = nx;
        p.y = ny;
    }
}