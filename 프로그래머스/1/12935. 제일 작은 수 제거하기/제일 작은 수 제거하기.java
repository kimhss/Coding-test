import java.util.*;

class Solution {
    public int[] solution(int[] arr) {
        int[] answer = {-1};
        List<Integer> list = new ArrayList<>();
        int min = arr[0];
        for(int n : arr) {
            list.add(n);
            if(n < min) min = n;
        }
        list.remove(list.indexOf(min));
        
        if(list.size() != 0) {
            return list.stream().mapToInt(i -> i).toArray();
        } 
        
        return answer;
    }
}