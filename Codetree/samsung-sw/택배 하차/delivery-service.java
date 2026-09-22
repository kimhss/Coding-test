import java.util.*;
import java.io.*;

class Box {
    int k;
    int h;
    int w;
    int r;
    int c;
    boolean isOut;

    public Box(int k, int h, int w, int c) {
        this.k = k;
        this.h = h;
        this.w = w;
        this.r = 0;
        this.c = c;
        this.isOut = false;
    }
}

public class Main {

    static int N, M;
    static int[][] map;
    static Box[] boxes;

    static List<Integer> outList = new ArrayList<>();

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
        );

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        boxes = new Box[101];

        for (int i = 0; i < M; i++) {

            st = new StringTokenizer(br.readLine());

            int k = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken()) - 1;

            boxes[k] = new Box(k, h, w, c);

            storeBox(k, h, w, c);
        }

        while (canOut()) {

            // 좌측 하차
            if (leftOut()) {
                down();
            }

            // 우측 하차
            if (rightOut()) {
                down();
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int n : outList) {
            sb.append(n).append("\n");
        }

        System.out.print(sb);
    }

    // 왼쪽 하차
    static boolean leftOut() {

        int minK = Integer.MAX_VALUE;

        for (int i = 1; i < boxes.length; i++) {

            Box box = boxes[i];

            if (box == null || box.isOut) continue;

            if (canLeftOut(i)) {
                minK = Math.min(minK, i);
            }
        }

        if (minK == Integer.MAX_VALUE) return false;

        removeBox(minK);

        return true;
    }

    // 오른쪽 하차
    static boolean rightOut() {

        int minK = Integer.MAX_VALUE;

        for (int i = 1; i < boxes.length; i++) {

            Box box = boxes[i];

            if (box == null || box.isOut) continue;

            if (canRightOut(i)) {
                minK = Math.min(minK, i);
            }
        }

        if (minK == Integer.MAX_VALUE) return false;

        removeBox(minK);

        return true;
    }

    // 박스 제거
    static void removeBox(int k) {

        Box box = boxes[k];

        for (int i = box.r; i < box.r + box.h; i++) {
            for (int j = box.c; j < box.c + box.w; j++) {
                map[i][j] = 0;
            }
        }

        box.isOut = true;

        outList.add(k);
    }

    // 중력 적용
    static void down() {

        List<Box> remain = new ArrayList<>();

        for (int i = 1; i < boxes.length; i++) {

            if (boxes[i] == null || boxes[i].isOut) continue;

            remain.add(boxes[i]);
        }

        // 아래쪽 박스부터 처리
        remain.sort((a, b) -> Integer.compare(b.r, a.r));

        for (Box box : remain) {

            int r = box.r;
            int h = box.h;
            int c = box.c;
            int w = box.w;

            // 기존 위치 제거
            for (int i = r; i < r + h; i++) {
                for (int j = c; j < c + w; j++) {
                    map[i][j] = 0;
                }
            }

            int resultR = r;

            // 한 칸씩 내려갈 수 있는지 확인
            while (resultR + h < N) {

                boolean isPossible = true;

                int nextR = resultR + h;

                for (int j = c; j < c + w; j++) {

                    if (map[nextR][j] > 0) {
                        isPossible = false;
                        break;
                    }
                }

                if (!isPossible) break;

                resultR++;
            }

            // 최종 위치에 기록
            for (int i = resultR; i < resultR + h; i++) {
                for (int j = c; j < c + w; j++) {
                    map[i][j] = box.k;
                }
            }

            // 박스 좌표 갱신
            box.r = resultR;
        }
    }

    static boolean canRightOut(int k) {

        Box box = boxes[k];

        int r = box.r;
        int h = box.h;
        int c = box.c;
        int w = box.w;

        for (int i = r; i < r + h; i++) {

            for (int j = c + w; j < N; j++) {

                if (map[i][j] > 0) return false;
            }
        }

        return true;
    }

    static boolean canLeftOut(int k) {

        Box box = boxes[k];

        int r = box.r;
        int h = box.h;
        int c = box.c;

        for (int i = r; i < r + h; i++) {

            for (int j = c - 1; j >= 0; j--) {

                if (map[i][j] > 0) return false;
            }
        }

        return true;
    }

    static boolean canOut() {

        for (int i = 1; i < boxes.length; i++) {

            if (boxes[i] == null) continue;

            if (!boxes[i].isOut) return true;
        }

        return false;
    }

    // 최초 택배 배치
    static void storeBox(int k, int h, int w, int c) {

        int resultR = N - h;

        for (int i = 0; i < N; i++) {

            boolean collision = false;

            for (int j = c; j < c + w; j++) {

                if (map[i][j] > 0) {
                    collision = true;
                    break;
                }
            }

            if (collision) {
                resultR = i - h;
                break;
            }
        }

        for (int i = resultR; i < resultR + h; i++) {

            for (int j = c; j < c + w; j++) {
                map[i][j] = k;
            }
        }

        boxes[k].r = resultR;
    }
}