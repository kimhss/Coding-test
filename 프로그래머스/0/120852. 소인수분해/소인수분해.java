import java.util.*;

class Solution {
    public int[] solution(int n) {
        int[] answer = {};
        
        // 순서 보장 & 중복 제거
        LinkedHashSet<Integer> list = new LinkedHashSet<>();
        
        int i = 2;
        while(i <= n && n != 0) {            
            if (n % i == 0) {
                n /= i;
                list.add(i);
            }
            else {
                i++;
            }
        }
        
        answer = list.stream().mapToInt(Integer::intValue).toArray();

        
        return answer;
    }
}