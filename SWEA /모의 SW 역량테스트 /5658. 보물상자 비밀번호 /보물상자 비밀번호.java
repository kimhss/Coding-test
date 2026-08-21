import java.util.*;
import java.io.*;

public class Solution {
    static int T;
    static int N, K;
    static String str;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {

            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            str = br.readLine();
            String[] strArr = str.split("");

            // 중복 제거
            Set<Integer> set = new HashSet<>();

            int len = N / 4;
            int time = 0;

            // N / 4번만 회전하면 모든 경우를 확인할 수 있음
            while (time < N / 4) {

                // 현재 상태에서 4개의 숫자 추출
                for (int i = 0; i < 4; i++) {

                    StringBuilder sb = new StringBuilder();

                    for (int j = i * len; j < (i + 1) * len; j++) {
                        sb.append(strArr[j]);
                    }

                    int number = Integer.parseInt(sb.toString(), 16);

                    set.add(number);
                }

                // 한 칸 회전
                String tmp = strArr[0];

                for (int i = 1; i < N; i++) {
                    strArr[i - 1] = strArr[i];
                }

                strArr[N - 1] = tmp;

                time++;
            }

            // 큰 값부터 꺼내기
            PriorityQueue<Integer> pq =
                    new PriorityQueue<>(Collections.reverseOrder());

            pq.addAll(set);

            int result = 0;

            for (int i = 0; i < K; i++) {
                result = pq.poll();
            }

            System.out.println("#" + (t + 1) + " " + result);
        }
    }
}
