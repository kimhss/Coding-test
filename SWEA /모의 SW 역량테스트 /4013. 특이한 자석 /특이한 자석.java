import java.util.Scanner;
import java.io.FileInputStream;
 
class Solution
{
    static int T;
    static int[][] wheel;
     
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();
 
        for(int i = 0; i < T; i++) {
            int K = sc.nextInt();
            wheel = new int[4][8];
 
            for(int j = 0; j < 4; j++) {
                for(int k = 0; k < 8; k++) {
                    wheel[j][k] = sc.nextInt();
                }
            }
 
            for(int l = 0; l < K; l++) {
                // 자석 번호, 회전 방향 순
                int n = sc.nextInt();
                int d = sc.nextInt();
 
                // 회전
                rotateAll(n - 1, d);
 
            }
 
            System.out.println("#" + (i + 1) + " " + calc());
        }
    }
 
    public static void rotateAll(int n, int d) {
        // 회전 전 돌아야 하는 바퀴 방향 정하기
        int[] rotation = new int[4];
        rotation[n] = d;
 
        // 양옆 다르면 회전 방향 정하기
        // 오른쪽 전파
        for(int i = n; i < 3; i++) {
            if (wheel[i][2] != wheel[i + 1][6]) {
                rotation[i + 1] = -rotation[i];
            }
            else break;
        }
 
        // 왼쪽 전파
        for(int i = n; i > 0; i--) {
            if (wheel[i][6] != wheel[i - 1][2]) {
                rotation[i - 1] = -rotation[i];
            }
            else break;
        }
 
        rotate(rotation);
    }
 
    public static void rotate(int[] rotation) {
        for (int i = 0; i < 4; i++) {
            if (rotation[i] == 0) {
                continue;
            }
 
            if (rotation[i] == 1) {
                // 시계 방향
                int tmp = wheel[i][7];
 
                for (int j = 7; j > 0; j--) {
                    wheel[i][j] = wheel[i][j - 1];
                }
 
                wheel[i][0] = tmp;
            } else {
                // 반시계 방향
                int tmp = wheel[i][0];
 
                for (int j = 0; j < 7; j++) {
                    wheel[i][j] = wheel[i][j + 1];
                }
 
                wheel[i][7] = tmp;
            }
        }
    }
 
    public static int calc() {
        int sum = 0;
        for(int i = 0; i < 4; i++) {
            if (wheel[i][0] != 0) {
               sum += 1 << i;
            }
        }
         
        return sum;
    }
}
