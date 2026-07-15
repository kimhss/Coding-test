import java.util.*;
import java.io.*;

class Student {
    int num;
    List<Integer> likes = new ArrayList<>();

    public Student(int num) {
        this.num = num;
    }

    public void addLikes(int num) {
        likes.add(num);
    }
}

public class Main {
    static int n;
    static Student[][] map;
    static List<Student> students = new ArrayList<>();

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        // Please write your code here.
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        map = new Student[n][n];

        int size = n * n;

        for(int i = 0; i < size; i++) {
            Student student = new Student(sc.nextInt());
            for(int j = 0; j < 4; j++) {
                student.addLikes(sc.nextInt());
            }

            students.add(student);
        }

        for(Student student : students) {
            placeStudent(student);
        }

        calculateScore();

    }

    public static void placeStudent(Student student) {
        int bestRow = -1;  // 행
        int bestCol = -1;  // 열
        int bestLike = -1; // 좋아하는 학생 수
        int bestEmpty = -1;  // 빈칸 수

        // 모든 빈자리 탐색

        for(int r = 0; r < n; r++) {
            for(int c = 0; c < n; c++) {
                // 이미 학생이 앉아 있는 자리
                if (map[r][c] != null) continue;

                int likeCount = 0;
                int emptyCount = 0;

                // 자리 주변 조사
                for(int i = 0; i < 4; i++) {
                    int x = dx[i] + r;
                    int y = dy[i] + c;

                    if (x < 0 || y < 0 || x >= n || y >= n) continue;

                    if (map[x][y] == null) emptyCount++;
                    else if (isLiked(student, map[x][y].num)) {
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

    static boolean isLiked(Student student, int target) {
        for(int likes : student.likes) {
            if (likes == target) {
                return true;
            }
        }

        return false;
    }

    static void calculateScore() {
        int answer = 0;
        int[] score = {0, 1, 10, 100, 1000};

        // 모든 자리 만족도 계산
        for(int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                int likeCount = 0;

                for(int i = 0; i < 4; i++) {
                    int x = dx[i] + r;
                    int y = dy[i] + c;

                    if (x < 0 || y < 0 || x >= n || y >= n) continue;

                    if(isLiked(map[r][c], map[x][y].num)) likeCount++;
                }

                answer += score[likeCount];
            }
        }

        System.out.println(answer);
    }

}
