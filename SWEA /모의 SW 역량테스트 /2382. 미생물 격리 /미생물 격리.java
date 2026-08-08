import java.util.*;
import java.io.*;

class Micro {
    int r;
    int c;
    int count;
    int d;

    public Micro(int r, int c, int count, int d) {
        this.r = r;
        this.c = c;
        this.count = count;
        this.d = d;
    }
}

public class Solution {
    static int T;
    static int N, M, K;
    static List<Micro>[][] map;

//    static int[][] micro;
    static List<Micro> micros;

    static int[] dr = {0, -1, 1, 0, 0};
    static int[] dc = {0, 0, 0, -1, 1};


    public static void main(String[] args) throws Exception {
        // Please write your code here.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {

            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());  // 격리 시간
            K = Integer.parseInt(st.nextToken());  // 미생물 군집 개수

            micros = new ArrayList<>();

            for(int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());

                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int count = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());

                Micro micro = new Micro(r, c, count, d);
                micros.add(micro);
            }

            for (int time = 0; time < M; time++) {

                map = new ArrayList[N][N];

                for (int r = 0; r < N; r++) {
                    for (int c = 0; c < N; c++) {
                        map[r][c] = new ArrayList<>();
                    }
                }

                // 1. 기존 micros를 이용해서 이동
                for (Micro micro : micros) {

                    int nr = micro.r + dr[micro.d];
                    int nc = micro.c + dc[micro.d];

                    micro.r = nr;
                    micro.c = nc;

                    if (nr == 0 || nr == N - 1 ||
                            nc == 0 || nc == N - 1) {

                        micro.count /= 2;

                        if (micro.d == 1) micro.d = 2;
                        else if (micro.d == 2) micro.d = 1;
                        else if (micro.d == 3) micro.d = 4;
                        else micro.d = 3;
                    }

                    if (micro.count > 0) {
                        map[nr][nc].add(micro);
                    }
                }

                // 기존 micros는 이제 역할 끝
                // 새로운 턴의 군집들을 담을 리스트로 초기화
                micros = new ArrayList<>();

                // 2. map을 검사해서 다시 micros 구성
                for (int r = 0; r < N; r++) {
                    for (int c = 0; c < N; c++) {

                        if (map[r][c].isEmpty()) continue;

                        if (map[r][c].size() == 1) {
                            micros.add(map[r][c].get(0));
                        }

                        else {
                            int totalCount = 0;
                            int max = 0;
                            int md = 0;

                            for (Micro micro : map[r][c]) {
                                totalCount += micro.count;

                                if (micro.count > max) {
                                    max = micro.count;
                                    md = micro.d;
                                }
                            }

                            micros.add(new Micro(r, c, totalCount, md));
                        }
                    }
                }
            }

            int totalCount = 0;

            for (Micro micro : micros) {
                totalCount += micro.count;
            }

            System.out.println("#" + (t + 1) + " " + totalCount);
        }
        
    }

}
