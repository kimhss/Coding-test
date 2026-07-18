import java.util.*;
import java.io.*;

class Solution
{
    static int T; // 테스트 케이스 개수
    static int N;
    static int[] opr; // + - * / 순
    static int[] num;
 
    static int max;
    static int min;
 
    public static void main(String[] args) throws Exception {
        // Please write your code here.
        Scanner sc = new Scanner(System.in);
 
        T = sc.nextInt();
        for(int i = 0; i < T; i++) {
            max = Integer.MIN_VALUE;
            min = Integer.MAX_VALUE;
 
            N = sc.nextInt();
 
            opr = new int[4];
            for(int j = 0; j < 4; j++) {
                opr[j] = sc.nextInt();
            }
 
            num = new int[N];
            for(int j = 0; j < N; j++) {
                num[j] = sc.nextInt();
            }
 
            DFS(1, num[0]);
 
            int result = max - min;
 
            System.out.println("#" + (i + 1) + " " + result);
        }
    }
 
    public static void DFS(int depth, int sum) {
        if (depth == N) {
            if (sum > max) max = sum;
            if (sum < min) min = sum;
            return;
        }
 
        for(int j = 0; j < 4; j++) {
            if (opr[j] == 0) continue;
 
            opr[j]--;
            DFS(depth + 1, calculate(sum, num[depth], j));
            opr[j]++;
        }
    }
 
    public static int calculate(int a, int b, int idx) {
        switch (idx) {
            case 0:
                return a + b;
            case 1:
                return a - b;
            case 2:
                return a * b;
            case 3:
                return a / b;
            default:
                throw new IllegalArgumentException("잘못된 연산자 인덱스");
        }
    }
}
