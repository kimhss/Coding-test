import java.util.*;

class Solution {
    public int solution(int[] nums) {
        int answer = 0;
        int have = nums.length / 2;
        HashMap<Integer, Integer> map = new HashMap<>();
        /* nums에 있는 포켓몬 종류들이.. have의 개수보다 적으면 종류 개수
           have보다 많거나 같으면 have 개수와 동일*/
        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        if (map.size() < have) {
            answer = map.size();
        }
        else answer = have;
        
        
        
        return answer;
    }
}