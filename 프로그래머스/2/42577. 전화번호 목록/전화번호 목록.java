import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        boolean answer = true;
        Arrays.sort(phone_book);
        // 문자열을 정렬하면 접두어 관계가 있는 문자열들이 이웃하게 된다.
        // 따라서 앞뒤(인접한 두 개) 만 비교해도 모든 접두어 관계를 확인할 수 있다.
        for (int i = 0; i < phone_book.length - 1; i++) {
            if (phone_book[i+1].indexOf(phone_book[i]) == 0) return false;
        }
        return answer;
    }
}