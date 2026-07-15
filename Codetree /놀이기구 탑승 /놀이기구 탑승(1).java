import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int[][] map;
    static int[][] likes;
    static int[] order;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();

        int size = n * n;

        map = new int[n][n];
        likes = new int[size + 1][4];
        order = new int[size];

        for (int i = 0; i < size; i++) {
            int student = sc.nextInt();
            order[i] = student;

            for (int j = 0; j < 4; j++) {
                likes[student][j] = sc.nextInt();
            }
        }

        for (int student : order) {
            placeStudent(student);
        }

        System.out.println(calculateScore());
    }

    static void placeStudent(int student) {
        int bestRow = -1;
        int bestCol = -1;
        int bestLike = -1;
        int bestEmpty = -1;

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {

                // 이미 학생이 앉은 자리
                if (map[r][c] != 0) continue;

                int likeCount = 0;
                int emptyCount = 0;

                for (int d = 0; d < 4; d++) {
                    int nr = r + dx[d];
                    int nc = c + dy[d];

                    if (nr < 0 || nr >= n || nc < 0 || nc >= n) {
                        continue;
                    }

                    if (map[nr][nc] == 0) {
                        emptyCount++;
                    } else if (isLiked(student, map[nr][nc])) {
                        likeCount++;
                    }
                }

                if (likeCount > bestLike ||
                    (likeCount == bestLike && emptyCount > bestEmpty)) {

                    bestLike = likeCount;
                    bestEmpty = emptyCount;
                    bestRow = r;
                    bestCol = c;
                }
            }
        }

        map[bestRow][bestCol] = student;
    }

    static boolean isLiked(int student, int target) {
        for (int i = 0; i < 4; i++) {
            if (likes[student][i] == target) {
                return true;
            }
        }

        return false;
    }

    static int calculateScore() {
        int answer = 0;
        int[] score = {0, 1, 10, 100, 1000};

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                int student = map[r][c];
                int likeCount = 0;

                for (int d = 0; d < 4; d++) {
                    int nr = r + dx[d];
                    int nc = c + dy[d];

                    if (nr < 0 || nr >= n || nc < 0 || nc >= n) {
                        continue;
                    }

                    if (isLiked(student, map[nr][nc])) {
                        likeCount++;
                    }
                }

                answer += score[likeCount];
            }
        }

        return answer;
    }
}
