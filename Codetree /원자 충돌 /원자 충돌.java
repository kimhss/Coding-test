import java.util.*;

class Element {
    int x;
    int y;
    int m;
    int s;
    int d;

    public Element(int x, int y, int m, int s, int d) {
        this.x = x;
        this.y = y;
        this.m = m;
        this.s = s;
        this.d = d;
    }

    public void update(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static int N, M, K;
    static List<Element>[][] map;

    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        K = sc.nextInt();

        List<Element> elements = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            int x = sc.nextInt() - 1;
            int y = sc.nextInt() - 1;
            int m = sc.nextInt();
            int s = sc.nextInt();
            int d = sc.nextInt();

            elements.add(new Element(x, y, m, s, d));
        }

        for (int time = 0; time < K; time++) {
            // 매 턴 새로운 격자 생성
            map = new ArrayList[N][N];

            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    map[r][c] = new ArrayList<>();
                }
            }

            // 1. 모든 원자 이동
            for (Element e : elements) {
                int move = e.s % N;

                int nx = (e.x + dx[e.d] * move + N) % N;
                int ny = (e.y + dy[e.d] * move + N) % N;

                e.update(nx, ny);
                map[nx][ny].add(e);
            }

            // 2. 충돌 및 분열 결과를 담을 새로운 리스트
            List<Element> nextElements = new ArrayList<>();

            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    int size = map[r][c].size();

                    if (size == 0) {
                        continue;
                    }

                    // 원자가 하나라면 그대로 유지
                    if (size == 1) {
                        nextElements.add(map[r][c].get(0));
                        continue;
                    }

                    int sumM = 0;
                    int sumS = 0;
                    int oddCount = 0;
                    int evenCount = 0;

                    for (Element e : map[r][c]) {
                        sumM += e.m;
                        sumS += e.s;

                        if (e.d % 2 == 0) {
                            evenCount++;
                        } else {
                            oddCount++;
                        }
                    }

                    int resultM = sumM / 5;
                    int resultS = sumS / size;

                    // 질량이 0이면 소멸
                    if (resultM == 0) {
                        continue;
                    }

                    // 방향이 모두 홀수이거나 모두 짝수인 경우
                    if (oddCount == 0 || evenCount == 0) {
                        for (int d = 0; d < 8; d += 2) {
                            nextElements.add(
                                new Element(r, c, resultM, resultS, d)
                            );
                        }
                    } else {
                        // 홀수와 짝수가 섞인 경우
                        for (int d = 1; d < 8; d += 2) {
                            nextElements.add(
                                new Element(r, c, resultM, resultS, d)
                            );
                        }
                    }
                }
            }

            elements = nextElements;
        }

        int sum = 0;

        for (Element e : elements) {
            sum += e.m;
        }

        System.out.println(sum);
    }
}
