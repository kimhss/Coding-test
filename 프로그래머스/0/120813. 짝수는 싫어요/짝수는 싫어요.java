import java.util.*;

class Solution {
    public int[] solution(int n) {
        List<Integer> list = new ArrayList<Integer>();
        for(int i = 1; i <= n; i++)
            if(i % 2 != 0)
                list.add(i);
        
        int[] arr = list.stream().mapToInt(i -> i).toArray();
        return arr;
    }
}