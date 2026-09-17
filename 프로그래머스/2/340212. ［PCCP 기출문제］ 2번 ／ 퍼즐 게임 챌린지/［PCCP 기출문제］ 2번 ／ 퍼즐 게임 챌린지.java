import java.util.*;

class Solution {
    static int[] diffs;
    static int[] times;
    static long limit;

    public int solution(int[] diffs, int[] times, long limit) {
        int answer = 0;

        this.diffs = diffs;
        this.times = times;
        this.limit = limit;

        int max = 0;
        for (int i = 0; i < diffs.length; i++) {
            if (diffs[i] > max) max = diffs[i];
        }

        int left = 1;
        int right = max;

        while (left <= right) {
            int level = (left + right) / 2;

            if (possible(level)) {
                right = level - 1;
                answer = level;
            }
            else {
                left = level + 1;
            }
        }

        return answer;
    }

    private static boolean possible(int level) {
        int time_cur = 0;
        int time_prev = 0;
        long total = 0;

        for (int i = 0; i < diffs.length; i++) {
            time_cur = times[i];

            if (diffs[i] <= level) {
                total += time_cur;
            }
            else {
                int wrong = diffs[i] - level;

                long time = ((long) time_cur + time_prev) * wrong + time_cur;
                total += time;
            }

            // 이전 퍼즐의 기본 소요 시간 저장
            time_prev = time_cur;
        }

        return total <= limit;
    }
}