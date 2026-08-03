import java.util.*;
import java.io.*;

class Solution {
    static int T;
    static int N, M, C;
    static int[][] map;
    static int[][] profits;

    static int MAX;

    public static void main(String[] args) throws Exception {
        // Please write your code here.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());

        for(int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());  // 별통 크기
            M = Integer.parseInt(st.nextToken());  // 선택 별통 개수
            C = Integer.parseInt(st.nextToken());  // 채취할 수 있는 꿀의 최대 양

            map = new int[N][N];

            for(int r = 0; r < N; r++) {
                st = new StringTokenizer(br.readLine());
                for(int c = 0; c < N; c++) {
                    map[r][c] = Integer.parseInt(st.nextToken());
                }
            }

            profits = new int[N][N - M + 1];

            for (int r = 0; r < N; r++) {
                for (int c = 0; c <= N - M; c++) {
                    profits[r][c] = getMaxProfit(r, c);

                }
            }

            selectTwoProfit();
            
            System.out.println("#" + (i + 1) + " " + MAX);

        }


    }

    private static void selectTwoProfit() {
        MAX = 0;
        
        for(int r1 = 0; r1 < N; r1++) {
            for(int c1 = 0; c1 <= N - M; c1++) {

                for(int r2 = r1; r2 < N; r2++) {
                    for(int c2 = 0; c2 <= N - M; c2++) {

                        // 겹치면
                        if (r1 == r2 && c1 + M > c2)
                            continue;

                        int totalProfit =
                                profits[r1][c1] + profits[r2][c2];

                        MAX = Math.max(MAX, totalProfit);
                    }
                }
            }
        }
    }

    private static int getMaxProfit(int r, int c) {
        return subset(r, c, 0, 0, 0);
    }

    private static int subset(int r, int c, int idx, int sum, int profit) {
        
        if (sum > C) return 0; // C보다 크면 채취 불가능

        if (idx == M) return profit;

        int honey = map[r][c +  idx];

        // 선택
        int select = subset(
                r,
                c,
                idx + 1,
                sum + honey,
                profit + honey * honey
        );

        int notSelect = subset(
                r,
                c,
                idx + 1,
                sum,
                profit
        );

        return Math.max(select, notSelect);

    }


}
