import java.util.*;
import java.io.*;

public class Main {
    static int T;
    static int M, A;

    static int[][] bc;
    static int[][] move;

    static int[] dx = {0, -1, 0, 1, 0};
    static int[] dy = {0, 0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        // Please write your code here.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {

            st = new StringTokenizer(br.readLine());

            M = Integer.parseInt(st.nextToken());  // 총 이동 시간
            A = Integer.parseInt(st.nextToken());  // BC의 개수

            // A와 B 이동 정보
            move = new int[2][M];

            // r, c, 충전 범위, 전력?
            bc = new int[A][4];

            for(int i = 0; i < 2; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < M; j++) {
                    move[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for(int i = 0; i < A; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < 4; j++) {
                    bc[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // A 시작 위치
            int ax = 1;
            int ay = 1;

            // B 시작 위치
            int bx = 10;
            int by = 10;

            int result = 0;

            // 이동하기 전 0초에도 충전
            result += charge (ax, ay, bx, by);

            for(int i = 0; i < M; i++) {
                ax += dx[move[0][i]];
                ay += dy[move[0][i]];

                bx += dx[move[1][i]];
                by += dy[move[1][i]];

                result += charge(ax, ay, bx, by);
            }

            System.out.println("#" + (t + 1) + " " + result);

        }
    }

    private static int charge(int ax, int ay, int bx, int by) {

        int max = 0;

        // A가 선택할 BC
        for(int i = 0; i < A; i++) {

            // A가 i번 BC를 사용할 수 있는지
            int aDist = Math.abs(ax - bc[i][0]) + Math.abs(ay - bc[i][1]);

            boolean aPossible = aDist <= bc[i][2];

            // B가 선택할 BC
            for(int j = 0; j < A; j++) {
                int bDist = Math.abs(bx - bc[j][0]) + Math.abs(by - bc[j][0]);

                boolean bPossible = bDist <= bc[j][2];

                int sum = 0;

                // A, B 둘 다 BC 사용 가능
                if (aPossible && bPossible) {

                    // 같은 BC를 선택
                    if (i == j) {
                        sum = bc[i][3];
                    }

                    // 서로 다른 BC를 선택
                    else {
                        sum = bc[i][3] + bc[j][3];
                    }
                }

                else if (aPossible) {
                    sum = bc[i][3];
                }

                else if (bPossible) {
                    sum = bc[j][3];
                }

                max = Math.max(max, sum);
            }
        }

        return max;
    }

}
