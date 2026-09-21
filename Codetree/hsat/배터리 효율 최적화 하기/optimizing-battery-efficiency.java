import java.util.*;

public class Main {
    
    static List<List<int[]>> modules = new ArrayList<>();
    static Set<String> moduleSet = new HashSet<>();

    static List<int[]> selected;
    static boolean[][] visited;

    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {-1, 0, 1, 0};

    static int N, M;
    static int[][] board;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                board[i][j] = sc.nextInt();
            }
        }
        // Please write your code here.

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                selected = new ArrayList<>();
                visited = new boolean[N][M];

                selected.add(new int[] {i, j});
                visited[i][j] = true;
                select(1);
            }
        }

        int max = Integer.MIN_VALUE;

        for (int i = 0; i < modules.size(); i++) {
            for (int j = i + 1; j < modules.size(); j++) {
                boolean possible = getOverlap(modules.get(i), modules.get(j));

                if (possible) {
                    int calc = calculation(modules.get(i), modules.get(j));

                    max = Math.max(max, calc);
                }
            }
        }

        System.out.println(max);
    }

    static int calculation(List<int[]> a, List<int[]> b) {
        int sum = 0;

        for (int[] arr : a) {
            int ar = arr[0];
            int ac = arr[1];

            sum += board[ar][ac];
        }

        for (int[] brr : b) {
            int br = brr[0];
            int bc = brr[1];

            sum += board[br][bc];
        }

        return sum;
    }

    static boolean getOverlap(List<int[]> a, List<int[]> b) {
        int count = 0;

        for (int[] arr : a) {
            int ar = arr[0];
            int ac = arr[1];

            for (int[] brr : b) {
                int br = brr[0];
                int bc = brr[1];

                if (ar == br && ac == bc) count++;
            }
        }

        return count == 2;
    }

    static void select(int count) {

        if (count == 5) {

            String key = makeKey();

            // 키가 없으면 저장
            if (moduleSet.add(key)) {
                modules.add(new ArrayList<>(selected));
            }
            
            return;
        }

        int size = selected.size();

        for (int i = 0; i < size; i++) {

            int[] now = selected.get(i);
            
            for (int j = 0; j < 4; j++) {
                int nx = now[0] + dr[j];
                int ny = now[1] + dc[j];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if (visited[nx][ny]) continue;
                
                // select
                selected.add(new int[] {nx, ny});
                visited[nx][ny] = true;
                select(count + 1);

                // unselect
                selected.remove(selected.size() - 1);
                visited[nx][ny] = false;
            }
        }
    }

    static String makeKey() {
        List<int[]> copy = new ArrayList<>(selected);

        Collections.sort(copy, (a, b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];
            }

            return a[0] - b[0];
        });

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 5; i++) {
            int[] now = copy.get(i);

            sb.append(now[0]);
            sb.append(",");
            sb.append(now[1]);
            sb.append("\n");
        }

        return sb.toString();
    }
}