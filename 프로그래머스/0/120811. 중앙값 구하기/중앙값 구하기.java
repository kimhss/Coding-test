import java.util.*;

class Solution {
    public int solution(int[] array) {
        Arrays.sort(array);

        if (array.length % 2 == 0)
            return array[array.length / 2 - 1];
        else
            return array[array.length / 2];
    }
}