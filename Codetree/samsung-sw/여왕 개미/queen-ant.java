import java.util.*;
import java.io.*;

class AntHome {
    int i;
    int x;
    boolean isBlocked;
    boolean isSafe;

    public AntHome(int i, int x) {
        this.i = i;
        this.x = x;
        isBlocked = false;
    }

    public AntHome(int i, int x, boolean isSafe) {
        this.i = i;
        this.x = x;
        isBlocked = false;
        this.isSafe = isSafe;
    }
}

public class Main {
    static int Q, N;
    
    static int idx;
    
    static List<AntHome> homes = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        // Please write your code here.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        Q = Integer.parseInt(st.nextToken());

        // 여왕집
        idx = 0;
        homes.add(new AntHome(idx++, 0));

        for (int q = 0; q < Q; q++) {
            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());

            switch(n) {
                case 100:
                    buildTown(st);
                    break;
                case 200:
                    buildHome(st);
                    break;
                case 300:
                    blockHome(st);
                    break;
                case 400:
                    lookAround(st);
                    break;
            }
        }
    }

    static void lookAround(StringTokenizer st) {
        // 정찰 나갈 개미의 수
        int r = Integer.parseInt(st.nextToken());

        List<Integer> alive = new ArrayList<>();

        // 철거되지 않은 집만
        for (AntHome home : homes) {
            if (!home.isBlocked) {
                alive.add(home.x);
            }
        }
        
        // 이분 탐색 + 그리디
        int left = 0;
        int right = 1_000_000_000;
        int answer = 0;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (possible(alive, r, mid)) {
                right = mid - 1;
                answer = mid;
            }

            else {
                left = mid + 1;
            }
        }

        System.out.println(answer);

    }

    
    static boolean possible(List<Integer> alive, int r, int time) {

        int antCount = 0;
        int start = 1;

        while (start < alive.size()) {

            // 새로운 개미 배치
            antCount++;

            if (antCount > r) {
                return false;
            }

            // 이 개미가 시작하는 가장 왼쪽 집
            int first = alive.get(start);

            int i = start;

            // time 안에 갈 수 있는 집을 최대한 포함
            while (i + 1 < alive.size()
                    && alive.get(i + 1) - first <= time) {

                i++;
            }

            // 다음 처리할 집
            start = i + 1;
        }

        return true;
    }


    static void blockHome(StringTokenizer st) {
        int q = Integer.parseInt(st.nextToken());

        if (q > homes.size() || homes.get(q).isBlocked) {
            return;
        }

        homes.get(q).isBlocked = true;
    }

    static void buildHome(StringTokenizer st) {
        int p = Integer.parseInt(st.nextToken());

        homes.add(new AntHome(idx++, p));
    }


    static void buildTown(StringTokenizer st) {

        N = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++, idx++) {
            int x = Integer.parseInt(st.nextToken());

            homes.add(new AntHome(idx, x));
        }
    }
}