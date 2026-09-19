import java.util.*;

public class Main {

    static String S;
    static int K, M;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        S = scanner.next();
        K = scanner.nextInt();
        M = scanner.nextInt();

        Map<Long, Integer> map = new HashMap<>();

        // 길이가 K인 모든 패턴 검사
        for (int i = 0; i <= S.length() - K; i++) {

            long sum = 0;

            // i부터 길이 K만큼 이진수 계산
            for (int j = i; j < i + K; j++) {
                sum = sum * 2 + (S.charAt(j) - '0');
            }

            // 패턴 등장 횟수 증가
            map.put(sum, map.getOrDefault(sum, 0) + 1);

            if (map.get(sum) >= M) {
                System.out.println(1);
                return;
            }
        }

        System.out.println(0);
    }
}