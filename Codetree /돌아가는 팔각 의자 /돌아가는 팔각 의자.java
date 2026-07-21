import java.util.*;
import java.io.*;

class Main {
    static int[][] wheel;
    static int k;

    public static void main(String[] args) throws Exception {
        // Please write your code here.
        Scanner sc = new Scanner(System.in);

        wheel = new int[5][8];

        for(int i = 1; i < 5; i++) {
            String str = sc.next();
            for(int j = 0; j < 8; j++) {
                wheel[i][j] = str.charAt(j) - '0';
            }
        }

        k = sc.nextInt();

        for(int i = 0; i < k; i++) {
            int n = sc.nextInt();
            int d = sc.nextInt();

            rotateAll(n, d);
        }

        System.out.println(calculate());

    }

    public static void rotateAll(int n, int d) {
        int[] rotation = new int[5];
        rotation[n] = d;

        for(int i = n; i < 4; i++) {
            if (wheel[i][2] != wheel[i + 1][6]) {
                rotation[i + 1] = -rotation[i];
            }
            else break;
        }

        for(int i = n; i > 0; i--) {
            if (wheel[i][6] != wheel[i - 1][2]) {
                rotation[i - 1] = -rotation[i];
            }
            else break;
        }

        rotate(rotation);
    }

    public static void rotate(int[] rotation) {
        for(int i = 1; i < 5; i++) {
            // 시계방향 회전
            if (rotation[i] == 1) {
                int tmp = wheel[i][7];
                for(int j = 7; j > 0; j--) {
                    wheel[i][j] = wheel[i][j - 1];
                }
                wheel[i][0] = tmp;
            }

            // 반시계 방향 회전
            else if (rotation[i] == -1){
                int tmp = wheel[i][0];
                for(int j = 0; j < 7; j++) {
                    wheel[i][j] = wheel[i][j + 1];
                }
                wheel[i][7] = tmp;
            }
        }
    }

    public static int calculate() {
        int[] score = {0, 1, 2, 4, 8};
        int sum = 0;
        for(int i = 1; i < 5; i++) {
            sum += score[i] * wheel[i][0];
        }

        return sum;
    }

}
