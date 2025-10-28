import java.util.*;

class Solution {
    public int solution(String before, String after) {
        int answer = 1;
        HashMap<Character, Integer> map = new HashMap<>();
        char arr1[] = before.toCharArray();
        char arr2[] = after.toCharArray();
        
        for(int i = 0; i < arr1.length; i++) {
            map.put(arr1[i], map.getOrDefault(arr1[i], 0) + 1);
        }
        
        for(int i = 0; i < arr2.length; i++) {
            if(map.containsKey(arr2[i])) {
                if (map.get(arr2[i]) == 0) return 0;
                map.put(arr2[i], map.get(arr2[i]) - 1);
            }
            else return 0;
        }
        return answer;
    }
}