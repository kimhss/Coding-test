import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        Stack<Integer> st = new Stack<>();
        st.add(arr[0]);
        for(int i = 1; i < arr.length; i++) {
            if(arr[i] == st.peek()) {
                continue;
            }
            st.add(arr[i]);
        }

        return st.stream().mapToInt(i -> i).toArray();
    }
}