import java.util.*;
import java.io.*;

class Attacker {
    int r;
    int c;
    int hp;
    int time;

    public Attacker(int r, int c, int hp) {
        this.r = r;
        this.c = c;
        this.hp = hp;
        time = 0;
    }
}

public class Main {
    static int N, M, K;

    static Attacker[][] map;

    static int[] dr = {0, 1, 0, -1, -1, -1, 1, 1};
    static int[] dc = {-1, 0, 1, 0, -1, 1, -1, 1};

    // 레이저 전용
    static int[] laserDr = {0, 1, 0, -1};
    static int[] laserDc = {1, 0, -1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new Attacker[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < M; j++) {
                int hp = Integer.parseInt(st.nextToken());

                map[i][j] = new Attacker(i, j, hp);
            }
        }

        // Please write your code here.
        for (int time = 1; time <= K; time++) {
            List<Attacker> attackers = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {

                    if (map[i][j].hp <= 0) continue;

                    attackers.add(map[i][j]);
                }
            }

            // 포탑 하나만 남으면 종료
            if (attackers.size() <= 1) break;

            // 1. 공격자 선정
            int[] attacker = selectAttacker(attackers, time);

            // 2. 공격 대상 선정
            int[] victim = selectVictim(attackers, attacker);

            // 3. 공격
            attack(attacker, victim);
        }

        int max = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                max = Math.max(map[i][j].hp, max);
            }
        }

        System.out.println(max);
    }

    static int[] selectAttacker(List<Attacker> attackers, int time) {
        PriorityQueue<Attacker> pq = new PriorityQueue<>((a, b) -> {
            if (a.hp == b.hp) {
                if (a.time == b.time) {
                    if ((a.r + a.c) == (b.r + b.c)) {
                        return b.c - a.c;
                    }
                    return (b.r + b.c) - (a.r + a.c); 
                }
                return b.time - a.time;
            }
            return a.hp - b.hp;
        });

        pq.addAll(attackers);

        Attacker attacker = pq.poll();

        int ar = attacker.r;
        int ac = attacker.c;

        map[ar][ac].hp += N + M;
        map[ar][ac].time = time;

        return new int[] {ar, ac};
    }

    static int[] selectVictim(List<Attacker> attackers, int[] attacker) {
        PriorityQueue<Attacker> pq = new PriorityQueue<>((a, b) -> {
            if (a.hp == b.hp) {
                if (a.time == b.time) {
                        if ((a.r + a.c) == (b.r + b.c)) {
                            return a.c - b.c;
                        }

                        return (a.r + a.c) - (b.r + b.c);
                    }

                    return a.time - b.time;
                }
                
                return b.hp - a.hp;
            }
        );

        for (Attacker now : attackers) {
            // 공격자는 공격 대상이 될 수 없음
            if (now.r == attacker[0]
            & now.c == attacker[1]) continue;

            pq.add(now);
        }

        Attacker victim = pq.poll();

        return new int[] {victim.r, victim.c};
    }


    static void attack(int[] attacker, int[] victim) {

        // 이번 공격에 관여한 포탑
        boolean[][] attacked = new boolean[N][M];

        int ar = attacker[0];
        int ac = attacker[1];

        int vr = victim[0];
        int vc = victim[1];

        attacked[ar][ac] = true;

        // 1. 레이저 공격 가능한지
        List<int[]> roadMap = canRazor(attacker, victim);


        if (roadMap != null) {

            // 공격 대상
            map[vr][vc].hp -= map[ar][ac].hp;
            attacked[vr][vc] = true;
            

            for(int[] list : roadMap) {
                int r = list[0];
                int c = list[1];

                // 공격자랑 위치 같으면 pass
                if (r == ar && c == ac) {
                    continue;
                }

                // 공격 대상 제외
                if (r == vr && c == vc) {
                    continue;
                }

                map[r][c].hp -= map[ar][ac].hp / 2;

                attacked[r][c] = true;
            
            }
        }

        // 2. 레이저 공격 안 되면 포탄 공격
        else {
            bomb(attacker, victim, attacked);

        }

        // 3. 포탑 재정비
        repair(attacked);
    }

    static void repair(boolean[][] attacked) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {

                if (map[i][j].hp <= 0) continue;
                
                if (attacked[i][j]) continue;

                map[i][j].hp += 1;
            }
        }
    }

    static void bomb(int[] attacker, int[] victim, boolean[][] attacked) {
        int vr = victim[0];
        int vc = victim[1];

        int ar = attacker[0];
        int ac = attacker[1];

        map[vr][vc].hp -= map[ar][ac].hp;

        attacked[vr][vc] = true;

        for (int i = 0; i < 8; i++) {
            int nr = ((vr + dr[i]) % N + N) % N;
            int nc = ((vc + dc[i]) % M + M) % M;

            // 공격자는 피해 받지 않음
            if (nr == ar && nc == ac) continue;

            // 이미 부서진 포탑
            if (map[nr][nc].hp <= 0) continue;

            map[nr][nc].hp -= map[ar][ac].hp / 2;

            attacked[nr][nc] = true;
        }
    }

    static List<int[]> canRazor(int[] attacker, int[] victim) {
        int ar = attacker[0];
        int ac = attacker[1];

        int targetR = victim[0];
        int targetC = victim[1];

        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];

        // 이전 위치 저장
        int[][] parentR = new int[N][M];
        int[][] parentC = new int[N][M];

        for (int i = 0; i < N; i++) {
            Arrays.fill(parentR[i], -1);
            Arrays.fill(parentC[i], -1);
        }

        q.add(new int[] {ar, ac});
        visited[ar][ac] = true;

        while(!q.isEmpty()) {
            int[] now = q.poll();

            int r = now[0];
            int c = now[1];
            //roadMap.add(new int[] {now[0], now[1]});

            // 목적지 도착
            if (r == targetR && c == targetC) {
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nx = (r + laserDr[i] + N) % N;
                int ny = (c + laserDc[i] + M) % M;

                if (visited[nx][ny]) continue;
                if (map[nx][ny].hp <= 0) continue; 

                q.add(new int[] {nx, ny});
                visited[nx][ny] = true;
                parentR[nx][ny] = r;
                parentC[nx][ny] = c;
            }
        }

        // 피해자까지 못 감
        if (!visited[targetR][targetC]) return null;

        // 경로 복원
        List<int[]> roadMap = new ArrayList<>();

        int r = targetR;
        int c = targetC;

        while(true) {
            roadMap.add(new int[] {r, c});

            // 공격자 위치에 도달
            if (r == ar && c == ac) break;

            int pr = parentR[r][c];
            int pc = parentC[r][c];

            r = pr;
            c = pc;

        }

        return roadMap;
        
    }
}