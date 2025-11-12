import java.util.*;

class Solution {
    public int[] solution(String[] strlist) {
        int[] answer = {};
        List<Integer> list = new ArrayList<>();
        for(String s : strlist) {
            list.add(s.length());
        }
        answer = list.stream().mapToInt(Integer::intValue).toArray();
        return answer;
    }
}