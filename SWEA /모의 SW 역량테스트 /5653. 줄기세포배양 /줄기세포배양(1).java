import java.util.*;
import java.io.*;

class Cell {
    int x;
    int y;
    int life;  // 생명력 X
    int createTime;  // 생성된 시간

    public Cell (int x, int y, int life, int createTime) {
        this.x = x;
        this.y = y;
        this.life = life;
        this.createTime = createTime;
    }
}

class Solution {
    static int N, M, K;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    static List<Cell> cells;
    static Set<String> occupied;

    public static void main(String args[]) throws Exception {

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++) {

            N = sc.nextInt();  // 세로 크기
            M = sc.nextInt();  // 가로 크기
            K = sc.nextInt();  // 배양 시간

            cells = new ArrayList<>();
            occupied = new HashSet<>();

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {

                    int life = sc.nextInt();

                    if (life == 0) continue;

                    Cell cell = new Cell(i, j, life, 0);

                    cells.add(cell);
                    occupied.add(i + "," + j);
                }
            }

            for (int t = 0; t < K; t++) {
                Map<String, Cell> newCells = new HashMap<>();

                for (int i = 0; i < cells.size(); i++) {
                    Cell cell = cells.get(i);
                    
                    if (canBreed(cell, t)) {
                        for (int j = 0; j < 4; j++) {
                            int nx = cell.x + dx[j];
                            int ny = cell.y + dy[j];

                            String key = nx + "," + ny;

                            // 이미 세포 배양 됐다면 pass
                            // if (!isCell(nx, ny)) continue;
                            if (occupied.contains(key)) continue;

                            // 중복된거 처리를 어떻게 해야될지 모르겠음...
                            if (!newCells.containsKey(key)
                                    || newCells.get(key).life < cell.life) {
                                newCells.put(key, new Cell(nx, ny, cell.life, t + 1)
                                );
                            }
                        }
                    }
                }

                cells.addAll(newCells.values());
                occupied.addAll(newCells.keySet());
            }

            int answer = 0;
            for (int i = 0; i < cells.size(); i++) {
                if (isActive(cells.get(i), K)) answer++;
            }

            System.out.println("#" + test_case + " " + answer);

        }
    }

    private static boolean canBreed(Cell cell, int time) {
        int age = time - cell.createTime;

        return age == cell.life;
    }

    private static boolean isActive(Cell cell, int time) {
        int age = time - cell.createTime;

        if (age < cell.life * 2) {
            // 활성화 & 비활성화
            return true;
        }

        else {
            // 죽음
            return false;
        }
    }
}
